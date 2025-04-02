package com.example.ControleDeAcessoSpring.entity;

public class Aluno extends Usuario{

    public Aluno(Integer id, Integer idAcesso, String nome, Integer cpf, String email, String imagem) {
        super(id, idAcesso, nome, cpf, email, imagem);
        this.cargo = "Aluno";
    }
}
