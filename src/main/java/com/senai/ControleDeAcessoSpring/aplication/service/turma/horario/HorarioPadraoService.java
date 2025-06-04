package com.senai.ControleDeAcessoSpring.aplication.service.turma.horario;

import com.senai.ControleDeAcessoSpring.aplication.dto.turma.horario.HorarioPadraoDto;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.Semestre;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.HorarioPadrao;
import com.senai.ControleDeAcessoSpring.domain.repository.turma.SemestreRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.turma.horario.HorarioPadraoRepository;
import com.senai.ControleDeAcessoSpring.domain.service.HorarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioPadraoService {
    @Autowired
    private HorarioPadraoRepository repository;

    @Autowired
    private HorarioService horarioService;

    @Autowired
    private SemestreRepository semestreRepository;

    @Transactional
    public void salvarHorarioPadrao(Long semestreId, HorarioPadraoDto dto) {
        Semestre semestre = semestreRepository.findById(semestreId)
                .orElseThrow(() -> new IllegalArgumentException("Semestre não encontrado"));

        HorarioPadrao horario = semestre.getHorarioPadrao();

        horarioService.preencherHorario(horario, dto.listaAulasDoDia());
        repository.save(horario);
    }


    public List<HorarioPadraoDto> listar() {
        return repository.findAll().stream()
                .map(HorarioPadraoDto::toDto).toList();
    }

    public Optional<HorarioPadraoDto> buscarPorId(Long id) {
        return repository.findById(id).map(HorarioPadraoDto::toDto);
    }

    @Transactional
    public boolean atualizar(Long id, HorarioPadraoDto dto) {
        Optional<HorarioPadrao> optional = repository.findById(id);
        if (optional.isEmpty()) return false;

        HorarioPadrao horario = optional.get();
        horarioService.preencherHorario(horario, dto.listaAulasDoDia());
        repository.save(horario);
        return true;
    }

    public boolean deletar(Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
