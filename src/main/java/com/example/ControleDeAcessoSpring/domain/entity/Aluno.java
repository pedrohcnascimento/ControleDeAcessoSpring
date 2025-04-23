package com.example.ControleDeAcessoSpring.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aluno extends Usuario{

    @Column
    @JoinColumn(name = "turma_id")
    private Turma turma;
}
