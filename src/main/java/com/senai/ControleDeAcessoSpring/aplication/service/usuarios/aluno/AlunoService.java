package com.senai.ControleDeAcessoSpring.aplication.service.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.AlunoDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.JustificativaDto;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaJustificativa;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.aluno.AlunoRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.aluno.JustificativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    private JustificativaRepository justificativaRepository;


    public void cadastrarAluno(AlunoDto dto) {
        alunoRepository.save(dto.fromDTO());
    }

    public List<AlunoDto> listarAtivos() {
        return alunoRepository.findByAtivoTrue()
                .stream().map(AlunoDto::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AlunoDto> buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .filter(Aluno::getAtivo)
                .map(AlunoDto::toDTO);
    }

    public List<JustificativaDto> listarJustificativas(Long id) {
        return alunoRepository.findById(id).get().getJustificativas().stream().map(JustificativaDto::toDtoSemAluno).toList();
    }

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

    public boolean criarJustificativa(Long id, JustificativaDto justificativaDto) {
        Justificativa j = justificativaDto.fromDto();
        j.setAluno(alunoRepository.findById(id).get());
        j.setStatus(StatusDaJustificativa.AGUARDANDO_ANALISE);
        j.setDataHoraCriacao(LocalDateTime.now()); // Hora em que é cadastrada
        j.setDataHoraConclusao(null); // Ainda não foi concluída
        j.setAtivo(true); // Ativo quando cria
        return alunoRepository.findById(id).map(aluno -> {
            aluno.getJustificativas().add(j);
            justificativaRepository.save(j);
            return true;
        }).orElse(false);
    }

    public boolean inativar(Long id) {
        return alunoRepository.findById(id).map(aluno -> {
            aluno.setAtivo(false);
            alunoRepository.save(aluno);
            return true;
        }).orElse(false);
    }
}
