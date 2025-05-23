package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.AmbienteDto;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.Ambiente;
import com.senai.ControleDeAcessoSpring.domain.repository.AmbienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AmbienteService {
    @Autowired
    private AmbienteRepository ambienteRepository;

    public void cadastrarAmbiente(AmbienteDto dto) {
        ambienteRepository.save(dto.fromDTO());
    }

    public List<AmbienteDto> listarAtivos() {
        return ambienteRepository.findByAtivoTrue()
                .stream().map(AmbienteDto::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AmbienteDto> buscarPorId(Long id) {
        return ambienteRepository.findById(id)
                .filter(Ambiente::isAtivo)
                .map(AmbienteDto::toDTO);
    }

    public boolean atualizar(Long id, AmbienteDto dto) {
        return ambienteRepository.findById(id).map(ambiente -> {
            Ambiente ambienteAtualizado = dto.fromDTO();
            ambiente.setNome(ambienteAtualizado.getNome());
            ambienteRepository.save(ambiente);
            return true;
        }).orElse(false);
    }

    public boolean inativar(Long id) {
        return ambienteRepository.findById(id).map(ambiente -> {
            ambiente.setAtivo(false);
            ambienteRepository.save(ambiente);
            return true;
        }).orElse(false);
    }
}
