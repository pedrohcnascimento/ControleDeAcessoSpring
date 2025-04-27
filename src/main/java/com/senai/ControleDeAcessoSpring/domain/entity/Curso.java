package com.senai.ControleDeAcessoSpring.domain.entity;

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

    @Column(nullable = false)
    private String nome;

    @OneToMany
    private List<UnidadeCurricular> unidadesCurriculares;

    @Column(nullable = false)
    private Long cargaHoraria;

    @Column(nullable = false)
    private TipoDeCurso tipo;

    @Column(nullable = false)
    private Integer qtdDeSemestres;

    @Column(nullable = false)
    private Periodo periodo;

    @Column(nullable = false)
    private Integer toleranciaEmMinutos;
}