package com.senai.ControleDeAcessoSpring.aplication.service.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.AlunoDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.JustificativaDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.OcorrenciaDto;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.UnidadeCurricular;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.Semestre;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.SubTurma;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.Aula;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.AulaDoDia;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.HorarioPadrao;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Professor;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.ControleDeAcessoSpring.domain.enums.*;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.aluno.AlunoRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.aluno.JustificativaRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.aluno.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    private JustificativaRepository justificativaRepository;

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void cadastrarAluno(List<AlunoDto> listaDtos) {
        listaDtos.forEach(alunoDto -> {
            Aluno aluno = alunoDto.fromDTO();
            aluno.setSenha(passwordEncoder.encode(alunoDto.senha()));
            alunoRepository.save(aluno);
        });
    }

    public List<AlunoDto> listarAtivos() {
        return alunoRepository.findByAtivoTrue()
                .stream().map(AlunoDto::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AlunoDto> buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .filter(Aluno::getAtivo)
                .map(AlunoDto::toDTO);
    }

    public boolean atualizar(Long id, AlunoDto dto) {
        return alunoRepository.findById(id).map(aluno -> {
            Aluno alunoAtualizado = dto.fromDTO();
            aluno.setNome(alunoAtualizado.getNome());
            aluno.setEmail(alunoAtualizado.getEmail());
            aluno.setDataNascimento(alunoAtualizado.getDataNascimento());
            aluno.setCpf(alunoAtualizado.getCpf());
            alunoRepository.save(aluno);
            return true;
        }).orElse(false);
    }

    public boolean inativar(Long id) {
        return alunoRepository.findById(id).map(aluno -> {
            aluno.setAtivo(false);
            alunoRepository.save(aluno);
            return true;
        }).orElse(false);
    }

    // Justificativas
    public List<JustificativaDto> listarJustificativas(Long id) {
        return alunoRepository.findById(id).get().getJustificativas().stream().filter(justificativa -> {
            if (justificativa.getStatus().equals(StatusDaJustificativa.INATIVADA)) {
                return false;
            } else return true;
        }) .map(JustificativaDto::toDto).toList();
    }

    public Optional<JustificativaDto> listarJustificativaPorId(Long idJustificativa) {
        return justificativaRepository.findById(idJustificativa).map(JustificativaDto::toDto);
    }

    public boolean criarJustificativa(Long id, JustificativaDto justificativaDto) {
        Justificativa j = justificativaDto.fromDto();
        j.setAluno(alunoRepository.findById(id).get());
        j.setStatus(StatusDaJustificativa.AGUARDANDO_ANALISE);
        j.setDataHoraCriacao(LocalDateTime.now()); // Hora em que é cadastrada
        j.setDataHoraConclusao(null); // Ainda não foi concluída
        return alunoRepository.findById(id).map(aluno -> {
            aluno.getJustificativas().add(j);
            justificativaRepository.save(j);
            return true;
        }).orElse(false);
    }

    public boolean alterarStatusJustificativa(Long idJustificativa, StatusDaJustificativa status) {
        justificativaRepository.findById(idJustificativa).map(justificativa -> {
                    justificativa.setStatus(status);
                    if (status.equals(StatusDaJustificativa.APROVADA) || status.equals(StatusDaJustificativa.REPROVADA)) {
                        justificativa.setDataHoraConclusao(LocalDateTime.now());
                    }
                    justificativaRepository.save(justificativa);
                    return true;
                }
        );
        return false;
    }

    public boolean inativarJustificativa(Long idJustificativa) {
        justificativaRepository.findById(idJustificativa).map(justificativa -> {
            justificativa.setStatus(StatusDaJustificativa.INATIVADA);
            justificativaRepository.save(justificativa);
            return true;
        });
        return false;
    }

    // Ocorrência Saída
    public List<OcorrenciaDto> listarOcorrencias(Long id) {
        return alunoRepository.findById(id).get().getOcorrencias().stream().filter(ocorrencia -> {
            if (ocorrencia.getStatus().equals(StatusDaOcorrencia.INATIVADA)) {
                return false;
            } else return true;
        }) .map(OcorrenciaDto::toDto).toList();
    }

    public Optional<OcorrenciaDto> listarOcorrenciaPorId(Long idOcorrencia) {
        return ocorrenciaRepository.findById(idOcorrencia).map(OcorrenciaDto::toDto);
    }

    public boolean alterarStatusOcorrencia(Long idOcorrencia, StatusDaOcorrencia status) {
        ocorrenciaRepository.findById(idOcorrencia).map(ocorrencia -> {
                    ocorrencia.setStatus(status);
                    if (status.equals(StatusDaOcorrencia.APROVADO) || status.equals(StatusDaOcorrencia.REPROVADO)) {
                        ocorrencia.setDataHoraConclusao(LocalDateTime.now());
                    }
                    ocorrenciaRepository.save(ocorrencia);
                    return true;
                }
        );
        return false;
    }

    public boolean inativarOcorrencia(Long idOcorrencia) {
        ocorrenciaRepository.findById(idOcorrencia).map(ocorrencia -> {
            ocorrencia.setStatus(StatusDaOcorrencia.INATIVADA);
            ocorrenciaRepository.save(ocorrencia);
            return true;
        });
        return false;
    }

    public boolean criarOcorrenciaSaida(Long id, OcorrenciaDto ocorrenciaDto) {
        Optional<Aluno> alunoOpt = alunoRepository.findById(id);
        if (alunoOpt.isPresent()) {
            SubTurma subTurma = definirSubTurma(alunoOpt.get(), ocorrenciaDto.dataHoraConclusao());
            Semestre semestre =definirSemestre(subTurma);
            Aula aula = definirAula(semestre, subTurma);
            Ocorrencia o = ocorrenciaDto.fromDto();
            o.setAluno(alunoRepository.findById(id).get());
            o.setStatus(StatusDaOcorrencia.AGUARDANDO_AUTORIZACAO);
            o.setTipo(TipoDeOcorrencia.SAIDA_ANTECIPADA); // Só pode ser de saída
            o.setDataHoraCriacao(LocalDateTime.now()); // Hora em que é cadastrada
            o.setDataHoraConclusao(null); // Utilizar dataHoraConclusao como a horaDaSaidaAntecipada?
            o.setProfessorResponsavel(aula.getProfessor());
            o.setUnidadeCurricular(aula.getUnidadeCurricular());
            return alunoRepository.findById(id).map(aluno -> {
                aluno.getOcorrencias().add(o);
                ocorrenciaRepository.save(o);
                return true;
            }).orElse(false);
        }
        return false;
    }
    private SubTurma definirSubTurma(Aluno aluno, LocalDateTime dataHoraConclusao) {
        LocalTime horaSaidaAntecipada = dataHoraConclusao.toLocalTime();
        SubTurma subTurma = null;

        for (SubTurma st : aluno.getSubTurmas()) {
            if (st.getTurma().getPeriodo().equals(Periodo.INTEGRAL)) {
                subTurma = st;
                break;
            }

            LocalTime horarioDeEntrada = st.getTurma().getHorarioEntrada();

            LocalTime horarioDeSaida = horarioDeEntrada.plusMinutes(
                    ((long) st.getTurma().getQtdAulasPorDia() *
                            st.getTurma().getCurso().getTipo().getMinutosPorAula()) +
                            st.getTurma().getCurso().getTipo().getIntervaloMinutos()
            );

            if (horaSaidaAntecipada.isAfter(horarioDeEntrada) &&
                    horaSaidaAntecipada.isBefore(horarioDeSaida)) {
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
        System.out.println("Dia da semana: " + diaDaSemanaHoje.name());

        AulaDoDia aulas = horario.getAulasDoDia().stream()
                .filter(aulaDoDia -> aulaDoDia.getDiaDaSemana().equals(diaDaSemanaHoje))
                .findFirst()
                .orElse(null);

        if (aulas == null) throw new RuntimeException("Lista de aulas não determinada!");

        aulas.getAulas().forEach(aula -> {
            System.out.println(aula.getUnidadeCurricular().getNome());
        });

        LocalTime inicioDoDia = subTurma.getTurma().getHorarioEntrada();
        System.out.println("Horario limite de entrada: " + inicioDoDia);


        long minutosPassados = Duration.between(inicioDoDia, LocalTime.now()).toMinutes();

        System.out.println("Horas passadas: " + minutosPassados / 60);

        long aula = (minutosPassados /
                ((long) subTurma.getTurma().getCurso().getTipo().getMinutosPorAula() *
                        subTurma.getTurma().getQtdAulasPorDia()))
                + 1;
        System.out.println("aula N° " + aula);

        Aula aulaEncontrada = aulas.getAulas().get((int) aula);

        System.out.println("Aula encontrada: " + aulaEncontrada.getAmbiente());

        if (aulaEncontrada == null) throw new RuntimeException("Aula não encontrada");

        System.out.println("Aula - " + aulas.getAulas().indexOf(aulaEncontrada));
        System.out.println(aulaEncontrada.getUnidadeCurricular().getNome());

        return aulaEncontrada;
    }
}
