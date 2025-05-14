package com.senai.ControleDeAcessoSpring.inteface.Controller;

import com.senai.ControleDeAcessoSpring.aplication.dto.TurmaDto;
import com.senai.ControleDeAcessoSpring.aplication.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @PostMapping
    public ResponseEntity<Void> cadastrarTurma(@RequestBody TurmaDto turmaDto){
        turmaService.cadastrarTurma(turmaDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<TurmaDto>> listarTurmas(){
        return ResponseEntity.ok(turmaService.listarTurmas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaDto> buscarPorId(@PathVariable Long id){
        return turmaService.buscarTurmaPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarUsuario(@PathVariable Long id, @RequestBody TurmaDto turmaDto){
        if (!turmaService.atualizarTurma(id, turmaDto)) {
            return ResponseEntity.badRequest().body("Falha ao atualizar");
        }
        return ResponseEntity.ok("Atualizado com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarTurma(@PathVariable Long id){
        if (turmaService.deletarTurma(id)) {
            return ResponseEntity.ok("Usuario deletado com sucesso");
        }
        return ResponseEntity.notFound().build();
    }

}
