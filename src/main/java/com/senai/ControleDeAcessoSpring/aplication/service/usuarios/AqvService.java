package com.senai.ControleDeAcessoSpring.aplication.service.usuarios;

import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.AQVDto;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.AQV;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.AqvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AqvService {
    @Autowired
    private AqvRepository aqvRepository;

    public void cadastrarAqv(AQVDto dto) {
        aqvRepository.save(dto.fromDto());
    }

    public List<AQVDto> listarAtivos() {
        return aqvRepository.findByAtivoTrue()
                .stream().map(AQVDto::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AQVDto> buscarPorId(Long id) {
        return aqvRepository.findById(id)
                .filter(AQV::getAtivo)
                .map(AQVDto::toDTO);
    }

    public boolean atualizar(Long id, AQVDto dto) {
        return aqvRepository.findById(id).map(aqv -> {
            AQV aqVAtualizado = dto.fromDto();
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
