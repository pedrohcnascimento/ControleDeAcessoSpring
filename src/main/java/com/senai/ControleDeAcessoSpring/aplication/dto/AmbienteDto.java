package com.senai.ControleDeAcessoSpring.aplication.dto;

package com.seusistema.controleacesso.ambiente.dto;

import com.seusistema.controleacesso.ambiente.model.Ambiente;
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
}
