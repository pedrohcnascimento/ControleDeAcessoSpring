package com.example.ControleDeAcessoSpring.model.dto;

import com.example.ControleDeAcessoSpring.model.entity.UnidadeCurricular;

public record ProfessorDto(Long id, String nome, Integer idade, String turma, UnidadeCurricular unidadeCurricular) {
}
