package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.ControleDeAcessoSpring.domain.enuns.StatusDaOcorrencia;
import com.senai.ControleDeAcessoSpring.domain.enuns.TipoDeOcorrencia;

import java.time.LocalDateTime;

public record OcorrenciaDto (
        Long id,
        TipoDeOcorrencia tipoDeOcorrencia,
        String descricao,
        StatusDaOcorrencia status,
        LocalDateTime dataHoraCriacao,
        LocalDateTime dataHoraConclusao,
        Long idAluno,
        Long idProfessorResponsavel,
        Long idUnidadeCurricular
){

    public static OcorrenciaDto toDto(Ocorrencia o) {
        return new OcorrenciaDto(
                o.getId(),
                o.getTipo(),
                o.getDescricao(),
                o.getStatus(),
                o.getDataHoraCriacao(),
                o.getDataHoraConclusao(),
                o.getAluno().getId(),
                o.getProfessorResponsavel().getId(),
                o.getUnidadeCurricular().getId()
        );
    }

    public Ocorrencia fromDto() {
        Ocorrencia o = new Ocorrencia();
        o.setId(this.id);
        o.setTipo(this.tipoDeOcorrencia);
        o.setDescricao(this.descricao);
        o.setDataHoraCriacao(this.dataHoraCriacao);
        o.setDataHoraConclusao(this.dataHoraConclusao);
        o.setStatus(this.status);
        return o;
    }
}
