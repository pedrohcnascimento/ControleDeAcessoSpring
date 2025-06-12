package com.senai.ControleDeAcessoSpring.aplication.service.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.OcorrenciaDto;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaOcorrencia;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.aluno.OcorrenciaRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OcorrenciaService {

    @Autowired
    OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

//    public void cadastrarOcorrenciaSaida(OcorrenciaDto dto) {
//        Ocorrencia ocorrencia = dto.fromDto(alunoRepository.findById(id), new Professor(), new UnidadeCurricular());
//        ocorrencia.setTipo(TipoDeOcorrencia.SAIDA_ANTECIPADA);
//        ocorrencia.setAtivo(true);
//        ocorrencia.setStatus(StatusDaOcorrencia.AGUARDANDO_AUTORIZACAO);
//        ocorrencia.setDataHoraCriacao(LocalDateTime.now());
//        ocorrencia.setDataHoraConclusao(null);
//        ocorrenciaRepository.save(ocorrencia);
//    }  Ocorrência será criada no AlunoController

    public List<OcorrenciaDto> listar() {
        return ocorrenciaRepository.findByStatusOrStatusOrStatusOrStatus(StatusDaOcorrencia.AGUARDANDO_AUTORIZACAO, StatusDaOcorrencia.APROVADO, StatusDaOcorrencia.REPROVADO, StatusDaOcorrencia.AGUARDANDO_CIENCIA_PROFESSOR)
                .stream().map(OcorrenciaDto::toDto).toList();
    }

    public Optional<OcorrenciaDto> buscarPorId(Long id) {
        return ocorrenciaRepository.findById(id).map(OcorrenciaDto::toDto);
    }

    public boolean inativar(Long id) {
        return ocorrenciaRepository.findById(id).map(ocorrencia -> {
            ocorrencia.setStatus(StatusDaOcorrencia.INATIVADA);
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

    }
}
