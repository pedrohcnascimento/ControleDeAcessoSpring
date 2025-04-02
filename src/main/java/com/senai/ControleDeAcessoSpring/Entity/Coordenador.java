package com.senai.ControleDeAcessoSpring.Entity;

public class Coordenador extends Usuario{

    public Coordenador(Integer id, Integer idAcesso, String nome, String email, String imagem) {
        super(id, idAcesso, nome, email, imagem, "Coordenador");
    }
}
