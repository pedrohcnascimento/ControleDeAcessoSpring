package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.horarios.HorarioPadrao;

public record SubTurmaDto(
        Long id,
        String nome,
        HorarioPadrao horarioPadrao
) {
}
