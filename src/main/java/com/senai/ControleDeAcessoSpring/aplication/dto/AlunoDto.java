package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;

import java.time.LocalDate;
import java.util.ArrayList;

public record AlunoDto(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String idAcesso,
        String email
){
    public static AlunoDto toDto(Aluno a){
        return new AlunoDto(
                a.getId(),
                a.getNome(),
                a.getCpf(),
                a.getDataNascimento(),
                a.getIdAcesso(),
                a.getEmail()
        );
    }

    public Aluno fromDto(){
        Aluno a = new Aluno();
        a.setId(id);
        a.setNome(nome);
        a.setCpf(cpf);
        a.setEmail(email);
        a.setDataNascimento(dataNascimento);
        a.setAtivo(true);
        a.setIdAcesso("");
        a.setSenha("");
        a.setJustificativas(new ArrayList<>());
        a.setOcorrencias(new ArrayList<>());
        return a;
    }
}