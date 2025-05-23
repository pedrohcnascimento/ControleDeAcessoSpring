package com.senai.ControleDeAcessoSpring.aplication.dto.curso;

import com.senai.ControleDeAcessoSpring.domain.entity.curso.Curso;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.UnidadeCurricular;
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
    public static CursoDto toDto(Curso curso) {
        return new CursoDto(
                curso.getId(),
                curso.getTitulo(),
                curso.getTipo(),
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

        curso.setId(id);
        curso.setTitulo(titulo);
        curso.setTipo(tipo);
        curso.setCargaHoraria(cargaHoraria);
        curso.setToleranciaMinutos(toleranciaMinutos);
        curso.setUnidadesCurriculares(unidadesCurriculares
                .stream()
                .map(uc -> new UnidadeCurricular(
                        uc.nome(),
                        uc.cargaHorariaTotal()
                ))
                .toList()
        );

        return curso;
    }
}