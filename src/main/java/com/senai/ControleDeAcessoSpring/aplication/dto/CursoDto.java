package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.TipoCurso;

import java.util.List;

public record CursoDto(String nome,
                       List<UnidadeCurricularDto> unidadesCurriculares,
                       Long cargaHoraria,
                       TipoCurso tipoCurso,
                       int qtdSemestres,
                       String periodo,
                       int toleranciaAtraso) {
}
