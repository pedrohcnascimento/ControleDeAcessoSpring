package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.curso.Curso;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.UnidadeCurricular;
import com.senai.ControleDeAcessoSpring.domain.enums.TipoDeCurso;

import java.util.List;

public record CursoDto (
        Long id,
        String titulo,
        TipoDeCurso tipoDeCurso,
        Integer cargaHoraria,
        Integer tolerancia,
        List<UnidadeCurricularDto> unidadesCuricularesDto
) {
    public static CursoDto toDto(Curso curso) {
        return new CursoDto(
                curso.getId(),
                curso.getTitulo(),
                curso.getTipoDeCurso(),
                curso.getCargaHoraria(),
                curso.getToleranciaMinutos(),
                curso.getUnidadesCurriculares()
                        .stream()
                        .map(UnidadeCurricularDto::toDto)
                        .toList()
        );
    }

    public Curso fromDto() {
        Curso curso = new Curso();
        curso.setTitulo(this.titulo);
        curso.setTipoDeCurso(this.tipoDeCurso);
        curso.setCargaHoraria(this.cargaHoraria);
        curso.setToleranciaMinutos(this.tolerancia);

        List<UnidadeCurricular> ucs = this.unidadesCuricularesDto
                .stream()
                .map(dto -> dto.fromDto(curso))
                .toList();

        curso.setUnidadesCurriculares(ucs);

        return curso;
    }
}