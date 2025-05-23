package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.HorarioPadrao;

import java.util.List;

public record HorarioPadraoDto(
        Long id,
        List<AulasDoDiaDto> listaDeAulasDoDia
) {
    public static HorarioPadraoDto toDto(HorarioPadrao horario) {
        return new HorarioPadraoDto(
                horario.getId(),
                horario.getListaDeAulasDoDia().stream().map(AulasDoDiaDto::toDto).toList()
        );
    }

    public HorarioPadrao fromDto() {
        HorarioPadrao horario = new HorarioPadrao();
        horario.setListaDeAulasDoDia(
                listaDeAulasDoDia.stream().map(AulasDoDiaDto::fromDto).toList()
        );
        return horario;
    }
}
