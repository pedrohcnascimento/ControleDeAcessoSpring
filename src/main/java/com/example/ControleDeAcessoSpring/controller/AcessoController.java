package com.example.ControleDeAcessoSpring.controller;

import com.example.ControleDeAcessoSpring.model.entity.Acesso;
import com.example.ControleDeAcessoSpring.services.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acesso")
public class AcessoController {
    @Autowired
    private AcessoService service;

    @PostMapping
    public Acesso adicionarAcesso(@RequestBody Acesso obj) {
        return service.create(obj);
    }

    @GetMapping
    public List<Acesso> listarAcessos() {
        return service.getAll();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
