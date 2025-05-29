package com.senai.ControleDeAcessoSpring.aplication.dto.usuarios;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.AQV;

import java.time.LocalDate;

public record AQVDto(
        Long id,
        String nome,
        String cpf,
<<<<<<<< HEAD:src/main/java/com/senai/ControleDeAcessoSpring/aplication/dto/AQVDto.java
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
========
        LocalDate dataNascimento,
        String email,
        String senha
){
    public static AqvDto toDTO(AQV aqv) {
        return new AqvDto(aqv.getId(), aqv.getNome(), aqv.getCpf(), aqv.getDataNascimento(), aqv.getEmail(), aqv.getSenha());
>>>>>>>> master:src/main/java/com/senai/ControleDeAcessoSpring/aplication/dto/usuarios/AqvDto.java
    }

    public AQV fromDto(){
        AQV aqv = new AQV();
        aqv.setId(id);
        aqv.setNome(nome);
        aqv.setCpf(cpf);
        aqv.setEmail(email);
        aqv.setDataNascimento(dataNascimento);
        aqv.setAtivo(true);
<<<<<<<< HEAD:src/main/java/com/senai/ControleDeAcessoSpring/aplication/dto/AQVDto.java
========
        aqv.setIdAcesso("");
        aqv.setSenha(senha);
>>>>>>>> master:src/main/java/com/senai/ControleDeAcessoSpring/aplication/dto/usuarios/AqvDto.java
        return aqv;
    }
}