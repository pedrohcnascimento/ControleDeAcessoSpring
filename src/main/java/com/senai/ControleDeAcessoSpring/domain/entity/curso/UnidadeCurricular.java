package com.senai.ControleDeAcessoSpring.domain.entity.curso;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.Aula;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.AulaDoDia;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Professor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Entity
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class UnidadeCurricular {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer cargaHoraria;

    @ElementCollection
    private Map<Integer, Integer> cargaHorariaPorSemestre;

    @ManyToMany
    private List<AulaDoDia> diasDaSemana;

    @OneToMany(mappedBy = "unidadesCurriculares")
    private List<Professor> professor;

    @ManyToOne
    private Curso curso;

    @OneToOne(mappedBy = "uc")
    private Aula aula;
}
