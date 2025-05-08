package com.senai.ControleDeAcessoSpring.aplication.dto;

public record UnidadeCurricularDto(
        Long id,
        String nome,
        Integer cargaHorariaTotal,
        Integer cargarHorariaPorSemestre
) {
}
