package com.senai.ControleDeAcessoSpring.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("professor")
public class Professor extends Usuario {

    @OneToMany
    private List<Turma> turma;

    @OneToMany
    private List<UnidadeCurricular> unidadesCurriculares;

    @ManyToOne
    private Coordenador coordenador;

    @OneToOne(mappedBy = "professor")
    private Aula aula;
}
