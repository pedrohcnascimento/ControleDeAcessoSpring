package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.curso.Curso;

import java.time.LocalDate;
import java.time.LocalTime;

public record TurmaDto(
        Long id,
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