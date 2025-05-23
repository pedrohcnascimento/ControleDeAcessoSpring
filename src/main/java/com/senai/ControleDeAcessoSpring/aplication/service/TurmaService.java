package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.CursoDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.TurmaDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.UnidadeCurricularDto;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.Curso;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.Semestre;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.SubTurma;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.Turma;
import com.senai.ControleDeAcessoSpring.domain.repository.CursoRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.TurmaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private HorarioService horarioService;

    @Transactional
    public void criarTurma(TurmaDto dto) {
        Turma turma = dto.fromDto();

        Curso curso = cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        turma.setCurso(curso);

        SubTurma subTurma = new SubTurma();
        turma.getSubTurmas().add(subTurma);

        subTurma.setNome("Turma "+ turma.getSubTurmas().size());
        subTurma.setTurma(turma);

        Semestre semestre = new Semestre();
        subTurma.getSemestres().add(semestre);
        semestre.setNomeDaTurma(
                subTurma.getSemestres().size() +
                        subTurma.getTurma().getSiglaDaTurma() +
                        subTurma.getTurma().getPeriodo().getSigla()
        );

        semestre.setSubTurma(subTurma);
        semestre.setNumero(subTurma.getSemestres().size());

        semestre.setHorarioPadrao(horarioService.criarHorarioPadraoVazio(semestre));
        semestre.setHorariosSemanais(new ArrayList<>());

        turmaRepository.save(turma);
    }

    public List<TurmaDto> listarTurmas() {
        return turmaRepository.findAll().stream()
                .map(TurmaDto::toDto).toList();
    }

    public Optional<TurmaDto> buscarPorId(Long id) {
        return turmaRepository.findById(id).map(TurmaDto::toDto);
    }

    @Transactional
    public boolean atualizarTurma(Long id, TurmaDto dto) {
        Optional<Turma> optional = turmaRepository.findById(id);
        if (optional.isEmpty()) return false;

        Turma turma = optional.get();
        turma.setSiglaDaTurma(dto.siglaDaTurma());
        turma.setPeriodo(dto.periodo());
        turma.setDataInicial(dto.dataInicial());
        turma.setHorarioEntrada(dto.horarioEntrada());
        turma.setQtdSemestres(dto.qtdSemestres());
        turma.setQtdAulasPorDia(dto.qtdAulasPorDia());

        turmaRepository.save(turma);
        return true;
    }

    public boolean deletarTurma(Long id) {
        Optional<Turma> optional = turmaRepository.findById(id);
        if (optional.isEmpty()) return false;
        turmaRepository.deleteById(id);
        return true;
    }
}