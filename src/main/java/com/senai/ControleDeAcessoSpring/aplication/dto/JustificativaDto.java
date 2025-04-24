package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.Entity.TipoJustificativa;

import java.io.File;
import java.util.Date;

public record JustificativaDto(TipoJustificativa tipoJustificativa,
                               String descricao,
                               Date dataDeInicio,
                               int qtdDeDias,
                               File anexo,
                               boolean status) {
}
