package com.senai.ControleDeAcessoSpring.aplication.dto.turma.horario;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.Aula;

public record AulaDto(
        Long id,
        Integer ordem,
        Long unidadeCurricularId,
        Long professorId,
        Long ambienteId
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
        return new Aula();
    }
}
