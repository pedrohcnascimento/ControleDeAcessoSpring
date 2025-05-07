package com.senai.ControleDeAcessoSpring.domain.entity.turma.horarios;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.SubTurma;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class HorarioBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "sub_turma_id", nullable = false, unique = true)
    protected SubTurma subTurma;

    @OneToMany(mappedBy = "horario", cascade = CascadeType.ALL, orphanRemoval = true)
    protected List<AulasDoDia> diasDaSemana;
}
