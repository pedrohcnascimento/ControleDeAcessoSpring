package com.example.ControleDeAcessoSpring.model.dto;

import com.example.ControleDeAcessoSpring.model.entity.Aluno;
import com.example.ControleDeAcessoSpring.model.entity.Professor;

import java.util.List;

public record TurmaDto(
        Long id,
        List<Professor> professores,
        List<Aluno> alunos,
        String curso
) {
}
