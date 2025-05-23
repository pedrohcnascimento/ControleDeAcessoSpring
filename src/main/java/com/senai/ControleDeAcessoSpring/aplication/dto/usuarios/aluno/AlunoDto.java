package com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;

import java.time.LocalDate;
import java.util.ArrayList;

public record AlunoDto(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email
) {
    public static AlunoDto toDTO(Aluno a) {
        return new AlunoDto(a.getId(), a.getNome(), a.getCpf(), a.getDataNascimento(), a.getEmail());
    }

    public Aluno fromDTO() {
        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setNome(nome);
        aluno.setCpf(cpf);
        aluno.setEmail(email);
        aluno.setDataNascimento(dataNascimento);
        aluno.setAtivo(true);
        aluno.setIdAcesso("");
        aluno.setSenha("");
        aluno.setJustificativas(new ArrayList<>());
        aluno.setOcorrencias(new ArrayList<>());
        aluno.setSubTurmas(new ArrayList<>());
        return aluno;
    }
}
