package com.senai.ControleDeAcessoSpring.domain.entity.usuarios;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("PROFESSOR")
public class Professor extends Usuario {
}
