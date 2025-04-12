package com.example.ControleDeAcessoSpring.controller;

import com.example.ControleDeAcessoSpring.model.entity.Professor;
import com.example.ControleDeAcessoSpring.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    private ProfessorService service;

    @PostMapping
    public Professor adicionarAluno(@RequestBody Professor obj) {
        return service.create(obj);
    }

    @GetMapping
    public List<Professor> listarAlunos() {
        return service.getAll();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
