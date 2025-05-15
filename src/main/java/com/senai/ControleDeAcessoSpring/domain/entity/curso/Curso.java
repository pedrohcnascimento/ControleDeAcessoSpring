package com.senai.ControleDeAcessoSpring.domain.entity.curso;

import com.senai.ControleDeAcessoSpring.domain.enums.TipoDeCurso;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Enumerated(EnumType.STRING)
    private TipoDeCurso tipo;

    private Integer cargaHoraria;
    private Integer toleranciaMinutos;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UnidadeCurricular> unidadesCurriculares;
}
