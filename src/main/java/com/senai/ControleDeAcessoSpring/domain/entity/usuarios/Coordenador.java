package com.senai.ControleDeAcessoSpring.domain.entity.usuarios;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@DiscriminatorValue("COORDENADOR")
public class Coordenador extends Usuario {
}
