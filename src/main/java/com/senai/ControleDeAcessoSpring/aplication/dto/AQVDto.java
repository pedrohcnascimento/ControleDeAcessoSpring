package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.AQV;

import java.time.LocalDate;

public record AQVDto(
        Long id,
        String nome,
        String cpf,
        String email,
        LocalDate dataNascimento
){
    public static AQVDto toDto(AQV a) {
        return new AQVDto(
                a.getId(),
                a.getNome(),
                a.getCpf(),
                a.getEmail(),
                a.getDataNascimento()
        );
    }

    public AQV fromDto(){
        AQV aqv = new AQV();
        aqv.setId(id);
        aqv.setNome(nome);
        aqv.setCpf(cpf);
        aqv.setEmail(email);
        aqv.setDataNascimento(dataNascimento);
        aqv.setAtivo(true);
        return aqv;
    }
}