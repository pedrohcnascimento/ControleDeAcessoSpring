package com.senai.ControleDeAcessoSpring.domain.entity.turma.horario;

import com.senai.ControleDeAcessoSpring.domain.enums.DiaDaSemana;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AulaDoDia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DiaDaSemana diaDaSemana;

    @OneToMany(mappedBy = "aulasDoDia", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderColumn(name = "ordem")
    private List<Aula> aulas;

    @ManyToOne
    @JoinColumn(name = "horario_id")
    private HorarioBase horario;
}
