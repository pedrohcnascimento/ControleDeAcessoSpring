package com.senai.ControleDeAcessoSpring.domain.entity.turma.horario;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class HorarioPadrao extends HorarioBase{
    @OneToOne
    private Semestre
}
