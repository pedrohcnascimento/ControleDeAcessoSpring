package com.senai.ControleDeAcessoSpring.aplication.dto.turma.horario;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.HorarioPadrao;

import java.util.List;

public record HorarioPadraoDto(
    Long id,
    List<AulaDoDiaDto> listaAulasDoDia
    )
{
    public HorarioPadraoDto fromDto(HorarioPadrao padrao) {
        return new HorarioPadraoDto(
                padrao.getId(),
                padrao.getAulasDoDia().stream().map(AulaDoDiaDto::toDto).toList()
        );
    }

    public HorarioPadrao toDto() {
        HorarioPadrao padrao = new HorarioPadrao();

        padrao.setId(id);
        padrao.setAulasDoDia(listaAulasDoDia.stream().map(AulaDoDiaDto::fromDto).toList());

        return padrao;
    }
}
