package com.example.ControleDeAcessoSpring.entity;

import java.util.List;

public abstract class Usuario {
    protected Integer id;
    protected Integer idAcesso;
    protected String nome;
    protected Integer cpf;
    protected String email;
    protected String imagem;
    protected String cargo;

    public Usuario(Integer id, Integer idAcesso, String nome, Integer cpf, String email, String imagem) {
        this.id = id;
        this.idAcesso = idAcesso;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.imagem = imagem;
    }

    public Integer getCpf() {
        return cpf;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdAcesso() {
        return idAcesso;
    }

    public void setIdAcesso(Integer idAcesso) {
        this.idAcesso = idAcesso;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    protected void autoBuscar(List lista){
        System.out.println(lista.get(this.id));
    }

}
