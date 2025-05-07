package com.senai.ControleDeAcessoSpring.domain.entity.usuarios;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("AQV")
public class AQV extends Usuario{
}
