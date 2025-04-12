package controller;

import controller.services.AlunoService;
import model.entity.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {
    @Autowired
    private AlunoService servico;

    @PostMapping
    public ResponseEntity<Aluno> adicionarAluno(@RequestBody Aluno obj) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servico.create(obj));
    }

    @GetMapping
    public List<Aluno> listarAlunos() {
        return servico.getAll();
    }

    @DeleteMapping(value = "/id")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.noContent().build();
    }
}
