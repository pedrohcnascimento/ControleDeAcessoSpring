package com.senai.ControleDeAcessoSpring.aplication.service.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.JustificativaDto;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaJustificativa;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.aluno.JustificativaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JustificativaService {

    @Autowired
    JustificativaRepository justificativaRepository;

    @Transactional
    public void cadastrarJustificativa(JustificativaDto dto) {
        Justificativa justificativa = dto.fromDto();
        justificativa.setAtivo(true);
        justificativa.setDataHoraCriacao(LocalDateTime.now());
        justificativa.setDataHoraConclusao(null);
        justificativa.setStatus(StatusDaJustificativa.AGUARDANDO_ANALISE);
        justificativaRepository.save(justificativa);
    }

    @Transactional
    public List<JustificativaDto> listar() {
       return justificativaRepository.findByAtivoTrue()
               .stream().map(JustificativaDto::toDto)
               .collect(Collectors.toList());
    }

    @Transactional
    public Optional<JustificativaDto> buscarPorId(Long id) {
        return justificativaRepository.findById(id).map(JustificativaDto::toDto);
    }

    @Transactional
    public boolean inativar(Long id) {
        return justificativaRepository.findById(id).map(justificativa -> {
            justificativa.setAtivo(false);
            justificativaRepository.save(justificativa);
            return true;
        }).orElse(false);
    }

    @Transactional
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
