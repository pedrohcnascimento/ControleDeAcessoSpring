package com.example.ControleDeAcessoSpring.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String curso;

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
}
