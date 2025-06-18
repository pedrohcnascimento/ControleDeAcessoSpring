package com.senai.ControleDeAcessoSpring.aplication.service.usuarios;

import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.ProfessorDto;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.AQV;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Professor;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void cadastrarUsuario(List<ProfessorDto> listaDtos) {
        listaDtos.forEach(professorDto -> {
            Professor professor = professorDto.fromDTO();
            professor.setSenha(passwordEncoder.encode(professorDto.senha()));
            professorRepository.save(professor);
        });
    }

    public List<ProfessorDto> listarAtivos() {
        return professorRepository.findByAtivoTrue()
                .stream().map(ProfessorDto::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProfessorDto> buscarPorId(Long id) {
        return professorRepository.findById(id)
                .filter(Professor::isAtivo)
                .map(ProfessorDto::toDTO);
    }

    public boolean atualizar(Long id, ProfessorDto dto) {
        return professorRepository.findById(id).map(professor -> {
            Professor professorAtualizado = dto.fromDTO();
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