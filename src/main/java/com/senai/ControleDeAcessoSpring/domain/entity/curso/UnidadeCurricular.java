package com.senai.ControleDeAcessoSpring.domain.entity.curso;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Professor;
import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

@Entity
public class UnidadeCurricular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer cargaHorariaTotal;

    @ElementCollection
    private Map<Integer, Integer> cargaHorariaPorSemestre;

    @ManyToOne
    private Curso curso;

    @ManyToMany
    private List<Professor> professores;
}
