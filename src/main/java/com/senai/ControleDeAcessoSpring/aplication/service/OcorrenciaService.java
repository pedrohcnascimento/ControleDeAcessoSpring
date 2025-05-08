package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.OcorrenciaDto;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaOcorrencia;
import com.senai.ControleDeAcessoSpring.domain.repository.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        ocorrencia.setDataHoraCriacao(dto.dataHoraCriacao());
        ocorrencia.setDataHoraConclusao(dto.dataHoraConclusao());
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
                ocorrencia.getAluno().getId(),
                ocorrencia.getProfessorResponsavel().getId(),
                ocorrencia.getUnidadeCurricular().getId()
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
                ocorrencia.getAluno().getId(),
                ocorrencia.getProfessorResponsavel().getId(),
                ocorrencia.getUnidadeCurricular().getId()
        ));
    }

    public boolean alterarStatus(Long id, StatusDaOcorrencia status) {
        ocorrenciaRepository.findById(id).map(ocorrencia -> {
                    ocorrencia.setStatus(status);

                    ocorrenciaRepository.save(ocorrencia);
                    return true;
                }
        ).orElse(false);
        return false;
    }


}
