package com.example.ControleDeAcessoSpring.interface_ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acessos")
public class AcessoController {

    @Autowired
    private AcessoService acessoService;

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody AcessoDto acessoDto) {
        acessoService.salvar(acessoDto);
        return ResponseEntity.ok("Acesso registrado");
    }

    @GetMapping
    public ResponseEntity<List<AcessoDto>> listarAcessos() {
        return ResponseEntity.ok(acessoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcessoDto> buscarPorId(@PathVariable Long id) {
        return acessoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
