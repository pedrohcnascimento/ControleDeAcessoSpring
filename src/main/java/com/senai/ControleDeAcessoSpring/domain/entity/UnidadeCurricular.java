package com.senai.ControleDeAcessoSpring.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class UnidadeCurricular {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer cargaHoraria;

    @ManyToMany
    @JoinTable(
            name = "uc_dia_da_semana",
            joinColumns = @JoinColumn(name = "uc_id"),
            inverseJoinColumns = @JoinColumn(name = "dia_id")
    )
    private List<DiaDaSemana> diasDaSemana;

    @OneToMany(mappedBy = "unidadesCurriculares")
    private List<Professor> professor;

    @ManyToOne
    private Curso curso;

    @OneToOne
    private Aula aula;
}
