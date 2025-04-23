package com.senai.ControleDeAcessoSpring.aplication.dto;

import java.util.List;

public record CoordenadorDto(
        Long id,
        Long idAcesso,
        String nome,
        String email,
        String telefone,
        String cargo,
        String foto,
        List<ProfessorDto> equipe)
{
}
