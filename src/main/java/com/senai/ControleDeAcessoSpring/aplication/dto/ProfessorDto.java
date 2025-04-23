package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.Entity.Cursos;

import java.util.List;

public record ProfessorDto(
        Long id,
        Long idAcesso,
        String nome,
        String email,
        String telefone,
        String cargo,
        String turma,
        String foto,
        List<Cursos> cursos
        ) {
}
