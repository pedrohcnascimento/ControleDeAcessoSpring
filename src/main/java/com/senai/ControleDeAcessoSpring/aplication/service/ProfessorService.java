package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.ProfessorDto;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Professor;
import com.senai.ControleDeAcessoSpring.domain.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public void cadastrarUsuario(ProfessorDto dto) {
        professorRepository.save(dto.fromDto());
    }

    public List<ProfessorDto> listarAtivos() {
        return professorRepository.findByAtivoTrue()
                .stream().map(ProfessorDto::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ProfessorDto> buscarPorId(Long id) {
        return professorRepository.findById(id)
                .filter(Professor::isAtivo)
                .map(ProfessorDto::toDto);
    }

    public boolean atualizar(Long id, ProfessorDto dto) {
        return professorRepository.findById(id).map(professor -> {
            Professor professorAtualizado = dto.fromDto();
            professor.setNome(professorAtualizado.getNome());
            professor.setEmail(professorAtualizado.getEmail());
            professor.setDataNascimento(professorAtualizado.getDataNascimento());
            professor.setCpf(professorAtualizado.getCpf());
            professorRepository.save(professor);
            return true;
        }).orElse(false);
    }

    public boolean inativar(Long id) {
        return professorRepository.findById(id).map(professor -> {
            professor.setAtivo(false);
            professorRepository.save(professor);
            return true;
        }).orElse(false);
    }
}