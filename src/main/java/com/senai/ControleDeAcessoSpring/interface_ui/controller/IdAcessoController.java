package com.senai.ControleDeAcessoSpring.interface_ui.controller;

import com.senai.ControleDeAcessoSpring.aplication.service.usuarios.IdAcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/idAcesso")
public class IdAcessoController {

    @Autowired
    private IdAcessoService idAcessoService;

    private String rota = "";

    private Long idUsuario;

    @PostMapping("/{id}")
    public void cadastrarIdAcesso(@PathVariable Long id) {
        rota = "cadastrar";
        this.idUsuario = id;
    }

    public void direcionarIdAcessoRecebido(String idAcesso) {
        System.out.println("id recebido pelo controller:"+idAcesso);
        idAcessoService.direcionarIdAcessoRecebido(idAcesso, rota, idUsuario);
        rota = "";
    }

    @PutMapping("/{id}")
    public void atualizarIdAcesso(@PathVariable Long id) {
        rota = "atualizar";
        this.idUsuario = id;
    }
}
