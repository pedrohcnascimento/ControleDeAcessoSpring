package com.senai.ControleDeAcessoSpring.aplication.service;


import com.senai.ControleDeAcessoSpring.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

}
