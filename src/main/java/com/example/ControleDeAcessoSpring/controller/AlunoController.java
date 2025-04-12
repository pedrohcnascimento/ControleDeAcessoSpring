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
    private AlunoService servico;

    @PostMapping
    public Aluno adicionarAluno(@RequestBody Aluno obj) {
        return servico.create(obj);
    }

    @GetMapping
    public List<Aluno> listarAlunos() {
        return servico.getAll();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.noContent().build();
    }
}
