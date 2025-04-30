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

    @Column
    private String nome;

    @OneToMany(mappedBy = "turma")
    private List<SubTurma> subTurmas;

    @ManyToOne
    @JoinColumn
    private Curso curso;

    @Column
    private Date dataDeInicio;

    @Column
    private Time horarioDeEntrada;
}
