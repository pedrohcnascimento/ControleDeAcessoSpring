package com.senai.ControleDeAcessoSpring.aplication.service.turma;

import com.senai.ControleDeAcessoSpring.aplication.dto.turma.SubTurmaDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.AlunoDto;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.Semestre;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.SubTurma;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.Turma;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.HorarioPadrao;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import com.senai.ControleDeAcessoSpring.domain.repository.turma.SubTurmaRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.turma.TurmaRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.aluno.AlunoRepository;
import com.senai.ControleDeAcessoSpring.domain.service.HorarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubTurmaService {
    @Autowired
    private SubTurmaRepository subTurmaRepository;
    @Autowired
    private TurmaRepository turmaRepository;
    @Autowired
    private HorarioService horarioService;
    @Autowired
    private AlunoRepository alunoRepository;

    @Transactional
    public void criarSubTurma(Long turmaId) {
        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        SubTurma subTurma = new SubTurma();
        subTurma.setTurma(turma);
        subTurma.setStatus(true); // Ver isso aq

        turma.getSubTurmas().add(subTurma);
        turma.getSubTurmas().getLast().setNome("Turma " + turma.getSubTurmas().size());

        Semestre semestre = new Semestre();
        subTurma.setSemestres(new ArrayList<>());
        subTurma.getSemestres().add(semestre);

        semestre.setNumero(subTurma.getSemestres().size());
        semestre.setNomeDaTurma(
                subTurma.getSemestres().size() +
                        subTurma.getTurma().getSiglaDaTurma() +
                        subTurma.getTurma().getPeriodo().getSigla()
        );
        semestre.setSubTurma(subTurma);

        // Criar HorarioPadrao vazio
        HorarioPadrao horarioPadrao = horarioService.criarHorarioPadraoVazio(semestre);
        semestre.setHorarioPadrao(horarioPadrao);

        semestre.setHorariosSemanais(new ArrayList<>());
        subTurma.setAlunos(new ArrayList<>());

        subTurmaRepository.save(subTurma);
    }

    @Transactional
    public boolean atualizar(Long id, SubTurmaDto dto) {
        Optional<SubTurma> optional = subTurmaRepository.findById(id);
        if (optional.isEmpty()) return false;

        SubTurma subTurma = optional.get();
        subTurma.setNome(dto.nome());

        subTurmaRepository.save(subTurma);
        return true;
    }

    @Transactional
    public boolean deletar(Long id) {
        if (!subTurmaRepository.existsById(id)) return false;
        subTurmaRepository.deleteById(id);
        return true;
    }

    public List<SubTurmaDto> listar() {
        return subTurmaRepository.findAll().stream()
                .map(SubTurmaDto::toDto)
                .collect(Collectors.toList());
    }

    public Optional<SubTurmaDto> buscarPorId(Long id) {
        return subTurmaRepository.findById(id).map(SubTurmaDto::toDto);
    }

    @Transactional
    public boolean adicionarAlunosPorId(Long id, List<Long> listaIdAlunos) {
        if (subTurmaRepository.findById(id).isEmpty()) {
            return false;
        }
        listaIdAlunos.forEach(idAluno -> {
            if (alunoRepository.findById(idAluno).isEmpty()) {
                return;
            }
            Aluno aluno = alunoRepository.findById(idAluno).get();
            SubTurma subturma = subTurmaRepository.findById(id).get();

            aluno.getSubTurmas().add(subturma);
            subturma.getAlunos().add(aluno);

            alunoRepository.save(aluno);

        });
        return true;
    }

    @Transactional
    public boolean removerAlunosPorId(Long id, List<Long> listaIdAlunos) {
        if (subTurmaRepository.findById(id).isEmpty()) {
            return false;
        }
        listaIdAlunos.forEach(idAluno -> {
            if (alunoRepository.findById(idAluno).isEmpty()) {
                return;
            }
            Aluno aluno = alunoRepository.findById(idAluno).get();
            SubTurma subturma = subTurmaRepository.findById(id).get();

            aluno.getSubTurmas().remove(subturma);
            subturma.getAlunos().remove(aluno);

            alunoRepository.save(aluno);

        });
        return true;
    }
}