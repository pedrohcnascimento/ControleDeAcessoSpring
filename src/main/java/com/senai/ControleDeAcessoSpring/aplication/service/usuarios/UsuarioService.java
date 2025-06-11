package com.senai.ControleDeAcessoSpring.aplication.service.usuarios;


import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.UsuarioDto;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Usuario;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired private UsuarioRepository usuarioRepository;

    @Transactional
    public void cadastrarUsuario(UsuarioDto dto) {
        usuarioRepository.save(dto.fromDto());
    }

    @Transactional
    public List<UsuarioDto> listarAtivos() {
        return usuarioRepository.findByAtivoTrue()
                .stream().map(UsuarioDto::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<UsuarioDto> buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .filter(Usuario::getAtivo)
                .map(UsuarioDto::toDto);
    }

    @Transactional
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

    @Transactional
    public boolean inativar(Long id) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setAtivo(false);
            usuarioRepository.save(usuario);
            return true;
        }).orElse(false);
    }

    @Transactional
    public void cadastrarIdAcesso(Long idUsuario, String idAcesso){
        if (verificarIdAcesso(idAcesso, idUsuario)) {
            usuarioRepository.findById(idUsuario).ifPresent(usuario -> {
                usuario.setIdAcesso(idAcesso);
                usuarioRepository.save(usuario);
                System.out.println("ID de acesso cadastrado com sucesso.");
            });
        }
    }

    @Transactional
    public void atualizarIdAcesso(Long idUsuario, String idAcesso){
        if (verificarIdAcesso(idAcesso, idUsuario)) {
            usuarioRepository.findById(idUsuario).map(usuario -> {
                usuario.setIdAcesso(idAcesso);
                usuarioRepository.save(usuario);
                System.out.println("ID de acesso atualizado com sucesso.");
                return true;
            });
        }
    }

    @Transactional
    private boolean verificarIdAcesso(String idAcesso, Long idUsuario) {
        Optional<Usuario> usuarioOptionalIdAcesso = usuarioRepository.findByIdAcesso(idAcesso);
        Optional<Usuario> usuarioOptionalId = usuarioRepository.findById(idUsuario);
        if (usuarioOptionalIdAcesso.isPresent()) {
           System.out.println("ID de acesso já cadastrado para outro aluno.");
           return false;
        }else if (usuarioOptionalId.isEmpty()){
            System.out.println("Aluno não encontrado.");
            return false;
        }
        return true;
    }
}