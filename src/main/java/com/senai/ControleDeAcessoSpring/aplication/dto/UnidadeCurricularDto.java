package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.curso.Curso;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.UnidadeCurricular;

public record UnidadeCurricularDto(
        Long id,
        String nome,
        Integer cargaHoraria
) {

    public static UnidadeCurricularDto toDto(UnidadeCurricular uc) {
        return new UnidadeCurricularDto(
                uc.getId(),
                uc.getNome(),
                uc.getCargaHorariaTotal()
        );
    }

    public UnidadeCurricular fromDto(Curso curso) {
        UnidadeCurricular uc = new UnidadeCurricular();
        uc.setNome(this.nome);
        uc.setCargaHorariaTotal(this.cargaHoraria);
        uc.setCurso(curso);
        return uc;
    }
}