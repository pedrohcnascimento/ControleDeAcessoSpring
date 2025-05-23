package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.HorarioBase;

import java.util.List;

public record HorarioBaseDto(
        Long id,
        Long semestreId,
        List<AulasDoDiaDto> listaDeAulasDoDia
) {
    public static HorarioBaseDto toDto(HorarioBase h) {
        return new HorarioBaseDto(
                h.getId(),
                h.getSemestre() != null ? h.getSemestre().getId() : null,
                h.getListaDeAulasDoDia().stream().map(AulasDoDiaDto::toDto).toList()
        );
    }
}
