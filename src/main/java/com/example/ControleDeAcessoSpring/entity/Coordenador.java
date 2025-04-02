package com.example.ControleDeAcessoSpring.entity;

public class Coordenador extends Usuario{

    public Coordenador(Integer id, Integer idAcesso, String nome, Integer cpf, String email, String imagem) {
        super(id, idAcesso, nome, cpf, email, imagem);
        this.cargo = "Coordenador";
    }
}
