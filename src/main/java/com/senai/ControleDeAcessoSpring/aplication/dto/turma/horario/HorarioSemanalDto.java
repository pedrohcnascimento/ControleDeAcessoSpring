package com.senai.ControleDeAcessoSpring.aplication.dto.turma.horario;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.HorarioSemanal;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record HorarioSemanalDto(
        Long id,
        Long semestreId,
        LocalDate semanaRefencial,
        List<AulaDoDiaDto> listaAulasDoDia
) {
    public static HorarioSemanalDto toDto(HorarioSemanal semanal) {
        return new HorarioSemanalDto(
                semanal.getId(),
                semanal.getSemestre().getId(),
                semanal.getSemanaReferencia(),
                semanal.getAulasDoDia().stream().map(AulaDoDiaDto::toDto).toList()
        );
    }

    public HorarioSemanal fromDto() {
        HorarioSemanal semanal = new HorarioSemanal();

        semanal.setId(id);
        semanal.setSemanaReferencia(semanaRefencial);
        semanal.setAulasDoDia(listaAulasDoDia.stream().map(AulaDoDiaDto::fromDto).toList());

        return semanal;
    }
}