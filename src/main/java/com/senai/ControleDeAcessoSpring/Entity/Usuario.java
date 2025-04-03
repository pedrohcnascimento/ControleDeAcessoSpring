package com.senai.ControleDeAcessoSpring.Entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class Usuario {
    private Integer id;
    private Integer idAcesso;
    private String nome;
    private String email;
    private String telefone;
    private String cargo;
    private String foto;

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", idAcesso=" + idAcesso +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", imagem='" + foto + '\'' +
                '}';
    }

}
