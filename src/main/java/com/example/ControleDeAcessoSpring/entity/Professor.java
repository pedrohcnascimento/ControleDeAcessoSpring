package com.example.ControleDeAcessoSpring.entity;

public class Professor extends Usuario{

    public Professor(Integer id, Integer idAcesso, String nome, Integer cpf, String email, String imagem) {
        super(id, idAcesso, nome, cpf, email, imagem);
        this.cargo = "Professor";
    }
}
