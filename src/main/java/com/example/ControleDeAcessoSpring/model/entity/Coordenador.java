package com.example.ControleDeAcessoSpring.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Coordenador extends Usuario{

    @Column
    @JoinTable(
            name = "equipe_de_professores",
            joinColumns = @JoinColumn(name = "coordenador_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private List<Professor> equipeDeProfessores;
}
