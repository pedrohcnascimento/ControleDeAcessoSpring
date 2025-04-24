package com.example.ControleDeAcessoSpring.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AQV extends Usuario{

    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "gerador_id_acesso")
    @TableGenerator(
            name = "gerador_id_acesso",
            table = "id_generator",
            pkColumnName = "idUsuario",
            valueColumnName = "value_column",
            pkColumnValue = "",
            allocationSize = 1
    )
    Long idAcesso;
}
