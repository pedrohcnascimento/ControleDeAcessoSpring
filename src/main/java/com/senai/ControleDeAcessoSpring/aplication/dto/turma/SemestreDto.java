package com.senai.ControleDeAcessoSpring.aplication.dto.turma;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.Semestre;

public record SemestreDto(
        Long id,
        Integer numero,
        String nomeDaTurma
) {
    public static SemestreDto toDto(Semestre semestre) {
        return new SemestreDto(
                semestre.getId(),
                semestre.getNumero(),
                semestre.getNomeDaTurma()
        );
    }

    public Semestre fromDto() {
        Semestre semestre = new Semestre();

        semestre.setId(id);
        semestre.setNumero(numero);
        semestre.setNomeDaTurma(nomeDaTurma);

        return semestre;
    }
}