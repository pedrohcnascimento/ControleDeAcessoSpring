package com.senai.ControleDeAcessoSpring.aplication.dto;

import java.util.Date;

public record AQVDto(Long id,
                     Long idAcesso,
                     String nome,
                     String email,
                     String telefone,
                     Date dataDeNascimento
                     ) {
}
