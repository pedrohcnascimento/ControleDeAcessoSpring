package com.senai.ControleDeAcessoSpring.interface_ui.controller.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.AlunoDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.JustificativaDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.OcorrenciaDto;
import com.senai.ControleDeAcessoSpring.aplication.service.usuarios.aluno.AlunoService;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaJustificativa;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaOcorrencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    //INSERT INTO usuario(ativo, id_acesso, nome, cpf, data_nascimento, email, senha, tipo_usuario) VALUES(true, "", "Nome", 11111111111, "2000-01-01", "email.exemplo@teste.com", "senha", "ALUNO");

    @PostMapping
    public ResponseEntity<Void> cadastrarUsuario(@RequestBody List<AlunoDto> listaDtos) {
        alunoService.cadastrarAluno(listaDtos);
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

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody AlunoDto dto) {
        if (alunoService.atualizar(id, dto)) {
            return ResponseEntity.ok().build();
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

    // Justificativas
    @GetMapping("/{id}/justificativas")
    public ResponseEntity<List<JustificativaDto>> listarJustificativas(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.listarJustificativas(id));
    }

    @GetMapping("/{id}/justificativas/{idJustificativa}")
    public ResponseEntity<Optional<JustificativaDto>> listarJustificativaPorId(@PathVariable Long id, @PathVariable Long idJustificativa) {
        return ResponseEntity.ok(alunoService.listarJustificativaPorId(idJustificativa));
    }

    @PutMapping("/{id}/justificativas")
    public ResponseEntity<String> criarJustificativa(@PathVariable Long id, @RequestBody JustificativaDto justificativaDto) {
        if (alunoService.criarJustificativa(id, justificativaDto)) {
            return ResponseEntity.ok("Justificativa adicionada!");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/justificativas/{idJustificativa}")
    public ResponseEntity<String> alterarStatusJustificativa(@PathVariable Long id, @PathVariable Long idJustificativa,
                                                @RequestBody StatusDaJustificativa status) {
        if (alunoService.alterarStatusJustificativa(idJustificativa, status)) {
            return ResponseEntity.ok("Status alterado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/justificativas/{idJustificativa}")
    public ResponseEntity<String> inativarJustificativa(@PathVariable Long id, @PathVariable Long idJustificativa) {
        if (alunoService.inativarJustificativa(idJustificativa)) {
            return ResponseEntity.ok("Justificativa inativada");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Ocorrências Saída
    @GetMapping("/{id}/ocorrencias")
    public ResponseEntity<List<OcorrenciaDto>> listarOcorrencias(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.listarOcorrencias(id));
    }

    @GetMapping("/{id}/ocorrencias/{idOcorrencia}")
    public ResponseEntity<Optional<OcorrenciaDto>> listarOcorrenciaPorId(@PathVariable Long id, @PathVariable Long idOcorrencia) {
        return ResponseEntity.ok(alunoService.listarOcorrenciaPorId(idOcorrencia));
    }

    @PutMapping("/{id}/ocorrencias")
    public ResponseEntity<String> criarJustificativa(@PathVariable Long id, @RequestBody OcorrenciaDto ocorrenciaDto) {
        if (alunoService.criarOcorrenciaSaida(id, ocorrenciaDto)) {
            return ResponseEntity.ok("Ocorrência de saída adiantada adicionada!");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/ocorrencias/{idOcorrencia}")
    public ResponseEntity<String> alterarStatusJustificativa(@PathVariable Long id, @PathVariable Long idOcorrencia,
                                                             @RequestBody StatusDaOcorrencia status) {
        if (alunoService.alterarStatusOcorrencia(idOcorrencia, status)) {
            return ResponseEntity.ok("Status alterado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/ocorrencias/{idOcorrencia}")
    public ResponseEntity<String> inativarOcorrencia(@PathVariable Long id, @PathVariable Long idOcorrencia) {
        if (alunoService.inativarOcorrencia(idOcorrencia)) {
            return ResponseEntity.ok("Ocorrência inativada");
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
