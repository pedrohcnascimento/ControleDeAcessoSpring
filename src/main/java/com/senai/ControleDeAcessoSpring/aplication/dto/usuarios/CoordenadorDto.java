package com.senai.ControleDeAcessoSpring.aplication.dto.usuarios;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Coordenador;

import java.time.LocalDate;

public record CoordenadorDto(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email,
        String senha
) {
    public static CoordenadorDto toDTO(Coordenador c) {
        return new CoordenadorDto(c.getId(), c.getNome(), c.getCpf(), c.getDataNascimento(), c.getEmail(), c.getSenha());
    }

    public Coordenador fromDTO() {
        Coordenador coordenador = new Coordenador();
        coordenador.setId(id);
        coordenador.setNome(nome);
        coordenador.setCpf(cpf);
        coordenador.setEmail(email);
        coordenador.setDataNascimento(dataNascimento);
        coordenador.setAtivo(true);
        coordenador.setIdAcesso("");
        coordenador.setSenha(senha);
        return coordenador;
    }
}
