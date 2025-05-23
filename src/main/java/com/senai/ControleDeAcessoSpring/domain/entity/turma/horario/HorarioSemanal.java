package com.senai.ControleDeAcessoSpring.domain.entity.turma.horario;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.Semestre;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
public class HorarioSemanal extends HorarioBase {
    private LocalDate semanaReferencia;

    @ManyToOne
    @JoinColumn(name = "semestre_id")
    private Semestre semestre;
}