package com.senai.ControleDeAcessoSpring.Entity;

public class Professor extends Usuario{

    public Professor(Integer id, Integer idAcesso, String nome, String email, String imagem) {
        super(id, idAcesso, nome, email, imagem,"Professor");
    }
}
