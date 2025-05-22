package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.curso.Curso;
import com.senai.ControleDeAcessoSpring.domain.enums.TipoDeCurso;

import java.util.List;

public record CursoDto(
        Long id,
        String titulo,
        TipoDeCurso tipo,
        Integer cargaHoraria,
        Integer toleranciaMinutos,
        List<UnidadeCurricularDto> unidadesCurriculares
) {
    public static CursoDto fromDto(Curso curso) {
        return new CursoDto()
    }
}