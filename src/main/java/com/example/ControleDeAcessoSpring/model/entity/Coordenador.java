package com.example.ControleDeAcessoSpring.model.entity;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Coordenador extends Usuario{

    private List<String> equipeDeProfessores;
}
