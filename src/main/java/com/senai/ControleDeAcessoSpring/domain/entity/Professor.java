package com.senai.ControleDeAcessoSpring.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import com.senai.ControleDeAcessoSpring.domain.entity.Usuario;
import com.senai.ControleDeAcessoSpring.domain.entity.Turma;
import com.senai.ControleDeAcessoSpring.domain.entity.UnidadeCurricular;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Professor extends Usuario {

    @OneToMany
    private List<Turma> turma;

    @OneToMany
    private List<UnidadeCurricular> unidadesCurriculares;

    @ManyToOne
    private Coordenador coordenador;
}
