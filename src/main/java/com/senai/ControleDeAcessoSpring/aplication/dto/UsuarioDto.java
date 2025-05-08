package com.senai.ControleDeAcessoSpring.aplication.dto;


import com.senai.ControleDeAcessoSpring.domain.enums.TipoDeUsuario;

import java.time.LocalDate;

public record UsuarioDto(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email,
        TipoDeUsuario tipoDeUsuario
) {
}
