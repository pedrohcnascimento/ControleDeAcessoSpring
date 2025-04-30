package com.senai.ControleDeAcessoSpring.domain.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("coordenador")
public class Coordenador extends Usuario {
}