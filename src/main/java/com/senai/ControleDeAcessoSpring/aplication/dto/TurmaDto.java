package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.Entity.Cursos;

import java.util.List;

public record TurmaDto(
        Long id,
        List<ProfessorDto> professores,
        List<AlunoDto> alunos,
        Cursos curso
) {
}