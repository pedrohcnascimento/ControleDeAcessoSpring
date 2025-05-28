package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.AQVDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.AlunoDto;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.AQV;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import com.senai.ControleDeAcessoSpring.domain.repository.AqvRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.JustificativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AQVService {

    @Autowired
    private AqvRepository aqvRepository;

    @Autowired
    private JustificativaRepository justificativaRepository;

    public void cadastrarAqv(AQVDto dto) {
        aqvRepository.save(dto.fromDto());
    }

    public List<AQVDto> listarAtivos() {
        return aqvRepository.findByAtivoTrue().stream()
                .map(AQVDto::toDto)
                .collect(Collectors.toList());
    }

    public Optional<AQVDto> buscarPorId(Long id) {
        return aqvRepository.findById(id)
                .filter(AQV::isAtivo)
                .map(AQVDto::toDto);
    }

    public boolean atualizar(Long id, AlunoDto dto) {
        return aqvRepository.findById(id).map(aluno -> {
            Aluno alunoAtualizado = dto.fromDto();
            aluno.setNome(alunoAtualizado.getNome());
            aluno.setEmail(alunoAtualizado.getEmail());
            aluno.setDataNascimento(alunoAtualizado.getDataNascimento());
            aluno.setCpf(alunoAtualizado.getCpf());
            aqvRepository.save(aluno);
            return true;
        }).orElse(false);
    }

    public boolean inativar(Long id) {
        return aqvRepository.findById(id).map(aqv -> {
            aqv.setAtivo(false);
            aqvRepository.save(aqv);
            return true;
        }).orElse(false);
    }
}
