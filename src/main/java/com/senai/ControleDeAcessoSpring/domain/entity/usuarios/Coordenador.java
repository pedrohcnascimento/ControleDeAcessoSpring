package com.senai.ControleDeAcessoSpring.domain.entity.usuarios;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("coordenador")
public class Coordenador extends Usuario {

    @OneToMany(mappedBy = "coordenador")
    private List<Professor> equipeDeProfessores;
}
