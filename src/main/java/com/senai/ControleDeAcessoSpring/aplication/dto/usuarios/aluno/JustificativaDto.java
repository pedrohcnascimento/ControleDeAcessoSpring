package com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaJustificativa;
import com.senai.ControleDeAcessoSpring.domain.enums.TipoDeJustificativa;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record JustificativaDto(
        Long id,
        TipoDeJustificativa tipo,
        String descricao,
        String anexo,
        LocalDate dataInicial,
        Integer qtdDias,
        LocalDateTime dataHoraCriacao,
        LocalDateTime dataHoraConclusao,
        StatusDaJustificativa status,
        Long alunoId
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
        j.setId(id);
        j.setTipo(tipo);
        j.setDescricao(descricao);
        j.setAnexo(anexo);
        j.setDataInicial(dataInicial);
        j.setQtdDias(qtdDias);
        j.setDataHoraCriacao(dataHoraCriacao);
        j.setDataHoraConclusao(dataHoraConclusao);
        j.setStatus(status);
        return j;
    }
}