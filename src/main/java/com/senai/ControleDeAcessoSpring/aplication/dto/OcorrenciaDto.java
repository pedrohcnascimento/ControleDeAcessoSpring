package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaOcorrencia;
import com.senai.ControleDeAcessoSpring.domain.enums.TipoDeOcorrencia;

import java.time.LocalDateTime;

public record OcorrenciaDto (
        Long id,
        TipoDeOcorrencia tipoDeOcorrencia,
        String descricao,
        StatusDaOcorrencia status,
        LocalDateTime dataHoraCriacao,
        LocalDateTime dataHoraConclusao,
        Long alunoId,
        Long professorResponsavelId,
        Long unidadeCurricularId
){
}
