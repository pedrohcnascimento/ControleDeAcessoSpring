package com.example.ControleDeAcessoSpring.entity;

import java.util.List;

public class Professor extends Usuario{
    private List<String> turmas;
    private List<String> materias;

    public Professor(Integer id, Integer idAcesso, String nome, Integer cpf, String email, String imagem) {
        super(id, idAcesso, nome, cpf, email, imagem);
        this.cargo = "Professor";
    }
}

