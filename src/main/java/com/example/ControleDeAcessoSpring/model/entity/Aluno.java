package com.example.ControleDeAcessoSpring.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Entity
@Data
public class Aluno extends Usuario{

    @Column
    @JoinColumn(name = "turma_id")
    private Turma turma;
}
