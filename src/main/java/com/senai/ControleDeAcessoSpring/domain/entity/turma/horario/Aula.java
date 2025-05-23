package com.senai.ControleDeAcessoSpring.domain.entity.turma.horario;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Professor;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.Ambiente;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.UnidadeCurricular;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ordem;

    @ManyToOne
    private AulasDoDia aulasDia;

    @ManyToOne
    private UnidadeCurricular unidadeCurricular;

    @ManyToOne
    private Professor professor;

    @ManyToOne
    private Ambiente ambiente;
}