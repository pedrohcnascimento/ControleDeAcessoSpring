package com.senai.ControleDeAcessoSpring.domain.entity.curso;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Professor;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class UnidadeCurricular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer cargaHorariaTotal;
    private List<Integer> cargaHorariaPorSemestre;

    @ManyToOne
    private Curso curso;

    @ManyToMany
    private List<Professor> professores;

    @Column(name = "ativo")
    private boolean ativo;

}
