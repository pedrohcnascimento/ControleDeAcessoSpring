package com.senai.ControleDeAcessoSpring.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@DiscriminatorValue("aluno")
public class Aluno extends Usuario{

    @OneToMany
    private List<Ocorrencia> ocorrencia;

    @OneToMany
    private List<Justificativa> justificativas;

}