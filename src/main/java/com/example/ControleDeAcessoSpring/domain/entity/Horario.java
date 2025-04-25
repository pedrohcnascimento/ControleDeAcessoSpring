package com.example.ControleDeAcessoSpring.domain.entity;

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
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinTable(
            name = "dias_da_semana",
            joinColumns = @JoinColumn(name = "Horario_id"),
            inverseJoinColumns = @JoinColumn(name = "DiaDaSemana_id")
    )
    private List<DiaDaSemana> diasDaSemana;
}
