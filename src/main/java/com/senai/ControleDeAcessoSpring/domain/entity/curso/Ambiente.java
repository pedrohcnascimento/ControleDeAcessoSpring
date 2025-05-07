package com.senai.ControleDeAcessoSpring.domain.entity.curso;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.Aula;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ambiente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "ambiente")
    private Aula aula;

    private String nome;
}
