package com.senai.ControleDeAcessoSpring.aplication.dto.turma;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.SubTurma;

public record SubTurmaDto(
        String nome,
        Boolean status,
        Long turmaId
)
{
    public static SubTurmaDto fromDto(SubTurma subTurma) {
        return new SubTurmaDto(
                subTurma.getNome(),
                subTurma.getStatus(),
                subTurma.getTurma().getId()
        );
    }

    public SubTurma toDto(SubTurmaDto dto) {
        SubTurma sub = new SubTurma();

        sub.setNome(nome);

        return sub;
    }
}
