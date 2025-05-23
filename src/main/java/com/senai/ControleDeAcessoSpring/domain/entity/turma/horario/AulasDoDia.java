package com.senai.ControleDeAcessoSpring.domain.entity.turma.horario;

import com.senai.ControleDeAcessoSpring.domain.enums.DiasDaSemana;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
public class AulasDoDia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DiasDaSemana diaDaSemana;

    @ManyToOne
    @JoinColumn(name = "horario_id")
    private HorarioBase horario;

    @OneToMany(mappedBy = "aulasDia", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderColumn(name = "ordem")
    private List<Aula> aulas;
}