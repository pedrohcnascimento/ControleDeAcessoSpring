package com.example.ControleDeAcessoSpring.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DiaDaSemana {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @JoinTable(
            name = "UnidadesCurriculares",
            joinColumns = @JoinColumn(name = "DiaDaSemana"),
            inverseJoinColumns = @JoinColumn(name = "UnidadeCurricular_id")
    )
    private List<UnidadeCurricular> Ucs;
}
