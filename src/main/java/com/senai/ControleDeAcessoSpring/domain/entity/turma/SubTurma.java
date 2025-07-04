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

    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    @ManyToMany(mappedBy = "subTurmas")
    private List<Aluno> alunos;

    @OneToMany(mappedBy = "subTurma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Semestre> semestres;
}
