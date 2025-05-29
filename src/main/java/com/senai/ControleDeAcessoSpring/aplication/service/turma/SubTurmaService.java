package com.senai.ControleDeAcessoSpring.aplication.service.turma;

import com.senai.ControleDeAcessoSpring.aplication.dto.turma.SubTurmaDto;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.SubTurma;
import com.senai.ControleDeAcessoSpring.domain.repository.turma.SubTurmaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubTurmaService {
    @Autowired
    private SubTurmaRepository subTurmaRepository;

    @Transactional
    public void cadastrarSubTurma(SubTurmaDto dto) {
        SubTurma novaSubTurma = new SubTurma();

        novaSubTurma.setNome(dto.nome());
        novaSubTurma.setStatus(dto.status());

        subTurmaRepository.save(novaSubTurma);
    }

    @Transactional
    public Boolean deletarSubTurma(Long id) {
        return subTurmaRepository.findById(id).map(st -> {
            st.setStatus(false);
            return true;
        }).orElse(false);
    }

    @Transactional
    public Boolean atualizarSubTurma(Long id, SubTurmaDto novaSubTurma) {
        return subTurmaRepository.findById(id).map(st -> {
            st.setNome(novaSubTurma.nome());
            st.setStatus(novaSubTurma.status());
            return true;
        }).orElse(false);
    }

    public List<SubTurmaDto> listar() {
        return subTurmaRepository.findAll().stream().map(st -> new SubTurmaDto(
                st.getNome(),
                st.getStatus(),
                st.getTurma()
                        .getId()
        )).toList();
    }

    public SubTurmaDto listarPorId(Long id) {
        SubTurma subTurma = subTurmaRepository.findById(id).get();
        SubTurmaDto dto = new SubTurmaDto(
                subTurma.getNome(),
                subTurma.getStatus(),
                subTurma.getTurma()
                        .getId());
        return dto;
    }
}