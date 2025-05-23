package com.senai.ControleDeAcessoSpring.inteface.controller;

import com.senai.ControleDeAcessoSpring.aplication.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ocorrencias")
public class OcorrenciaController {

    @Autowired
    private OcorrenciaService ocorrenciaService;

    public void criarOcorrenciaAtraso(String idAcesso){
        ocorrenciaService.criarOcorrenciaAtraso(idAcesso);
    }
}