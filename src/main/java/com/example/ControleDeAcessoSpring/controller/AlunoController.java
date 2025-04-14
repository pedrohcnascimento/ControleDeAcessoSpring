package com.example.ControleDeAcessoSpring.controller;

import com.example.ControleDeAcessoSpring.model.entity.Aluno;
import com.example.ControleDeAcessoSpring.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoService service;

    @PostMapping
    public Aluno adicionarAluno(@RequestBody Aluno obj) {
        return service.create(obj);
    }

    @GetMapping
    public List<Aluno> listarAlunos() {
        return service.getAll();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
