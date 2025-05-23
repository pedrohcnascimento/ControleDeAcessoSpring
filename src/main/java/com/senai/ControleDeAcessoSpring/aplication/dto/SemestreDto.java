package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.Semestre;

public record SemestreDto (
        Long id,
        int numero,
        String nomeDaTurma
) {
    public static SemestreDto toDto(Semestre semestre) {
        return new SemestreDto(
                semestre.getId(),
                semestre.getNumero(),
                semestre.getNomeDaTurma()
        );
    }
}