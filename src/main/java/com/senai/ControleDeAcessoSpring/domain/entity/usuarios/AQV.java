package com.senai.ControleDeAcessoSpring.domain.entity.usuarios;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("aqv")
public class AQV extends Usuario{
}
