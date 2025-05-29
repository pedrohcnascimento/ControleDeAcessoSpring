package com.senai.ControleDeAcessoSpring.aplication.dto.usuarios;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.AQV;

import java.time.LocalDate;

public record AQVDto(
        Long id,
        String nome,
        String cpf,
        String email,
        LocalDate dataNascimento,
        String senha
){
    public static AQVDto toDTO(AQV aqv) {
        return new AQVDto(aqv.getId(), aqv.getNome(), aqv.getCpf(),aqv.getEmail(),  aqv.getDataNascimento(), aqv.getSenha());
    }

    public AQV fromDto(){
        AQV aqv = new AQV();
        aqv.setId(id);
        aqv.setNome(nome);
        aqv.setCpf(cpf);
        aqv.setEmail(email);
        aqv.setDataNascimento(dataNascimento);
        aqv.setAtivo(true);
        aqv.setIdAcesso("");
        aqv.setSenha(senha);
        return aqv;
    }
}