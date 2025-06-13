package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.curso.Ambiente;
import lombok.Data;

@Data
public class AmbienteDTO {

private Long id;
private String nome;
private String descricao;
private boolean ativo = true;

public Ambiente toEntity() {
    Ambiente ambiente = new Ambiente();
    ambiente.setId(this.id);
    ambiente.setNome(this.nome);
    ambiente.setDescricao(this.descricao);
    ambiente.setAtivo(this.ativo);
    return ambiente;
}

public static AmbienteDTO fromEntity(Ambiente ambiente) {
    AmbienteDTO dto = new AmbienteDTO();
    dto.setId(ambiente.getId());
    dto.setNome(ambiente.getNome());
    dto.setDescricao(ambiente.getDescricao());
    dto.setAtivo(ambiente.isAtivo());
    return dto;
}

public String getNome() {
    return this.nome;
}

public String getDescricao() {
    return this.descricao;
}

public void setId(Long id) {
    this.id = id;
}

public void setNome(String nome) {
    this.nome = nome;
}

public void setDescricao(String descricao) {
    this.descricao = descricao;
}

public void setAtivo(boolean ativo) {
    this.ativo = ativo;
}
} ;;