package com.senai.ControleDeAcessoSpring.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Professor extends Usuario {

    @ManyToOne
    private List<Turma> turma;

    @ManyToOne
    @JoinColumn(name = "coordenador_id")
    private List<UnidadeCurricular> unidadesCurriculares;
}
