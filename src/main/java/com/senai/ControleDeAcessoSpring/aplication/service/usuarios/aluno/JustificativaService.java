package com.senai.ControleDeAcessoSpring.aplication.service.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.JustificativaDto;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaJustificativa;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.aluno.JustificativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JustificativaService {

    @Autowired
    JustificativaRepository justificativaRepository;

//    public void cadastrarJustificativa(JustificativaDto dto) {
//        Justificativa justificativa = dto.fromDto();
//        justificativa.setDataHoraCriacao(LocalDateTime.now());
//        justificativa.setDataHoraConclusao(null);
//        justificativa.setStatus(StatusDaJustificativa.AGUARDANDO_ANALISE);
//        justificativaRepository.save(justificativa);
//    }

    public List<JustificativaDto> listar() {
       return justificativaRepository.findByStatusOrStatusOrStatus(StatusDaJustificativa.AGUARDANDO_ANALISE, StatusDaJustificativa.APROVADA, StatusDaJustificativa.REPROVADA)
               .stream().map(JustificativaDto::toDto).toList(); // Aprendi a língua do repository
    }

    public Optional<JustificativaDto> buscarPorId(Long id) {
        return justificativaRepository.findById(id).map(JustificativaDto::toDto);
    }

    public boolean inativar(Long id) {
        return justificativaRepository.findById(id).map(justificativa -> {
            justificativa.setStatus(StatusDaJustificativa.INATIVADA);
            justificativaRepository.save(justificativa);
            return true;
        }).orElse(false);
    }

    public boolean alterarStatus(Long id, StatusDaJustificativa status) {
        justificativaRepository.findById(id).map(justificativa -> {
                    justificativa.setStatus(status);
                    if (status.equals(StatusDaJustificativa.APROVADA) || status.equals(StatusDaJustificativa.REPROVADA)) {
                        justificativa.setDataHoraConclusao(LocalDateTime.now());
                    }
                    justificativaRepository.save(justificativa);
                    return true;
        }
        ).orElse(false);
        return false;
    }
}
