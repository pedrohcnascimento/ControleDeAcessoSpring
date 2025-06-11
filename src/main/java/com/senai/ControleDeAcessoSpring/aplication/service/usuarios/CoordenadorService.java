package com.senai.ControleDeAcessoSpring.aplication.service.usuarios;

import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.CoordenadorDto;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Coordenador;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.CoordenadorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoordenadorService {

    @Autowired
    private CoordenadorRepository coordenadorRepository;

    @Transactional
    public void cadastrarCoordenador(CoordenadorDto dto) {
        coordenadorRepository.save(dto.fromDTO());
    }

    @Transactional
    public List<CoordenadorDto> listarAtivos() {
        return coordenadorRepository.findByAtivoTrue()
                .stream().map(CoordenadorDto::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<CoordenadorDto> buscarPorId(Long id) {
        return coordenadorRepository.findById(id)
                .filter(Coordenador::getAtivo)
                .map(CoordenadorDto::toDTO);
    }

    @Transactional
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

    @Transactional
    public boolean inativar(Long id) {
        return coordenadorRepository.findById(id).map(coordenador -> {
            coordenador.setAtivo(false);
            coordenadorRepository.save(coordenador);
            return true;
        }).orElse(false);
    }
}
