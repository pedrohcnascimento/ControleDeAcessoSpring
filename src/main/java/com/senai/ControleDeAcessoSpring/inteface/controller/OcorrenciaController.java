package com.senai.ControleDeAcessoSpring.inteface.controller;

import com.senai.ControleDeAcessoSpring.aplication.dto.OcorrenciaDto;
import com.senai.ControleDeAcessoSpring.aplication.service.OcorrenciaService;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaOcorrencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ocorrencias")
public class OcorrenciaController {

    @Autowired
    private OcorrenciaService ocorrenciaService;

    public void criarOcorrenciaAtraso(String idAcesso){
        ocorrenciaService.criarOcorrenciaAtraso(idAcesso);
    }
}