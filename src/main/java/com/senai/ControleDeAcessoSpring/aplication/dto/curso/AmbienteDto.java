package com.senai.ControleDeAcessoSpring.aplication.dto.curso;

import com.senai.ControleDeAcessoSpring.domain.entity.curso.Ambiente;

public record AmbienteDto(
        Long id,
        String nome,
        Boolean ativo
) {
    public static AmbienteDto fromDto(Ambiente ambiente) {
        return new AmbienteDto(
                ambiente.getId(),
                ambiente.getNome(),
                ambiente.getAtivo()
        );
    }

    public Ambiente toDto() {
        Ambiente ambiente = new Ambiente();

        ambiente.setId(id);
        ambiente.setNome(nome);
        ambiente.setAtivo(ativo);

        return ambiente;
    }
}
