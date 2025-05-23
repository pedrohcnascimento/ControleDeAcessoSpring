package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Professor;

import java.time.LocalDate;
import java.util.ArrayList;

public record ProfessorDto(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String idAcesso,
        String email
) {
    public static ProfessorDto toDto(Professor p) {
        return new ProfessorDto(
                p.getId(),
                p.getNome(),
                p.getCpf(),
                p.getDataNascimento(),
                p.getIdAcesso(),
                p.getEmail()
        );
    }
    public Professor fromDto() {
        return new Professor(
                id,
                nome,
                cpf,
                dataNascimento,
                idAcesso,
                email,
                "",
                true,
                new ArrayList<>()
        );
    }
}