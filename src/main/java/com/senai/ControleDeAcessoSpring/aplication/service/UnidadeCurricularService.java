package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.UnidadeCurricularDto;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.UnidadeCurricular;
import com.senai.ControleDeAcessoSpring.domain.repository.UnidadeCurricularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnidadeCurricularService {

    @Autowired
    UnidadeCurricularRepository unidadeCurricularRepository;

    @Autowired
    CursoService cursoService;

    public void cadastrarUnidadeCurricular(UnidadeCurricularDto dto) {
        UnidadeCurricular unidadeCurricular = dto.fromDto();
        unidadeCurricular.setAtivo(true);
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

    public boolean atualizar(Long id, UnidadeCurricularDto dto) {
        return unidadeCurricularRepository.findById(id).map(uc -> {
            UnidadeCurricular ucAtualizada = dto.fromDto();
            uc.setNome(ucAtualizada.getNome());
            uc.setCargaHorariaTotal(ucAtualizada.getCargaHorariaTotal());
            uc.setCargaHorariaPorSemestre(ucAtualizada.getCargaHorariaPorSemestre());
            uc.setProfessores(ucAtualizada.getProfessores());
            uc.setCurso(ucAtualizada.getCurso());
            unidadeCurricularRepository.save(uc);
            return true;
        }).orElse(false);
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
