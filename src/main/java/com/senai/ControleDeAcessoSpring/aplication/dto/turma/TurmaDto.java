package com.senai.ControleDeAcessoSpring.aplication.dto.turma;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.Turma;
import com.senai.ControleDeAcessoSpring.domain.enums.Periodo;

import java.time.LocalDate;
import java.time.LocalTime;

public record TurmaDto(
        Long id,
        String sigla,
        Periodo periodo,
        LocalDate dataInicial,
        LocalTime horarioEntrada,
        Integer qtdSemestres,
        Integer qtdAulasPorDia,
        Boolean ativo,
        Long cursoId
) {
    public static TurmaDto toDto(Turma turma) {
        return new TurmaDto(
                turma.getId(),
                turma.getSiglaDaTurma(),
                turma.getPeriodo(),
                turma.getDataInicial(),
                turma.getHorarioEntrada(),
                turma.getQtdSemestres(),
                turma.getQtdAulasPorDia(),
                turma.getAtivo(),
                turma.getCurso() != null ? turma.getCurso().getId() : null
        );
    }

    public Turma fromDto() {
        Turma turma = new Turma();
        turma.setSiglaDaTurma(sigla);
        turma.setPeriodo(periodo);
        turma.setAtivo(ativo);
        turma.setDataInicial(dataInicial);
        turma.setHorarioEntrada(horarioEntrada);
        turma.setQtdSemestres(qtdSemestres);
        return turma;
    }
}