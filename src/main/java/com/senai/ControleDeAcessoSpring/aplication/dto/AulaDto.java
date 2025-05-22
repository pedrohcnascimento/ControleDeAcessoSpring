package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.Aula;

public record AulaDto(
        Long id,
        Integer ordem,
        Long unidadeCurricularId,
        Long professorId,
        Long ambientId
) {
    public static AulaDto toDto(Aula a) {
        return new AulaDto(
                a.getId(),
                a.getOrdem(),
                a.getUnidadeCurricular() != null ? a.getUnidadeCurricular().getId() : null,
                a.getProfessor() != null ? a.getProfessor().getId() : null,
                a.getAmbiente() != null ? a.getAmbiente().getId() : null
        );
    }

    public Aula fromDto() {
        Aula aula = new Aula();
        return aula;
    }
}
