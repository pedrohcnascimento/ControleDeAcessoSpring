package com.senai.ControleDeAcessoSpring.aplication.dto;

import java.util.List;

public record TurmaDto(
        Long id,
        List<ProfessorDto> professores,
        List<AlunoDto> alunos,
        Cursos curso
) {
}