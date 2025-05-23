package com.senai.ControleDeAcessoSpring.aplication.dto;


import com.senai.ControleDeAcessoSpring.domain.entity.turma.SubTurma;

public record SubTurmaDto(
        Long id,
        String nome,
        Long turmaId
) {
    public static SubTurmaDto toDto(SubTurma s) {
        return new SubTurmaDto(
                s.getId(),
                s.getNome(),
                s.getTurma().getId()
        );
    }

    public  SubTurma fromDto() {
        SubTurma subTurma = new SubTurma();
        subTurma.setNome(nome);
        return subTurma;
    }
}