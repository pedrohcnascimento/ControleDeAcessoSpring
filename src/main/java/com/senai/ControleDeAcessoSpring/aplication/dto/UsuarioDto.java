package com.senai.ControleDeAcessoSpring.aplication.dto;


import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.AQV;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Coordenador;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Professor;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Usuario;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import com.senai.ControleDeAcessoSpring.domain.enums.TipoDeUsuario;

import java.time.LocalDate;

public record UsuarioDto(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email,
        String idAcesso,
        TipoDeUsuario tipoDeUsuario
) {
    public static UsuarioDto toDto(Usuario u) {
        TipoDeUsuario tipo = switch (u) {
            case Aluno a -> TipoDeUsuario.ALUNO;
            case Professor p -> TipoDeUsuario.PROFESSOR;
            case Coordenador c -> TipoDeUsuario.COORDENADOR;
            case AQV aqv -> TipoDeUsuario.AQV;
            default -> throw new IllegalArgumentException("Tipo de usuário desconhecido");
        };
        return new UsuarioDto(
                u.getId(),
                u.getNome(),
                u.getCpf(),
                u.getDataNascimento(),
                u.getEmail(),
                u.getIdAcesso(),
                tipo
        );
    }

    public Usuario fromDto() {
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
        usuario.setIdAcesso(idAcesso);
        usuario.setSenha("");
        return usuario;
    }
}