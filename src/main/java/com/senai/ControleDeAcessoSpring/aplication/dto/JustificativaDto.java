package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.ControleDeAcessoSpring.domain.enuns.StatusDaJustificativa;
import com.senai.ControleDeAcessoSpring.domain.enuns.TipoDeJustificativa;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record JustificativaDto(
        Long id,
        TipoDeJustificativa tipoDeJustificativa,
        String descricao,
        String anexo,
        LocalDate dataInicial,
        Integer qtdDias,
        LocalDateTime dataHoraCriacao,
        LocalDateTime dataHoraConclusao,
        StatusDaJustificativa status,
        Long idAluno
) {


    public static JustificativaDto toDto(Justificativa j) {
        return new JustificativaDto(
                j.getId(),
                j.getTipo(),
                j.getDescricao(),
                j.getAnexo(),
                j.getDataInicial(),
                j.getQtdDias(),
                j.getDataHoraCriacao(),
                j.getDataHoraConclusao(),
                j.getStatus(),
                j.getAluno().getId()
        );
    }

    public Justificativa fromDto() {
        Justificativa j = new Justificativa();
        j.setId(this.id);
        j.setTipo(this.tipoDeJustificativa);
        j.setDescricao(this.descricao);
        j.setAnexo(this.anexo);
        j.setDataInicial(this.dataInicial);
        j.setQtdDias(this.qtdDias);
        j.setDataHoraCriacao(this.dataHoraCriacao);
        j.setDataHoraConclusao(this.dataHoraConclusao);
        j.setStatus(this.status);
        return j;
    }
}
