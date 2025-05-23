package com.senai.ControleDeAcessoSpring.domain.entity.usuarios;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("AQV")
public class AQV extends Usuario{

}