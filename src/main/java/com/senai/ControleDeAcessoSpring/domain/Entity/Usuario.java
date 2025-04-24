package com.senai.ControleDeAcessoSpring.domain.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5)
    private Long idAcesso;
    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 13)
    private String telefone;
    @Column(length = 25)
    private String cargo;
    @Column
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
