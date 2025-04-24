package com.example.ControleDeAcessoSpring.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "professores_da_turma",
            joinColumns = @JoinColumn(name = "turma_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private List<Professor> professores;

    @Column
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "alunos_da_turma",
            joinColumns = @JoinColumn(name = "turma_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private List<Aluno> alunos;

    @Column(nullable = false)
    private Curso curso;

    @Column(nullable = false)
    private Date dataDeInicio;

    @Column(nullable = false)
    private Time horarioDeEntrada;
}
