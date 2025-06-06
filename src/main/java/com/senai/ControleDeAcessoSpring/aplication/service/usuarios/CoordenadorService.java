package com.senai.ControleDeAcessoSpring.aplication.service.usuarios;

import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.CoordenadorDto;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Coordenador;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.CoordenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoordenadorService {

    @Autowired
    private CoordenadorRepository coordenadorRepository;

    public void cadastrarCoordenador(List<CoordenadorDto> dtos) {
        dtos.forEach(coordenadorDto -> {
            coordenadorRepository.save(coordenadorDto.fromDTO());
        });
    }

    public List<CoordenadorDto> listarAtivos() {
        return coordenadorRepository.findByAtivoTrue()
                .stream().map(CoordenadorDto::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CoordenadorDto> buscarPorId(Long id) {
        return coordenadorRepository.findById(id)
                .filter(Coordenador::getAtivo)
                .map(CoordenadorDto::toDTO);
    }

    public boolean atualizar(Long id, CoordenadorDto dto) {
        return coordenadorRepository.findById(id).map(coordenador -> {
            Coordenador coordenadorAtualizado = dto.fromDTO();
            coordenador.setNome(coordenadorAtualizado.getNome());
            coordenador.setEmail(coordenadorAtualizado.getEmail());
            coordenador.setDataNascimento(coordenadorAtualizado.getDataNascimento());
            coordenador.setCpf(coordenadorAtualizado.getCpf());
            coordenadorRepository.save(coordenador);
            return true;
        }).orElse(false);
    }

    public boolean inativar(Long id) {
        return coordenadorRepository.findById(id).map(coordenador -> {
            coordenador.setAtivo(false);
            coordenadorRepository.save(coordenador);
            return true;
        }).orElse(false);
    }
}
