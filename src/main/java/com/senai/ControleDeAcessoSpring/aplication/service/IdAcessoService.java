package com.senai.ControleDeAcessoSpring.aplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdAcessoService {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    OcorrenciaService ocorrenciaService;

    public void direcionarIdAcessoRecebido(String idAcesso, String rota, Long idUsuario) {
        System.out.println("Teste"+rota);
        if (rota.equals("cadastrar")) {
            usuarioService.cadastrarIdAcesso(idUsuario, idAcesso);
        } else if (rota.equals("atualizar")) {
            usuarioService.atualizarIdAcesso(idUsuario, idAcesso);
        } else {
            System.out.println("[Ocorrencias] id recebido pelo service:"+idAcesso);
            ocorrenciaService.criarOcorrenciaAtraso(idAcesso);
        }
    }
}
