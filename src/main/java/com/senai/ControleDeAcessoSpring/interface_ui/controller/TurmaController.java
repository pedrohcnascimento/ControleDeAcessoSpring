package com.senai.ControleDeAcessoSpring.interface_ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody TurmaDto turmaDto) {
        turmaService.salvar(turmaDto);
        return ResponseEntity.ok("Turma cadastrada com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<TurmaDto>> listarTurmas() {
        return ResponseEntity.ok(turmaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaDto> buscarPorId(@PathVariable Long id) {
        return turmaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id,
                                            @RequestBody TurmaDto turmaDto
                                            ) {
        if (turmaService.salvar(id, turmaDto)) {
            return ResponseEntity.ok("Turma atualizada!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        if (turmaService.deletar(id)) {
            return ResponseEntity.ok("Turma deletada");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
