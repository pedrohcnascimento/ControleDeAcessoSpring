package com.example.ControleDeAcessoSpring.controller;

import com.example.ControleDeAcessoSpring.model.entity.Coordenador;
import com.example.ControleDeAcessoSpring.services.CoordenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coordenador")
public class CoordenadorController {
    @Autowired
    private CoordenadorService service;

    @PostMapping
    public Coordenador adicionarCoordenador(@RequestBody Coordenador obj) {
        return service.create(obj);
    }

    @GetMapping
    public List<Coordenador> listarCoordenadores() {
        return service.getAll();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
