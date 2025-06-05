package com.senai.ControleDeAcessoSpring.aplication.dto.turma.horario;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.HorarioBase;

import java.util.List;

public record HorarioBaseDto(
        Long id,
        Long semestreId,
        List<AulaDoDiaDto> aulasDoDia
){
    public static HorarioBaseDto toDto(HorarioBase base) {
        return new HorarioBaseDto(
                base.getId(),
                base.getSemestre().getId(),
                base.getAulasDoDia().stream().map(AulaDoDiaDto::toDto).toList()
        );
    }

}