package com.senai.ControleDeAcessoSpring.interface_ui.controller.turma;

import com.senai.ControleDeAcessoSpring.aplication.dto.turma.SubTurmaDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.AlunoDto;
import com.senai.ControleDeAcessoSpring.aplication.service.turma.SubTurmaService;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
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
    
    @PutMapping("/{id}/alunos")
    public ResponseEntity<AlunoDto> adicionarAlunos(@PathVariable Long id, @RequestBody AlunoDto alunoDto) {
        AlunoDto resposta = service.adicionarAluno(id, alunoDto);

        if (resposta == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}/alunos")
    public ResponseEntity<Boolean> deletarAluno(@PathVariable Long idSubTurma, @RequestBody Long idAluno) {
        if (service.removerAluno(idSubTurma, idAluno)) {
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarSubTurma(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok("Sub Turma deletada com sucesso!");
    }
}
