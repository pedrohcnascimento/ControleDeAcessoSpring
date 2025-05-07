package com.senai.ControleDeAcessoSpring.domain.entity.curso;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.Turma;
import com.senai.ControleDeAcessoSpring.domain.enuns.Periodo;
import com.senai.ControleDeAcessoSpring.domain.enuns.TipoDeCurso;
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
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @OneToMany(mappedBy = "curso")
    private List<Turma> turmas;

    @OneToMany(mappedBy = "curso")
    private List<UnidadeCurricular> unidadesCurriculares;

    @Column
    private Long cargaHoraria;

    @Enumerated(EnumType.STRING)
    @Column
    private TipoDeCurso tipo;

    @Column
    private Integer qtdDeSemestres;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Periodo periodo;

    @Column(nullable = false)
    private Integer toleranciaEmMinutos;
}