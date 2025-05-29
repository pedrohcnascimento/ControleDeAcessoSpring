package com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;

import java.time.LocalDate;
import java.util.ArrayList;

public record AlunoDto(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email,
        String senha
) {
    public static AlunoDto toDTO(Aluno a) {
        return new AlunoDto(a.getId(), a.getNome(), a.getCpf(), a.getDataNascimento(), a.getEmail(), a.getSenha());
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
        aluno.setSenha(senha);
        aluno.setJustificativas(new ArrayList<>());
        aluno.setOcorrencias(new ArrayList<>());
        aluno.setSubTurmas(new ArrayList<>());
        return aluno;
    }
}
