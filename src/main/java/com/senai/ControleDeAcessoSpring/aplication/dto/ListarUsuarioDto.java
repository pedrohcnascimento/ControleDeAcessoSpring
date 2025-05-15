package com.senai.ControleDeAcessoSpring.aplication.dto;

import java.time.LocalDate;

public record ListarUsuarioDto(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email
        ) {
}
