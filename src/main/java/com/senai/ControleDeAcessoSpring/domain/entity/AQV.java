package com.senai.ControleDeAcessoSpring.domain.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("aqv")
public class AQV extends Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
