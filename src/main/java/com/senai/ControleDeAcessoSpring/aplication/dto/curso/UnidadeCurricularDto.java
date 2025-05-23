package com.senai.ControleDeAcessoSpring.aplication.dto.curso;

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

    public UnidadeCurricular fromDto() {
        UnidadeCurricular uc = new UnidadeCurricular();

        uc.setId(id);
        uc.setNome(nome);
        uc.setCargaHorariaTotal(cargaHorariaTotal);

        return uc;
    }
}
