package com.example.ControleDeAcessoSpring.domain.dto;

import com.example.ControleDeAcessoSpring.domain.entity.UnidadeCurricular;

public record ProfessorDto(Long id, String nome, Integer idade, String turma, UnidadeCurricular unidadeCurricular) {
}
