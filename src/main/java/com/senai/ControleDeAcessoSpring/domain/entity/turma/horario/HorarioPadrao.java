package com.senai.ControleDeAcessoSpring.domain.entity.turma.horario;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.Semestre;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class HorarioPadrao extends HorarioBase {
    @OneToOne(mappedBy = "horarioPadrao")
    private Semestre semestre;
}