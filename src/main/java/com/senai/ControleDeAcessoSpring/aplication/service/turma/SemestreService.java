package com.senai.ControleDeAcessoSpring.aplication.service.turma;

import com.senai.ControleDeAcessoSpring.aplication.dto.turma.SemestreDto;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.Semestre;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.SubTurma;
import com.senai.ControleDeAcessoSpring.domain.repository.turma.SemestreRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.turma.SubTurmaRepository;
import com.senai.ControleDeAcessoSpring.domain.service.HorarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SemestreService {

    @Autowired
    private SemestreRepository semestreRepository;

    @Autowired
    private SubTurmaRepository subTurmaRepository;

    @Autowired
    private HorarioService horarioService;

    @Transactional
    public void criarSemestre(Long subTurmaId) {
        SubTurma subTurma = subTurmaRepository.findById(subTurmaId)
                .orElseThrow(() -> new RuntimeException("SubTurma não encontrada"));

        Semestre semestre = new Semestre();
        subTurma.getSemestres().add(semestre);
        semestre.setNumero(subTurma.getSemestres().size());
        semestre.setSubTurma(subTurma);
        semestre.setNomeDaTurma(
                subTurma.getSemestres().size() +
                        subTurma.getTurma().getSiglaDaTurma() +
                        subTurma.getTurma().getPeriodo().getSigla()
        );
        semestre.setHorarioPadrao(horarioService.criarHorarioPadraoVazio(semestre));
        semestre.setHorarioSemanais(new ArrayList<>());
        semestreRepository.save(semestre);
    }

    public Optional<SemestreDto> buscarPorId(Long id) {
        return semestreRepository.findById(id).map(SemestreDto::toDto);
    }

    public List<SemestreDto> listarTodos() {
        return semestreRepository.findAll().stream()
                .map(SemestreDto::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean atualizar(Long id, SemestreDto dto) {
        Optional<Semestre> optional = semestreRepository.findById(id);
        if (optional.isEmpty()) return false;

        Semestre semestre = optional.get();
        semestre.setNumero(dto.numero());

        semestreRepository.save(semestre);
        return true;
    }

    public boolean deletar(Long id) {
        Optional<Semestre> optional = semestreRepository.findById(id);
        if (optional.isEmpty()) return false;

        semestreRepository.deleteById(id);
        return true;
    }
}
