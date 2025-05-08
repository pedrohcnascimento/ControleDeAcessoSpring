package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaJustificativa;
import com.senai.ControleDeAcessoSpring.domain.enums.TipoDeJustificativa;

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
        Long alunoId
) {
}
