package com.senai.ControleDeAcessoSpring.inteface.Controller;

import com.senai.ControleDeAcessoSpring.aplication.dto.RespostaApiDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.CursoDto;
import com.senai.ControleDeAcessoSpring.aplication.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<Object> cadastrarCurso(@RequestBody CursoDto cursoDto) {
        cursoService.cadastrarCurso(cursoDto);
        RespostaApiDto resposta = new RespostaApiDto("Curso cadastrado com sucesso!", true, cursoDto);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping
    public ResponseEntity<Object> listarCursos() {
        List<CursoDto> cursos = cursoService.listarCursos();
        RespostaApiDto resposta = new RespostaApiDto("Cursos listados com sucesso!", true, cursos);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespostaApiDto> buscarCursoPorId(@PathVariable Long id) {
        return cursoService.buscarCursoPorId(id)
                .map(curso -> {
                    RespostaApiDto resposta = new RespostaApiDto("Curso encontrado com sucesso!", true, curso);
                    return ResponseEntity.ok(resposta);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new RespostaApiDto("Curso não encontrado.", false, null)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarCurso(@PathVariable Long id, @RequestBody CursoDto cursoDto) {
        if (cursoService.atualizarCurso(id, cursoDto)) {
            RespostaApiDto resposta = new RespostaApiDto("Curso atualizado com sucesso!", true, cursoDto);
            return ResponseEntity.ok(resposta);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new RespostaApiDto("Curso não encontrado para atualização.", false, null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarCurso(@PathVariable Long id) {
        if (cursoService.deletarCurso(id)) {
            RespostaApiDto resposta = new RespostaApiDto("Curso excluído com sucesso!", true, null);
            return ResponseEntity.ok(resposta);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new RespostaApiDto("Curso não encontrado para exclusão.", false, null));
    }
}