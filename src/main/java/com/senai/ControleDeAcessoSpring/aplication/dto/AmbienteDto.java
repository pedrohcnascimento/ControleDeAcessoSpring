package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.curso.Ambiente;

public record AmbienteDto(
        Long id,
        String nome,
        boolean ativo
) {
    public static AmbienteDto toDTO(Ambiente a) {
        return new AmbienteDto(
                a.getId(),
                a.getNome(),
                a.isAtivo()
        );
    }
    public Ambiente fromDTO() {
        return new Ambiente(
                id,
                nome,
                true
        );
    }
}
