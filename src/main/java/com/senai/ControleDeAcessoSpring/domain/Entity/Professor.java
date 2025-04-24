package com.senai.ControleDeAcessoSpring.domain.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Professor extends Usuario{

    @Column(nullable = false)
    private String turma;

    @CollectionTable(name = "curso_professores",joinColumns = @JoinColumn(name = "professor_id"))
    @ElementCollection(targetClass = Cursos.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "cursos")
    private List<Cursos> cursos;
}
