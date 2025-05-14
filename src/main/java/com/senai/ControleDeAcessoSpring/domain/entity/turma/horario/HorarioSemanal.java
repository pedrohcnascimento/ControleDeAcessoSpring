package com.senai.ControleDeAcessoSpring.domain.entity.turma.horario;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HorarioSemanal extends HorarioBase{
    private LocalDate semanaReferencia;
}
