package com.senai.ControleDeAcessoSpring.interface_ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody CursoDto cursoDto) {
        cursoService.salvar(cursoDto);
        return ResponseEntity.ok("Curso cadastrado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<CUrsoDto>> listarCursos() {
        return ResponseEntity.ok(cursoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDto> buscarPorId(@PathVariable Long id) {
        return cursoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id,
                                            @RequestBody CursoDto cursoDto
    ) {
        if (cursoService.salvar(id, cursoDto)) {
            return ResponseEntity.ok("Curso atualizado!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        if (cursoService.deletar(id)) {
            return ResponseEntity.ok("Curso deletado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
