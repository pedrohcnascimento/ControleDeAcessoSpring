package com.senai.ControleDeAcessoSpring.domain.entity.turma;

import com.senai.ControleDeAcessoSpring.domain.entity.curso.Curso;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String periodo;
    private LocalDate dataInicial;
    private LocalTime horarioEntrada;
    private Integer qtdSemestres;
    private Integer qtdAulasporDia;

    @ManyToOne
    private Curso curso;

    @OneToMany
    private List<SubTurma> subTurmas;
}
