package com.senai.ControleDeAcessoSpring.aplication.dto;

public record RespostaApiDto(
        String mensagem,
        boolean sucesso,
        Object dados
) {

}
