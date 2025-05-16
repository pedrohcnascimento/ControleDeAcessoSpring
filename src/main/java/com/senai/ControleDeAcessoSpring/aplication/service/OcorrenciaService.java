package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.OcorrenciaDto;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.ControleDeAcessoSpring.domain.enuns.StatusDaOcorrencia;
import com.senai.ControleDeAcessoSpring.domain.repository.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OcorrenciaService {

    @Autowired
    OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    AlunoService alunoService;

    @Autowired
    ProfessorService professorService;

    @Autowired
    UnidadeCurricularService unidadeCurricularService;

    public void cadastrarOcorrencia(OcorrenciaDto dto) {
        Ocorrencia ocorrencia = dto.fromDto();
        ocorrencia.setAtivo(true);
        ocorrencia.setStatus(StatusDaOcorrencia.AGUARDANDO_AUTORIZACAO);
        ocorrencia.setDataHoraCriacao(LocalDateTime.now());
        ocorrencia.setDataHoraConclusao(null);
        ocorrencia.setAluno(alunoService.buscarNoRepository(dto.idAluno()));
        ocorrencia.setProfessorResponsavel(professorService.buscarNoRepository(dto.idProfessorResponsavel()));
        ocorrencia.setUnidadeCurricular(unidadeCurricularService.buscarNoRepository(dto.idUnidadeCurricular()));
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
}
