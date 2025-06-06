package com.senai.ControleDeAcessoSpring.aplication.dto.usuarios;


import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.AQV;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Coordenador;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Professor;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Usuario;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import com.senai.ControleDeAcessoSpring.domain.enums.TipoDeUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

public record UsuarioDto (
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email,
        TipoDeUsuario tipoDeUsuario,
        String senha
){
  public static UsuarioDto toDto(Usuario u) {
        TipoDeUsuario tipo = switch (u) {
            case Aluno a -> TipoDeUsuario.ALUNO;
            case Professor p -> TipoDeUsuario.PROFESSOR;
            case Coordenador p -> TipoDeUsuario.COORDENADOR;
            case AQV p -> TipoDeUsuario.AQV;
            default -> throw new IllegalArgumentException("Usuário desconhecido detectado!");

        };
        return new UsuarioDto(u.getId(), u.getNome(), u.getCpf(), u.getDataNascimento(), u.getEmail(), tipo, u.getSenha());
    }

    public Usuario fromDto () {

        Usuario usuario = switch (tipoDeUsuario) {
            case ALUNO -> new Aluno();
            case PROFESSOR -> new Professor();
            case COORDENADOR -> new Coordenador();
            case AQV -> new AQV();
        };
        usuario.setId(id);
        usuario.setNome(nome);
        usuario.setCpf(cpf);
        usuario.setEmail(email);
        usuario.setDataNascimento(dataNascimento);
        usuario.setAtivo(true);
        usuario.setIdAcesso("");
        usuario.setSenha(senha);
        return usuario;
    }
}
