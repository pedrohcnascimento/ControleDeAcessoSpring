package com.senai.ControleDeAcessoSpring.aplication.service.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.OcorrenciaDto;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Usuario;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaOcorrencia;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.aluno.OcorrenciaRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
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

    public List<OcorrenciaDto> listar() {
        return ocorrenciaRepository.findByAtivoTrue()
                .stream().map(OcorrenciaDto::toDto)
                .collect(Collectors.toList());
    }

    public Optional<OcorrenciaDto> buscarPorId(Long id) {
        return ocorrenciaRepository.findById(id).map(OcorrenciaDto::toDto);
    }

    public boolean inativar(Long id) {
        return ocorrenciaRepository.findById(id).map(ocorrencia -> {
            ocorrencia.setAtivo(false);
            ocorrenciaRepository.save(ocorrencia);
            return true;
        }).orElse(false);
    }

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

    public void criarOcorrenciaAtraso(String idAcesso) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByIdAcesso(idAcesso);
        if (usuarioOpt.isPresent()) {
            System.out.println("O usuário existe!");
            if (usuarioOpt.get() instanceof Aluno aluno) {
                System.out.println("O usuário " + aluno.getNome() + " é um aluno!");
                if (verificarAtraso(aluno)) {
                    System.out.println("Aluno atrasado");
                    criarOcorrencia(aluno);
                    System.out.println("Ocorrência gerada");
                }
            }
        } else {
            System.out.println("O usuário não existe e não pode te machucar!");
        }
    }

    private boolean verificarAtraso(Aluno aluno) {
        for (int i = 0; i < aluno.getSubTurmas().size(); i++) {
            LocalTime horarioEntrada = aluno.getSubTurmas().get(i).getTurma().getHorarioEntrada();
            Integer toleranciaMinutos = aluno.getSubTurmas().get(i).getTurma().getCurso().getToleranciaMinutos();
            LocalTime horarioLimite = horarioEntrada.plusMinutes(toleranciaMinutos);
            LocalTime horaAtual = LocalTime.now();
            Duration diferenca = Duration.between(horarioEntrada, horaAtual);
            long diferencaMinutos = Math.abs(diferenca.toMinutes());

            if (diferencaMinutos > 300) {
                break; //Turma errada, buscar na outra
            } else if (horaAtual.isAfter(horarioLimite)) {
                return true;
            }
        }
        return false;
    }

    private void criarOcorrencia(Aluno aluno) {

    }
}
