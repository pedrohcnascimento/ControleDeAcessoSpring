package com.senai.ControleDeAcessoSpring.domain.entity.turma;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class SubTurma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    @OneToMany(mappedBy = "subTurma", cascade = CascadeType.ALL)
    private List<Aluno> alunos;

    @OneToMany(mappedBy = "subTurma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Semestre> semestres;
}