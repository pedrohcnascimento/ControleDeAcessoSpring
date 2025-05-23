package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.AulasDoDia;
import com.senai.ControleDeAcessoSpring.domain.enums.DiasDaSemana;

import java.util.List;

public record AulasDoDiaDto(
        Long id,
        DiasDaSemana diaDaSemana,
        List<AulaDto> aulas
) {
    public static AulasDoDiaDto toDto(AulasDoDia a) {
        return new AulasDoDiaDto(
                a.getId(),
                a.getDiaDaSemana(),
                a.getAulas().stream().map(AulaDto::toDto).toList()
        );
    }

    public AulasDoDia fromDto() {
        AulasDoDia aulasDia = new AulasDoDia();
        aulasDia.setDiaDaSemana(diaDaSemana);
        aulasDia.setAulas(aulas.stream().map(AulaDto::fromDto).toList());
        return aulasDia;
    }
}