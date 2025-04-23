package com.example.ControleDeAcessoSpring.domain.dto;

import com.example.ControleDeAcessoSpring.domain.entity.Turma;
import com.example.ControleDeAcessoSpring.domain.entity.UnidadeCurricular;

import java.util.List;

public record ProfessorDto(Long id,
                           String nome,
                           Integer idade,
                           List<Turma> turmas,
                           UnidadeCurricular unidadeCurricular) {
}
