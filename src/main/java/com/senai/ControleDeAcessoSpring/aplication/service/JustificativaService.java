package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.JustificativaDto;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.ControleDeAcessoSpring.domain.enuns.StatusDaJustificativa;
import com.senai.ControleDeAcessoSpring.domain.repository.AlunoRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.JustificativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class JustificativaService {

    @Autowired
    JustificativaRepository justificativaRepository;

    @Autowired
    AlunoService alunoService;

    public void cadastrarJustificativa(JustificativaDto dto) {
        Justificativa justificativa = dto.fromDto();
        justificativa.setAtivo(true);
        justificativa.setDataHoraCriacao(LocalDateTime.now());
        justificativa.setDataHoraConclusao(null);
        justificativa.setStatus(StatusDaJustificativa.ANALISE);
        justificativa.setAluno(alunoService.buscarNoRepository(dto.idAluno()));
        justificativaRepository.save(justificativa);
    }

    public List<JustificativaDto> listar() {
       return justificativaRepository.findByAtivoTrue()
               .stream().map(JustificativaDto::toDto)
               .collect(Collectors.toList());
    }

    public Optional<JustificativaDto> buscarPorId(Long id) {
        return justificativaRepository.findById(id).map(JustificativaDto::toDto);
    }

    public boolean inativar(Long id) {
        return justificativaRepository.findById(id).map(justificativa -> {
            justificativa.setAtivo(false);
            justificativaRepository.save(justificativa);
            return true;
        }).orElse(false);
    }

    public boolean alterarStatus(Long id, StatusDaJustificativa status) {
        justificativaRepository.findById(id).map(justificativa -> {
                    justificativa.setStatus(status);
                    if (status.equals(StatusDaJustificativa.APROVADO) || status.equals(StatusDaJustificativa.REPROVADO)) {
                        justificativa.setDataHoraConclusao(LocalDateTime.now());
                    }
                    justificativaRepository.save(justificativa);
                    return true;
        }
        ).orElse(false);
        return false;
    }
}
