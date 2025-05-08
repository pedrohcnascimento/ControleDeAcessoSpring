package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.JustificativaDto;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaJustificativa;
import com.senai.ControleDeAcessoSpring.domain.repository.JustificativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JustificativaService {

    @Autowired
    JustificativaRepository justificativaRepository;

    public void cadastrarJustificativa(JustificativaDto dto) {
        Justificativa justificativa = new Justificativa();
        justificativa.setTipo(dto.tipoDeJustificativa());
        justificativa.setDescricao(dto.descricao());
        justificativa.setAnexo(dto.anexo());
        justificativa.setDataInicial(dto.dataInicial());
        justificativa.setQtdDias(dto.qtdDias());
        justificativa.setDataHoraCriacao(dto.dataHoraCriacao());
        justificativa.setDataHoraConclusao(dto.dataHoraConclusao());
        justificativa.setStatus(StatusDaJustificativa.AGUARDANDO_ANALISE);
        justificativaRepository.save(justificativa);
    }

    public List<JustificativaDto> listar() {
       return justificativaRepository.findAll().stream().map(justificativa -> new JustificativaDto(
               justificativa.getId(),
               justificativa.getTipo(),
               justificativa.getDescricao(),
               justificativa.getAnexo(),
               justificativa.getDataInicial(),
               justificativa.getQtdDias(),
               justificativa.getDataHoraCriacao(),
               justificativa.getDataHoraConclusao(),
               justificativa.getStatus(),
               justificativa.getAluno().getId()
       )).toList();
    }

    public Optional<JustificativaDto> buscarPorId(Long id) {
        return justificativaRepository.findById(id).map(justificativa -> new JustificativaDto(
                justificativa.getId(),
                justificativa.getTipo(),
                justificativa.getDescricao(),
                justificativa.getAnexo(),
                justificativa.getDataInicial(),
                justificativa.getQtdDias(),
                justificativa.getDataHoraCriacao(),
                justificativa.getDataHoraConclusao(),
                justificativa.getStatus(),
                justificativa.getAluno().getId()
        ));
    }

    public boolean alterarStatus(Long id, StatusDaJustificativa status) {
        justificativaRepository.findById(id).map(justificativa -> {
                    justificativa.setStatus(StatusDaJustificativa.valueOf(String.valueOf(status)));

                    justificativaRepository.save(justificativa);
                    return true;
        }
        ).orElse(false);
        return false;
    }
}
