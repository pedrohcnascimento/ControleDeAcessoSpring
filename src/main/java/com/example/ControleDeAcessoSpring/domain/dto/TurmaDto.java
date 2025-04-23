package com.example.ControleDeAcessoSpring.domain.dto;

import com.example.ControleDeAcessoSpring.domain.entity.Aluno;
import com.example.ControleDeAcessoSpring.domain.entity.Professor;

import java.util.List;

public record TurmaDto(
        Long id,
        List<Professor> professores,
        List<Aluno> alunos,
        String curso
) {
}
