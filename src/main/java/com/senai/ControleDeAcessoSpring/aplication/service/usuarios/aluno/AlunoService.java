package com.senai.ControleDeAcessoSpring.aplication.service.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.AlunoDto;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.aluno.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    @Transactional
    public void cadastrarAluno(AlunoDto dto) {
        alunoRepository.save(dto.fromDTO());
    }

    @Transactional
    public List<AlunoDto> listarAtivos() {
        return alunoRepository.findByAtivoTrue()
                .stream().map(AlunoDto::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<AlunoDto> buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .filter(Aluno::getAtivo)
                .map(AlunoDto::toDTO);
    }

    @Transactional
    public boolean atualizar(Long id, AlunoDto dto) {
        return alunoRepository.findById(id).map(aluno -> {
            Aluno alunoAtualizado = dto.fromDTO();
            aluno.setNome(alunoAtualizado.getNome());
            aluno.setEmail(alunoAtualizado.getEmail());
            aluno.setDataNascimento(alunoAtualizado.getDataNascimento());
            aluno.setCpf(alunoAtualizado.getCpf());
            alunoRepository.save(aluno);
            return true;
        }).orElse(false);
    }

    @Transactional
    public boolean inativar(Long id) {
        return alunoRepository.findById(id).map(aluno -> {
            aluno.setAtivo(false);
            alunoRepository.save(aluno);
            return true;
        }).orElse(false);
    }
}
