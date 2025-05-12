package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.enums.TipoDeCurso;

import java.util.List;

public record CursoDto(
        Long id,
        String titulo,
        TipoDeCurso tipo,
        Integer cargaHoraria,
        Integer toleranciaMinutos,
        List<UnidadeCurricularDto> unidadeCurriculares
) {
}
