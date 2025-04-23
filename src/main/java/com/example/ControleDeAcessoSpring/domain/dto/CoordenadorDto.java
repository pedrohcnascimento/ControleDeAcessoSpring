package com.example.ControleDeAcessoSpring.domain.dto;

import com.example.ControleDeAcessoSpring.domain.entity.Professor;

import java.util.List;

public record CoordenadorDto(Long id,
                             String nome,
                             Integer idade,
                             List<Professor> equipeDeProfessores) {
}
