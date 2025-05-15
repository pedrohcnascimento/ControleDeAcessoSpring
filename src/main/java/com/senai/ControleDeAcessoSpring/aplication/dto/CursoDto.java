package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.enuns.TipoDeCurso;

import java.util.List;
import java.util.stream.Collectors;

import static com.senai.ControleDeAcessoSpring.domain.enums.TipoDeCurso.*;

public record CursoDto(
        Long id,
        String titulo,
        TipoDeCurso tipo,
        Integer cargaHoraria,
        Integer toleranciaMinutos,
        List<UnidadeCurricularDto> unidadeCurriculares
) {
}