package com.senai.ControleDeAcessoSpring.aplication.dto.curso;

import com.senai.ControleDeAcessoSpring.domain.entity.curso.Curso;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.UnidadeCurricular;

public record UnidadeCurricularDto(
        Long id,
        String nome,
        Integer cargaHorariaTotal
) {
    public static UnidadeCurricularDto toDto(UnidadeCurricular unidade) {
        return new UnidadeCurricularDto(
                unidade.getId(),
                unidade.getNome(),
                unidade.getCargaHorariaTotal()
        );
    }

    public UnidadeCurricular fromDto(Curso curso) {
        UnidadeCurricular uc = new UnidadeCurricular();

        uc.setNome(nome);
        uc.setCargaHorariaTotal(cargaHorariaTotal);
        uc.setCurso(curso);

        return uc;
    }
}