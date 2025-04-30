package com.senai.ControleDeAcessoSpring.domain.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("aqv")
public class AQV extends Usuario{
}
