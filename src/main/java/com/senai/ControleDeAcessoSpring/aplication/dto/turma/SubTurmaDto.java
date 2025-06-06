package com.senai.ControleDeAcessoSpring.aplication.dto.turma;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.SubTurma;

public record SubTurmaDto(
        String nome,
        Boolean status,
        Long idTurma
)
{
    public static SubTurmaDto toDto(SubTurma subTurma) {
        return new SubTurmaDto(
                subTurma.getNome(),
                subTurma.getStatus(),
                subTurma.getTurma().getId()
        );
    }

    public SubTurma fromDto(SubTurmaDto dto) {
        SubTurma sub = new SubTurma();

        sub.setNome(nome);

        return sub;
    }
}