package com.senai.ControleDeAcessoSpring.domain.entity.turma.horario;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Professor;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.Ambiente;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.UnidadeCurricular;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Professor professor;

    @ManyToOne
    private UnidadeCurricular UnidadeCurricular;

    @ManyToOne
    private Ambiente ambiente;

    @ManyToOne
    private AulaDoDia aulasDoDia;
}
