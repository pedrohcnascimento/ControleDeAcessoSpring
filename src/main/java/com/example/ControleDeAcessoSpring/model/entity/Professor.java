package com.example.ControleDeAcessoSpring.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Professor extends Usuario {

    @Column(nullable = false)
    @JoinTable(
            name = "turmas",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "turma_id")
    )
    private List<Turma> turma;

    @Column(nullable = false)
    private List<UnidadeCurricular> unidadesCurriculares;
}
