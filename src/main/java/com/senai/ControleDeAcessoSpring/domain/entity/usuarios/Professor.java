package com.senai.ControleDeAcessoSpring.domain.entity.usuarios;

import com.senai.ControleDeAcessoSpring.domain.entity.curso.UnidadeCurricular;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.Turma;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.Aula;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("PROFESSOR")
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
