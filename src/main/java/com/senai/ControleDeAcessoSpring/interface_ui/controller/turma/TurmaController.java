package com.senai.ControleDeAcessoSpring.interface_ui.controller.turma;

import com.senai.ControleDeAcessoSpring.aplication.dto.turma.TurmaDto;
import com.senai.ControleDeAcessoSpring.aplication.service.turma.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService service;

    @PostMapping
    public ResponseEntity<String> cadastrarTurma(@RequestBody TurmaDto turma) {
        service.cadastrarTurma(turma);
        return ResponseEntity.ok("Turma adicionada com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaDto> acharTurmaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id).get());
    }

    @GetMapping
    public ResponseEntity<List<TurmaDto>> listarTurmas() {
        return ResponseEntity.ok(service.listarTurmas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> desativarTurma(@PathVariable Long id) {
        if (service.deletarTurma(id)) {
            return ResponseEntity.ok("Turma desativada com exito!");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarTurma(@PathVariable Long id, @RequestBody TurmaDto novaTurma) {
        if (service.atualizarTurma(id, novaTurma)) {
            return ResponseEntity.ok("Turma atualizada com exito");
        }
        return ResponseEntity.notFound().build();
    }
}