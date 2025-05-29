package com.senai.ControleDeAcessoSpring.domain.entity.turma.horario;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.Semestre;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "semestre_id")
    private Semestre semestre;
}
