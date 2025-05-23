package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.HorarioSemanal;

import java.time.LocalDate;
import java.util.List;

public record HorarioSemanalDto(
        Long id,
        Long semestreId,
        LocalDate semanaReferencia,
        List<AulasDoDiaDto> listaDeAulasDoDia
) {
    public static HorarioSemanalDto toDto(HorarioSemanal h) {
        return new HorarioSemanalDto(
                h.getId(),
                h.getSemestre() != null ? h.getSemestre().getId() : null,
                h.getSemanaReferencia(),
                h.getListaDeAulasDoDia().stream().map(AulasDoDiaDto::toDto).toList()
        );
    }
    public HorarioSemanal fromDto() {
        HorarioSemanal horario = new HorarioSemanal();
        horario.setSemanaReferencia(semanaReferencia);
        horario.setListaDeAulasDoDia(
                listaDeAulasDoDia.stream().map(AulasDoDiaDto::fromDto).toList()
        );
        return horario;
    }
}
