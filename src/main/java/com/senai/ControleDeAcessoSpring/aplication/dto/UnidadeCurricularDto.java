package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.curso.UnidadeCurricular;

public record UnidadeCurricularDto(
        Long id,
        String nome,
        Integer cargaHorariaTotal,
        Integer cargaHorariaPorSemestre
) {
}
