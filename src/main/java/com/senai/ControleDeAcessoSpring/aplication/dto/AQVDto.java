package com.senai.ControleDeAcessoSpring.aplication.dto;

public record AQVDto(Long id,
                     Long idAcesso,
                     String nome,
                     String email,
                     String telefone,
                     String cargo,
                     String foto
                     ) {
}
