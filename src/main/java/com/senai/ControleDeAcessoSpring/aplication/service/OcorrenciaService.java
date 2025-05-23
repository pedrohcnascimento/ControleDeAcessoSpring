package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Usuario;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaOcorrencia;
import com.senai.ControleDeAcessoSpring.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OcorrenciaService {

    @Autowired
    UsuarioRepository usuarioRepository;

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
                //Turma errada, buscar na outra
            } else if (horaAtual.isAfter(horarioLimite)) {
                return true;
            }
        }
        return false;
    }

    private void criarOcorrencia(Aluno aluno) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setAtivo(true);
        ocorrencia.setStatus(StatusDaOcorrencia.AGUARDANDO_AUTORIZACAO);
        ocorrencia.setDataHoraCriacao(LocalDateTime.now());
        ocorrencia.setDataHoraConclusao(null);
        ocorrencia.setAluno(aluno);
        //ocorrencia.setProfessorResponsavel(aluno.getSubTurmas().get().getHorarioPadrao().getAulasDoDia().get().getAulas().get().getProfessor());
        //ocorrencia.setUnidadeCurricular(aluno.getSubTurmas().get().getTurma().getCurso().getUnidadesCurriculares().get());

        List<Ocorrencia> ocorrenciasAtualizada = new ArrayList<>();
        ocorrenciasAtualizada.add((Ocorrencia) aluno.getOcorrencias());
        ocorrenciasAtualizada.add(ocorrencia);
        aluno.setOcorrencias(ocorrenciasAtualizada);
    }
}

