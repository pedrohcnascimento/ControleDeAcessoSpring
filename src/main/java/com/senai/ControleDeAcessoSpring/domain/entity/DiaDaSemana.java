package com.senai.ControleDeAcessoSpring.domain.entity;

import com.senai.ControleDeAcessoSpring.domain.entity.enums.NomesDiaDaSemana;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DiaDaSemana {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NomesDiaDaSemana nome;

    @OneToMany
    private List<UnidadeCurricular> Ucs;

    @ManyToOne
    @JoinColumn
    private Horario horario;
}
