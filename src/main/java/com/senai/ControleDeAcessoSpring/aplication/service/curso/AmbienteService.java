package com.senai.ControleDeAcessoSpring.aplication.service.curso;

import com.senai.ControleDeAcessoSpring.aplication.dto.curso.AmbienteDto;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.Ambiente;
import com.senai.ControleDeAcessoSpring.domain.repository.curso.AmbienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmbienteService {
    @Autowired
    private AmbienteRepository ambienteRepository;

    public void cadastrarAmbiente(AmbienteDto dto) {
        ambienteRepository.save(dto.fromDto());
    }

    public List<AmbienteDto> listar() {
        return ambienteRepository.findAll()
                .stream()
                .filter(Ambiente::getAtivo)
                .map(AmbienteDto::toDto)
                .toList();
    }

    public Optional<AmbienteDto> listarPorId(Long id) {
        return ambienteRepository.findById(id)
                .filter(Ambiente::getAtivo)
                .map(AmbienteDto::toDto);
    }

    public Boolean inativarAmbiente(Long id) {
        return ambienteRepository.findById(id)
                .map(ambiente -> {
                    ambiente.setAtivo(false);
                    ambienteRepository.save(ambiente);
                    return true;
                }).orElse(false);
    }

    public Boolean atualizarAmbiente(Long id, AmbienteDto dto) {
        return ambienteRepository.findById(id)
                .map(ambiente -> {
                    ambiente.setId(dto.id());
                    ambiente.setNome(dto.nome());
                    ambienteRepository.save(ambiente);
                    return true;
                }).orElse(false);
    }
}
