package com.senai.ControleDeAcessoSpring.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubTurma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinTable(
            name = "Alunos",
            joinColumns = @JoinColumn(name = "Subturma_id"),
            inverseJoinColumns = @JoinColumn(name = "Aluno_id")
    )
    private List<Aluno> alunos;

    @Column(nullable = false)
    private Horario horario;
}
