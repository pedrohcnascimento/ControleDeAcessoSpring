package com.senai.ControleDeAcessoSpring.aplication.service;


import com.senai.ControleDeAcessoSpring.aplication.dto.ListarUsuarioDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.UsuarioDto;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.AQV;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Coordenador;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Professor;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Usuario;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import com.senai.ControleDeAcessoSpring.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    public void cadastrarUsuario(UsuarioDto usuarioDto){
        Usuario usuario;

        switch (usuarioDto.tipoDeUsuario()){
            case AQV -> usuario = new AQV();
            case ALUNO -> usuario = new Aluno();
            case COORDENADOR -> usuario = new Coordenador();
            case PROFESSOR -> usuario = new Professor();
            default -> throw new IllegalArgumentException("Tipo de usuário inválido");
        }
        usuario.setNome(usuarioDto.nome());
        usuario.setCpf(usuarioDto.cpf());
        usuario.setDataNascimento(usuarioDto.dataNascimento());
        usuario.setEmail(usuarioDto.email());
        usuario.setIdAcesso("");
        usuario.setSenha("");
        usuarioRepo.save(usuario);
    }

    public List<ListarUsuarioDto> listar(){
        return usuarioRepo.findAll()
                .stream()
                .map(usuario -> new ListarUsuarioDto(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getCpf(),
                        usuario.getDataNascimento(),
                        usuario.getEmail()
                )).toList();
    }

    public Optional<ListarUsuarioDto> buscarPorId(Long id){
        return usuarioRepo.findById(id)
                .map(usuario -> new ListarUsuarioDto(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getCpf(),
                        usuario.getDataNascimento(),
                        usuario.getEmail()
                ));
    }

    public boolean atualizar(Long id, UsuarioDto usuarioDto){
        return usuarioRepo.findById(id).map(usuario -> {
            usuario.setNome(usuarioDto.nome());
            usuario.setCpf(usuarioDto.cpf());
            usuario.setDataNascimento(usuarioDto.dataNascimento());
            usuario.setEmail(usuarioDto.email());
            usuarioRepo.save(usuario);
            return true;
        }).orElse(false);
    }

    public boolean deletar(Long id){
       if(usuarioRepo.existsById(id)){
           usuarioRepo.deleteById(id);
           return true;
       }else {
           return false;
       }
    }

}
