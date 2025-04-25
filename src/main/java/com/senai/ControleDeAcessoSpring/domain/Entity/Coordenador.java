package com.senai.ControleDeAcessoSpring.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coordenador extends Usuario{

    @Column
    @JoinTable(
            name = "equipe_de_professores",
            joinColumns = @JoinColumn(name = "coordenador_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private List<Professor> equipeDeProfessores;
}
