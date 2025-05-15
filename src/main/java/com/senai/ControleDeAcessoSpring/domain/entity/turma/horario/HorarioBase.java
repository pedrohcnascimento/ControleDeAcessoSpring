package com.senai.ControleDeAcessoSpring.domain.entity.turma.horario;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.SubTurma;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class HorarioBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sub_turma_id", nullable = false, unique = true)
    protected SubTurma subTurma;

    @OneToMany(mappedBy = "horario", cascade = CascadeType.ALL, orphanRemoval = true)
    protected List<AulaDoDia> aulasDoDia;
}
