package com.senai.ControleDeAcessoSpring.Entity;

public abstract class Usuario {
    private Integer id;
    private Integer idAcesso;
    private String nome;
    private String email;
    private String cargo;
    private String imagem;

    public Usuario(Integer id, Integer idAcesso, String nome, String email, String imagem, String cargo) {
        this.id = id;
        this.idAcesso = idAcesso;
        this.nome = nome;
        this.email = email;
        this.imagem = imagem;
        this.cargo = cargo;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", idAcesso=" + idAcesso +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", imagem='" + imagem + '\'' +
                '}';
    }

}
