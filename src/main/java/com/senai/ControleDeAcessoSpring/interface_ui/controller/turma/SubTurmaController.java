package com.senai.ControleDeAcessoSpring.interface_ui.controller.turma;

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

    @PostMapping("/{idTurma}")
    public ResponseEntity<String> cadastrarSubTurma(@PathVariable Long turmaId) {
        service.criarSubTurma(turmaId);
        return ResponseEntity.ok("Sub Turma criada com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubTurmaDto> buscarPorID(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarSubTurma(@PathVariable Long id, @RequestBody SubTurmaDto subTurmaDto) {
        service.atualizar(id, subTurmaDto);
        return ResponseEntity.ok("Sub Turma atualizada!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarSubTurma(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok("Sub Turma deletada com sucesso!");
    }
}
