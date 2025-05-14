package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.OcorrenciaDto;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaJustificativa;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaOcorrencia;
import com.senai.ControleDeAcessoSpring.domain.repository.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OcorrenciaService {

    @Autowired
    OcorrenciaRepository ocorrenciaRepository;

    public void cadastrarOcorrencia(OcorrenciaDto dto) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setTipo(dto.tipoDeOcorrencia());
        ocorrencia.setDescricao(dto.descricao());
        ocorrencia.setStatus(StatusDaOcorrencia.AGUARDANDO_AUTORIZACAO);
        ocorrencia.setDataHoraCriacao(LocalDateTime.now());
        ocorrencia.setDataHoraConclusao(null);
        ocorrencia.setAluno(null);
        ocorrencia.setProfessorResponsavel(null);
        ocorrencia.setUnidadeCurricular(null);
        ocorrenciaRepository.save(ocorrencia);
    }

    public List<OcorrenciaDto> listar() {
        return ocorrenciaRepository.findAll().stream().map(ocorrencia -> new OcorrenciaDto(
                ocorrencia.getId(),
                ocorrencia.getTipo(),
                ocorrencia.getDescricao(),
                ocorrencia.getStatus(),
                ocorrencia.getDataHoraCriacao(),
                ocorrencia.getDataHoraConclusao(),
                null,
                null,
                null //teste
        )).toList();
    }

    public Optional<OcorrenciaDto> buscarPorId(Long id) {
        return ocorrenciaRepository.findById(id).map(ocorrencia -> new OcorrenciaDto(
                ocorrencia.getId(),
                ocorrencia.getTipo(),
                ocorrencia.getDescricao(),
                ocorrencia.getStatus(),
                ocorrencia.getDataHoraCriacao(),
                ocorrencia.getDataHoraConclusao(),
                null,
                null,
                null //Teste
        ));
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
}
