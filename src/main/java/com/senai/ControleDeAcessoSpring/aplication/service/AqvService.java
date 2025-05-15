package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.AqvDto;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.AQV;
import com.senai.ControleDeAcessoSpring.domain.repository.AqvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AqvService {
    @Autowired
    private AqvRepository aqvRepository;

    public void cadastrarAqv(AqvDto dto) {
        aqvRepository.save(dto.fromDTO());
    }

    public List<AqvDto> listarAtivos() {
        return aqvRepository.findByAtivoTrue()
                .stream().map(AqvDto::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AqvDto> buscarPorId(Long id) {
        return aqvRepository.findById(id)
                .filter(AQV::isAtivo)
                .map(AqvDto::toDTO);
    }

    public boolean atualizar(Long id, AqvDto dto) {
        return aqvRepository.findById(id).map(aqv -> {
            AQV aqVAtualizado = dto.fromDTO();
            aqv.setNome(aqVAtualizado.getNome());
            aqv.setEmail(aqVAtualizado.getEmail());
            aqv.setDataNascimento(aqVAtualizado.getDataNascimento());
            aqv.setCpf(aqVAtualizado.getCpf());
            aqvRepository.save(aqv);
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
