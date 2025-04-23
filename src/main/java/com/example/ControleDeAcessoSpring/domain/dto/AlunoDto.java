package com.example.ControleDeAcessoSpring.domain.dto;

import com.example.ControleDeAcessoSpring.domain.entity.Turma;

public record AlunoDto(Long id,
                       String nome,
                       Integer idade,
                       Turma turma) {
}
