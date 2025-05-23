package com.senai.ControleDeAcessoSpring.domain.entity.curso;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Professor;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class UnidadeCurricular {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer cargaHorariaTotal;

    @ManyToOne
    private Curso curso;

    @ManyToMany
    private List<Professor> professores;

    public UnidadeCurricular(String nome, Integer cargaHoraria){
        this.nome = nome;
        this.cargaHorariaTotal = cargaHoraria;
    }
}