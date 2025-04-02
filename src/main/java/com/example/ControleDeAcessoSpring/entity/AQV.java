package com.example.ControleDeAcessoSpring.entity;

public class AQV extends Usuario{

    public AQV(Integer id, Integer idAcesso, String nome, Integer cpf, String email, String imagem) {
        super(id, idAcesso, nome, cpf, email, imagem);
        this.cargo = "AQV";
    }
}
