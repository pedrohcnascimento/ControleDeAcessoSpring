package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Coordenador;

import java.time.LocalDate;

public record CoordenadorDto(
        Long id,
        String nome,
        String cpf,
        String email,
        LocalDate dataNascimento
) {
    public static CoordenadorDto toDto(Coordenador c) {
        return new CoordenadorDto(
                c.getId(),
                c.getNome(),
                c.getCpf(),
                c.getEmail(),
                c.getDataNascimento()
        );
    }

    public Coordenador fromDto() {
        Coordenador coordenador = new Coordenador();
        coordenador.setId(id);
        coordenador.setNome(nome);
        coordenador.setCpf(cpf);
        coordenador.setEmail(email);
        coordenador.setDataNascimento(dataNascimento);
        coordenador.setAtivo(true);
        coordenador.setIdAcesso("");
        coordenador.setSenha("");
        return coordenador;
    }
}