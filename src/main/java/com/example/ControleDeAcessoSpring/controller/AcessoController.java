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
    private AcessoService servico;

    @PostMapping
    public Acesso adicionarAluno(@RequestBody Acesso obj) {
        return servico.create(obj);
    }

    @GetMapping
    public List<Acesso> listarAlunos() {
        return servico.getAll();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.noContent().build();
    }
}
