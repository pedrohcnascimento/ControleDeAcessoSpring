package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaJustificativa;
import com.senai.ControleDeAcessoSpring.domain.enums.TipoDeJustificativa;

import java.time.LocalDate;

public record JustificativaDto(
        Long id,
        TipoDeJustificativa tipo,
        String descricao,
        String anexo,
        LocalDate dataInicial,
        Integer qtdDias,
        StatusDaJustificativa status,
        Aluno aluno
) {
    public static JustificativaDto toDto(Justificativa j) {
        return new JustificativaDto(
                j.getId(),
                j.getTipo(),
                j.getDescricao(),
                j.getAnexo(),
                j.getDataInicial(),
                j.getQtdDias(),
                j.getStatus(),
                j.getAluno()
        );
    }

    public Justificativa fromDto() {
        Justificativa justificativa = new Justificativa();
        justificativa.setId(id);
        justificativa.setTipo(tipo);
        justificativa.setDescricao(descricao);
        justificativa.setAnexo(anexo);
        justificativa.setDataInicial(dataInicial);
        justificativa.setQtdDias(qtdDias);
        justificativa.setStatus(status);
        justificativa.setAluno(aluno);
        return justificativa;
    }
}