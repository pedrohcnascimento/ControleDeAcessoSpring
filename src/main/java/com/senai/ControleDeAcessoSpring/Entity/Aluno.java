package com.senai.ControleDeAcessoSpring.Entity;

public class Aluno extends Usuario{

    public Aluno(Integer id, Integer idAcesso, String nome, String email, String imagem) {
        super(id, idAcesso, nome, email, imagem, "Aluno");
    }
}
