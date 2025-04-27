package com.senai.ControleDeAcessoSpring.domain.entity;

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

    @OneToMany(mappedBy = "turma")
    private List<SubTurma> subTurmas;

    @OneToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @Column(nullable = false)
    private Date dataDeInicio;

    @Column(nullable = false)
    private Time horarioDeEntrada;
}
