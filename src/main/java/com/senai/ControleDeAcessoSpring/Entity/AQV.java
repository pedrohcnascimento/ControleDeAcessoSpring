package com.senai.ControleDeAcessoSpring.Entity;

public class AQV extends Usuario{

    public AQV(Integer id, Integer idAcesso, String nome, String email, String imagem) {
        super(id, idAcesso, nome, email, imagem, "AQV");
    }
}
