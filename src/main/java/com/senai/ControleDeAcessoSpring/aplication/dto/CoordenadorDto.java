package com.senai.ControleDeAcessoSpring.aplication.dto;

import java.util.Date;
import java.util.List;

public record CoordenadorDto(
        Long id,
        Long idAcesso,
        String nome,
        String email,
        String telefone,
        Date dataDeNascimento,
        List<ProfessorDto> equipe)
{
}
