package com.senai.ControleDeAcessoSpring.aplication.dto.curso;

import com.senai.ControleDeAcessoSpring.domain.entity.curso.Ambiente;

public record AmbienteDto(
        Long id,
        String nome
) {
    public static AmbienteDto toDto(Ambiente ambiente) {
        return new AmbienteDto(
                ambiente.getId(),
                ambiente.getNome()
        );
    }

    public Ambiente fromDto() {
        Ambiente ambiente = new Ambiente();

        ambiente.setId(id);
        ambiente.setNome(nome);
        ambiente.setAtivo(true);

        return ambiente;
    }
}