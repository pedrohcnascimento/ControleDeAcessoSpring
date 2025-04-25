package com.senai.ControleDeAcessoSpring.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coordenador extends Usuario{

    @ManyToOne
    private List<Professor> equipeDeProfessores;
}
