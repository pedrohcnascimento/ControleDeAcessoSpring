package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.curso.UnidadeCurricular;
import java.util.List;

public record UnidadeCurricularDto(
        Long id,
        String nome,
        Integer cargaHorariaTotal,
        List<Integer> cargaHorariaPorSemestre,
        Long idCurso
) {

    public static UnidadeCurricularDto toDto(UnidadeCurricular u) {
        return new UnidadeCurricularDto(
                u.getId(),
                u.getNome(),
                u.getCargaHorariaTotal(),
                u.getCargaHorariaPorSemestre(),
                u.getCurso().getId()
        );
    }

    public UnidadeCurricular fromDto() {
        UnidadeCurricular u = new UnidadeCurricular();
        u.setId(this.id);
        u.setNome(this.nome);
        u.setCargaHorariaTotal(this.cargaHorariaTotal);
        u.setCargaHorariaPorSemestre(this.cargaHorariaPorSemestre);
        return u;
    }
}
