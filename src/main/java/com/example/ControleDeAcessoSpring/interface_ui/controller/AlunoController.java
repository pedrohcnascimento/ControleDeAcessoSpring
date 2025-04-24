package com.example.ControleDeAcessoSpring.interface_ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody AlunoDto alunoDto) {
        alunoService.salvar(alunoDto);
        return ResponseEntity.ok("Aluno salvo com sucesso");
    }

    @GetMapping
    public ResponseEntity<List<AlunoDto>> listarAlunos() {
        return ResponseEntity.ok(alunoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDto> buscarPorId(@PathVariable Long id) {
        return alunoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Só uma ideia
//    @GetMapping("/{id}/{idOcorrencia}")
//    public ResponseEntity<OcorrenciaDto> buscarPorId(@PathVariable Long id, @PathVariable Long idOcorrencia) {
//    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id,
                                            @RequestBody AlunoDto alunoDto) {
        if (alunoService.atualizar(id, alunoDto)) {
            return ResponseEntity.ok("Aluno atualizado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        if (alunoService.deletar(id)) {
            return ResponseEntity.ok("Aluno deletado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
