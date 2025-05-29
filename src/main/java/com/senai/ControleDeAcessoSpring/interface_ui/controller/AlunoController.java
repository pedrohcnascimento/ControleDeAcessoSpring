package com.senai.ControleDeAcessoSpring.interface_ui.controller;

import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.AlunoDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.JustificativaDto;
import com.senai.ControleDeAcessoSpring.aplication.service.usuarios.aluno.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Void> cadastrarUsuario(@RequestBody AlunoDto dto) {
        alunoService.cadastrarAluno(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDto> buscarPorId(@PathVariable Long id) {
        return alunoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AlunoDto>> listarAtivos() {
        return ResponseEntity.ok(alunoService.listarAtivos());
    }

    @GetMapping("/{id}/justificativas")
    public ResponseEntity<List<JustificativaDto>> listarJustificativas(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.listarJustificativas(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody AlunoDto dto) {
        if (alunoService.atualizar(id, dto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/justificativas")
    public ResponseEntity<String> criarJustificativa(@PathVariable Long id, @RequestBody JustificativaDto justificativaDto) {
        if (alunoService.criarJustificativa(id, justificativaDto)) {
            return ResponseEntity.ok("Justificativa adicionada!");
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        if (alunoService.inativar(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
