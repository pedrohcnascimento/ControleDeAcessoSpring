package com.senai.ControleDeAcessoSpring.interface_ui.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping
//public class JustificativaController {
//
//    @Autowired
//    private JustificativaService justificativaService;
//
//    @PostMapping
//    public ResponseEntity<String> salvar(@RequestBody JustificativaDto justificativaDto) {
//        justificativaService.salvar(justificativaDto);
//        return ResponseEntity.ok("Justificativa salva? enviada? com sucesso!");
//    }
//
//    @GetMapping
//    public ResponseEntity<List<JustificativaDto>> listarJustificativas() {
//        return ResponseEntity.ok(justificativaService.listar());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<JustificativaDto> buscarPorId(@PathVariable Long id) {
//        return justificativaService.buscarPorId(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//
//    //Vai atualizar?
//    @PutMapping("/{id}")
//    public ResponseEntity<String> atualizar(@PathVariable Long id,
//                                            @RequestBody JustificativaDto justificativaDto) {
//        if (justificativaService.atualizar(id, justificativaDto)) {
//            return ResponseEntity.ok("Justificativa atualizada!");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deletar(@PathVariable Long id) {
//        if (justificativaService.deletar(id)) {
//            return ResponseEntity.ok("Justificativa deletada");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
//