package com.senai.ControleDeAcessoSpring.aplication.dto;

import com.senai.ControleDeAcessoSpring.domain.entity.StatusJustificativa;
import com.senai.ControleDeAcessoSpring.domain.entity.TipoOcorrencia;

import java.util.Date;

public record OcorrenciaDto (String justificativa,
                             Date dataEHora,
                             StatusJustificativa status,
                             TipoOcorrencia tipoOcorrencia){
}
