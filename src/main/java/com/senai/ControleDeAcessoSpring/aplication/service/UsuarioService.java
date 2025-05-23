package com.senai.ControleDeAcessoSpring.aplication.service;


import com.senai.ControleDeAcessoSpring.aplication.dto.UsuarioDto;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Usuario;
import com.senai.ControleDeAcessoSpring.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired private UsuarioRepository usuarioRepository;

    public void cadastrarUsuario(UsuarioDto dto) {
        usuarioRepository.save(dto.fromDto());
    }

    public List<UsuarioDto> listarAtivos() {
        return usuarioRepository.findByAtivoTrue()
                .stream().map(UsuarioDto::toDto)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDto> buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .filter(Usuario::isAtivo)
                .map(UsuarioDto::toDto);
    }

    public boolean atualizar(Long id, UsuarioDto dto) {
        return usuarioRepository.findById(id).map(usuario -> {
            Usuario usuarioAtualizado = dto.fromDto();
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setDataNascimento(usuarioAtualizado.getDataNascimento());
            usuario.setCpf(usuarioAtualizado.getCpf());
            usuarioRepository.save(usuario);
            return true;
        }).orElse(false);
    }

    public boolean inativar(Long id) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setAtivo(false);
            usuarioRepository.save(usuario);
            return true;
        }).orElse(false);
    }
}