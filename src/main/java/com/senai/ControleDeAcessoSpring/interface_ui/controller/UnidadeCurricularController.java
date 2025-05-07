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
//public class UnidadeCurricularController {
//
//    @Autowired
//    private UnidadeCurricularService unidadeCurricularService;
//
//    @PostMapping
//    public ResponseEntity<String> salvar(@RequestBody UnidadeCurricularDto unidadeCurricularDto) {
//        unidadeCurricularService.salvar(unidadeCurricularDto);
//        return ResponseEntity.ok("Unidade curricular salva com sucesso!");
//    }
//
//    @GetMapping
//    public ResponseEntity<List<UnidadeCurricularDto>> listarUnidadesCurriculares() {
//        return ResponseEntity.ok(unidadeCurricularService.listar());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UnidadeCurricularDto> buscarPorId(@PathVariable Long id) {
//        return unidadeCurricularService.buscarPorId(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<String> atualizar(@PathVariable Long id,
//                                            @RequestBody UnidadeCurricularDto unidadeCurricularDto) {
//        if (unidadeCurricularService.atualizar(id, unidadeCurricularDto)) {
//            return ResponseEntity.ok("Unidade curricular atualizada!");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deletar(@PathVariable Long id) {
//        if (unidadeCurricularService.deletar(id)) {
//            return ResponseEntity.ok("Unidade curricular deletada");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
//