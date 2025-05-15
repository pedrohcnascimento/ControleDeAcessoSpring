package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.AQV;

import java.time.LocalDate;

public record AqvDto (
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email
){
    public static AqvDto toDTO(AQV aqv) {
        return new AqvDto(aqv.getId(), aqv.getNome(), aqv.getCpf(), aqv.getDataNascimento(), aqv.getEmail());
    }

    public AQV fromDTO() {
        AQV aqv = new AQV();
        aqv.setId(id);
        aqv.setNome(nome);
        aqv.setCpf(cpf);
        aqv.setEmail(email);
        aqv.setDataNascimento(dataNascimento);
        aqv.setAtivo(true);
        aqv.setIdAcesso("");
        aqv.setSenha("");
        return aqv;
    }
}
