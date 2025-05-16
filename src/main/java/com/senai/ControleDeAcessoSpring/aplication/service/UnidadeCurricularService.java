package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.UnidadeCurricularDto;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.UnidadeCurricular;
import com.senai.ControleDeAcessoSpring.domain.repository.UnidadeCurricularRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UnidadeCurricularService {

    @Autowired
    UnidadeCurricularRepository unidadeCurricularRepository;

    @Autowired
    CursoService cursoService;

    public void cadastrarUnidadeCurricular(UnidadeCurricularDto dto) {
        UnidadeCurricular unidadeCurricular = dto.fromDto();
        unidadeCurricular.setCurso(cursoService.buscarNoRepository(dto.idCurso()));
        unidadeCurricularRepository.save(unidadeCurricular);
    }

    public List<UnidadeCurricularDto> listar() {
        return unidadeCurricularRepository.findByAtivoTrue()
                .stream().map(UnidadeCurricularDto::toDto)
                .collect(Collectors.toList());
    }

    public Optional<UnidadeCurricularDto> buscarPorId(Long id) {
        return unidadeCurricularRepository.findById(id).map(UnidadeCurricularDto::toDto);
    }

    public boolean inativar(Long id) {
        return unidadeCurricularRepository.findById(id).map(unidadeCurricular -> {
            unidadeCurricular.setAtivo(false);
            unidadeCurricularRepository.save(unidadeCurricular);
            return true;
        }).orElse(false);
    }

    public UnidadeCurricular buscarNoRepository(Long id) {
        return unidadeCurricularRepository.findById(id).get();
    }
}
