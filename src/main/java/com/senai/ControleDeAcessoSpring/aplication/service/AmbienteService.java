package com.senai.ControleDeAcessoSpring.aplication.service;

import com.seusistema.controleacesso.ambiente.dto.AmbienteDTO;
import com.seusistema.controleacesso.ambiente.model.Ambiente;
import com.seusistema.controleacesso.ambiente.repository.AmbienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AmbienteService {

    @Autowired
    private AmbienteRepository ambienteRepository;

    public AmbienteDTO criar(AmbienteDTO ambienteDTO) {
        Ambiente ambiente = ambienteDTO.toEntity();
        Ambiente ambienteSalvo = ambienteRepository.save(ambiente);
        return AmbienteDTO.fromEntity(ambienteSalvo);
    }

    public List<AmbienteDTO> listarAtivos() {
        return ambienteRepository.findByAtivoTrue().stream()
                .map(AmbienteDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public AmbienteDTO buscarPorId(Long id) {
        return ambienteRepository.findByIdAndAtivoTrue(id)
                .map(AmbienteDTO::fromEntity)
                .orElse(null);
    }

    public AmbienteDTO atualizar(Long id, AmbienteDTO ambienteDTO) {
        return ambienteRepository.findByIdAndAtivoTrue(id)
                .map(existingAmbiente -> {
                    existingAmbiente.setNome(ambienteDTO.getNome());
                    existingAmbiente.setDescricao(ambienteDTO.getDescricao());
                    Ambiente ambienteAtualizado = ambienteRepository.save(existingAmbiente);
                    return AmbienteDTO.fromEntity(ambienteAtualizado);
                })
                .orElse(null);
    }

    public void deletar(Long id) {
        ambienteRepository.findByIdAndAtivoTrue(id)
                .ifPresent(ambiente -> {
                    ambiente.setAtivo(false);
                    ambienteRepository.save(ambiente);
                });
    }
}
