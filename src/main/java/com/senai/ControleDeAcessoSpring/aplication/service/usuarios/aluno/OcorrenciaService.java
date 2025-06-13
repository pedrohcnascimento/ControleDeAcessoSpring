package com.senai.ControleDeAcessoSpring.aplication.service.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.OcorrenciaDto;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.Semestre;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.SubTurma;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.Aula;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.AulaDoDia;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.HorarioPadrao;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Usuario;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.ControleDeAcessoSpring.domain.enums.DiaDaSemana;
import com.senai.ControleDeAcessoSpring.domain.enums.Periodo;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaOcorrencia;
import com.senai.ControleDeAcessoSpring.domain.enums.TipoDeOcorrencia;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.aluno.OcorrenciaRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.UsuarioRepository;
import jakarta.transaction.Transactional;
import jdk.dynalink.linker.ConversionComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OcorrenciaService {

    @Autowired
    OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional
    public void cadastrarOcorrencia(OcorrenciaDto dto) {
        Ocorrencia ocorrencia = dto.fromDto();
        ocorrencia.setAtivo(true);
        ocorrencia.setStatus(StatusDaOcorrencia.AGUARDANDO_AUTORIZACAO);
        ocorrencia.setDataHoraCriacao(LocalDateTime.now());
        ocorrencia.setDataHoraConclusao(null);
        ocorrencia.setAluno(null);
        ocorrencia.setProfessorResponsavel(null);
        ocorrencia.setUnidadeCurricular(null);
        ocorrenciaRepository.save(ocorrencia);
    }

    @Transactional
    public List<OcorrenciaDto> listar() {
        return ocorrenciaRepository.findByAtivoTrue()
                .stream().map(OcorrenciaDto::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<OcorrenciaDto> buscarPorId(Long id) {
        return ocorrenciaRepository.findById(id).map(OcorrenciaDto::toDto);
    }

    @Transactional
    public boolean inativar(Long id) {
        return ocorrenciaRepository.findById(id).map(ocorrencia -> {
            ocorrencia.setAtivo(false);
            ocorrenciaRepository.save(ocorrencia);
            return true;
        }).orElse(false);
    }

    @Transactional
    public boolean alterarStatus(Long id, StatusDaOcorrencia status) {
        ocorrenciaRepository.findById(id).map(ocorrencia -> {
                    ocorrencia.setStatus(status);
                    if (status.equals(StatusDaOcorrencia.APROVADO) || status.equals(StatusDaOcorrencia.REPROVADO)) {
                        ocorrencia.setDataHoraConclusao(LocalDateTime.now());
                    }
                    ocorrenciaRepository.save(ocorrencia);
                    return true;
                }
        ).orElse(false);
        return false;
    }

    @Transactional
    public void criarOcorrenciaDeAcesso(String idAcesso) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByIdAcesso(idAcesso);
        if (usuarioOpt.isPresent()) {
            System.out.println("O usuário existe!");
            if (usuarioOpt.get() instanceof Aluno aluno) {
                SubTurma st = definirSubTurma(aluno);

                System.out.println("Atrasado: " + ((st != null) ? "Sim":"Não"));

                if (st != null) {
                    System.out.println("Subturma: " + st.getNome());
                    System.out.println("Periodo: " + st.getTurma().getPeriodo().name());

                    Semestre semestre = definirSemestre(st);

                    System.out.println("Semestre: " + semestre.getId());

                    Aula aula = definirAula(semestre, st);

                    System.out.println("Aula referida: " + aula.getUnidadeCurricular().getNome());
                    System.out.println("Professor responsavel: " + aula.getProfessor().getNome());
                    System.out.println("Sala: " + aula.getAmbiente().getNome());

                    Ocorrencia novaOcorrencia = new Ocorrencia();

                    novaOcorrencia.setAluno(aluno);
                    novaOcorrencia.setAtivo(true);
                    novaOcorrencia.setTipo(TipoDeOcorrencia.ATRASO);
                    novaOcorrencia.setDataHoraCriacao(LocalDateTime.now());
                    novaOcorrencia.setProfessorResponsavel(aula.getProfessor());
                    novaOcorrencia.setUnidadeCurricular(aula.getUnidadeCurricular());

                    ocorrenciaRepository.save(novaOcorrencia);
                }
            }
        } else {
            System.out.println("O usuário não existe!");
        }
    }

    private SubTurma definirSubTurma(Aluno aluno) {
        LocalTime agora = LocalTime.now();
        SubTurma subTurma = null;

        for (SubTurma st : aluno.getSubTurmas()) {
            if (st.getTurma().getPeriodo().equals(Periodo.INTEGRAL)) {
                subTurma = st;
                break;
            }

            LocalTime horarioDeEntradaComTolerancia = st.getTurma().getHorarioEntrada()
                    .plusMinutes(
                            st.getTurma().getCurso().getToleranciaMinutos()
                    );

            LocalTime horarioDeSaida = horarioDeEntradaComTolerancia.plusMinutes(
                    ((long) st.getTurma().getQtdAulasporDia() *
                            st.getTurma().getCurso().getTipo().getMinutosPorAula()) +
                            st.getTurma().getCurso().getTipo().getIntervaloMinutos()
            );

            if (agora.isAfter(horarioDeEntradaComTolerancia) &&
                    agora.isBefore(horarioDeSaida)) {
                subTurma = st;
            }
        }

        return subTurma;
    }

    private Semestre definirSemestre(SubTurma subTurma) {
        Semestre semestre = null;

        LocalDate dataInicial = subTurma.getTurma().getDataInicial();
        LocalDate hoje = LocalDate.now();

        long mesesPassados = ChronoUnit.MONTHS.between(dataInicial, hoje);

        semestre = subTurma.getSemestres().get(((int) mesesPassados / 6));

        return semestre;
    }

    private Aula definirAula(Semestre semestre, SubTurma subTurma) {
        HorarioPadrao horario = semestre.getHorarioPadrao();
        DiaDaSemana diaDaSemanaHoje = (
                switch (LocalDate.now().getDayOfWeek()) {
                    case MONDAY -> DiaDaSemana.SEGUNDA;
                    case TUESDAY -> DiaDaSemana.TERCA;
                    case WEDNESDAY -> DiaDaSemana.QUARTA;
                    case THURSDAY -> DiaDaSemana.QUINTA;
                    case FRIDAY -> DiaDaSemana.SEXTA;
                    default -> DiaDaSemana.SEGUNDA;
                }
                );

        AulaDoDia aulas = horario.getAulasDoDia().stream()
                .filter(aulaDoDia -> aulaDoDia.getDiaDaSemana().equals(diaDaSemanaHoje))
                .findFirst()
                .orElse(null);

        if (aulas != null) return null;

        LocalTime agora = LocalTime.now();
        LocalTime inicioDoDia = subTurma.getTurma().getHorarioEntrada();

        long minutosPassados = (
                (agora.getHour() * 60 + agora.getMinute()) +
                (inicioDoDia.getHour() * 60 + agora.getMinute())
        );

        int aula = (int)
                (minutosPassados /
                        subTurma.getTurma().getCurso().getTipo().getMinutosPorAula())
                + 1;

        return aulas.getAulas().get(aula);
    }
}

