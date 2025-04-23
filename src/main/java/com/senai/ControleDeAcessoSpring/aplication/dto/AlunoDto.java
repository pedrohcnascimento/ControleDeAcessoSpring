package com.senai.ControleDeAcessoSpring.aplication.dto;

public record AlunoDto(Long id,
                       Long idAcesso,
                       String nome,
                       String email,
                       String telefone,
                       String cargo,
                       String foto) {
}
