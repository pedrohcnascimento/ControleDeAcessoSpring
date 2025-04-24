package com.senai.ControleDeAcessoSpring.aplication.dto;

import java.util.Date;
import java.util.List;

public record ProfessorDto(
        Long id,
        Long idAcesso,
        String nome,
        String email,
        String telefone,
        Date dataDeNascimento,
        List<CursoDto> cursos,
        List<TurmaDto> turmas
        ) {
}
