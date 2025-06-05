package com.senai.ControleDeAcessoSpring.aplication.dto.turma.horario;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.AulaDoDia;
import com.senai.ControleDeAcessoSpring.domain.enums.DiaDaSemana;

import java.util.List;

public record AulaDoDiaDto(
        Long id,
        DiaDaSemana diaDaSemana,
        List<AulaDto> aulas
) {
    public static AulaDoDiaDto toDto(AulaDoDia d) {
        return new AulaDoDiaDto(
                d.getId(),
                d.getDiaDaSemana(),
                d.getAulas().stream().map(AulaDto::toDto).toList()
        );
    }

    public AulaDoDia fromDto() {
        AulaDoDia dia = new AulaDoDia();
        dia.setDiaDaSemana(diaDaSemana);
        dia.setAulas(aulas.stream().map(AulaDto::fromDto).toList());
        return dia;
    }
}