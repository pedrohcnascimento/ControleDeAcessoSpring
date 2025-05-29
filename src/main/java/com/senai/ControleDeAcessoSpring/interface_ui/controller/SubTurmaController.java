package com.senai.ControleDeAcessoSpring.interface_ui.controller;

import com.senai.ControleDeAcessoSpring.aplication.dto.turma.SubTurmaDto;
import com.senai.ControleDeAcessoSpring.aplication.service.turma.SubTurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subTurma")
public class SubTurmaController {
    @Autowired
    private SubTurmaService service;

    @GetMapping
    public ResponseEntity<List<SubTurmaDto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<String> cadastrarSubTurma(@RequestBody SubTurmaDto subTurma) {
        service.cadastrarSubTurma(subTurma);
        return ResponseEntity.ok("Sub Turma criada com sucesso!");
    }

    @PostMapping("/{id}")
    public ResponseEntity<SubTurmaDto> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarSubTurma(@PathVariable Long id, @RequestBody SubTurmaDto subTurmaDto) {
        service.atualizarSubTurma(id, subTurmaDto);
        return ResponseEntity.ok("Sub Turma atualizada!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarSubTurma(@PathVariable Long id) {
        service.deletarSubTurma(id);
        return ResponseEntity.ok("Sub Turma deletada com sucesso!");
    }
}
