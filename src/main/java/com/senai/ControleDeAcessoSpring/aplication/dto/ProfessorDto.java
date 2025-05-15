package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Professor;

import java.time.LocalDate;

public record ProfessorDto(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email
) {
    public static ProfessorDto toDTO(Professor p) {
        return new ProfessorDto(p.getId(), p.getNome(), p.getCpf(), p.getDataNascimento(), p.getEmail());
    }

    public Professor fromDTO() {
        Professor professor = new Professor();
        professor.setId(id);
        professor.setNome(nome);
        professor.setCpf(cpf);
        professor.setEmail(email);
        professor.setDataNascimento(dataNascimento);
        professor.setAtivo(true);
        professor.setIdAcesso("");
        professor.setSenha("");
        return professor;
    }
}
