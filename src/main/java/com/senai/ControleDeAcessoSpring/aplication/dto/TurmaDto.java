package com.senai.ControleDeAcessoSpring.aplication.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record TurmaDto(
        String nome,
        String periodo,
        LocalDate dataInicial,
        LocalTime horarioEntrada,
        Integer qtdSemestres,
        Integer qtdAulasPorDia,
        CursoDto curso,
        Boolean status
) {
}