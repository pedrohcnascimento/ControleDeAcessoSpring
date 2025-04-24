package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.Entity.Cursos;

import java.util.Date;
import java.util.List;

public record ProfessorDto(
        Long id,
        Long idAcesso,
        String nome,
        String email,
        String telefone,
        Date dataDeNascimento,
        List<Cursos> cursos,
        List<TurmaDto> turmas
        ) {
}
