package com.senai.ControleDeAcessoSpring.domain.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Coordenador extends Usuario{

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "coordenador_equipe",
            joinColumns = @JoinColumn(name = "coordenador_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private List<Professor> equipeProfessores;
}
