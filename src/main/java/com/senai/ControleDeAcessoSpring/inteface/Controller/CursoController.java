package com.senai.ControleDeAcessoSpring.inteface.Controller;

import com.senai.ControleDeAcessoSpring.aplication.dto.CursoDto;
import com.senai.ControleDeAcessoSpring.aplication.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<Void> cadastrarCurso(@RequestBody CursoDto cursoDto) {
        cursoService.cadastrarCurso(cursoDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CursoDto>> listarCursos() {
        return ResponseEntity.ok(cursoService.listarCursos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDto> buscarCursoPorId(@PathVariable Long id) {
        return cursoService.buscarCursoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarCurso(@PathVariable Long id, @RequestBody CursoDto cursoDto) {
        if (cursoService.atualizarCurso(id, cursoDto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCurso(@PathVariable Long id) {
        if (cursoService.deletarCurso(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}