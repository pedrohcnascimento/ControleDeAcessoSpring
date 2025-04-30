package com.senai.ControleDeAcessoSpring.domain.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("professor")
public class Professor extends Usuario {
}