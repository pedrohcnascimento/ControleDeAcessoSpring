package com.senai.ControleDeAcessoSpring.domain.entity.turma.horario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HorarioSemanal extends HorarioBase{
    private LocalDate semanaReferencia;
}
