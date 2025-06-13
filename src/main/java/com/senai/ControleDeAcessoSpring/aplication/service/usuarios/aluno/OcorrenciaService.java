package com.senai.ControleDeAcessoSpring.aplication.service.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.OcorrenciaDto;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.SubTurma;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Usuario;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.ControleDeAcessoSpring.domain.enums.Periodo;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaOcorrencia;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.aluno.OcorrenciaRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
}

