package com.senai.ControleDeAcessoSpring.Controller;


import com.senai.ControleDeAcessoSpring.Entity.*;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    private final List<Usuario> listaUsuarios = new ArrayList<>();

    public UsuariosController(){
        listaUsuarios.add(new Professor(1,1,"Rafael","rafael@gmail.com","-"));
        listaUsuarios.add(new Coordenador(2,2,"Rafael","rafael@gmail.com","-"));
        listaUsuarios.add(new AQV(3,3,"Rafael","rafael@gmail.com","-"));
        listaUsuarios.add(new Aluno(4,4,"Rafael","rafael@gmail.com","-"));
    }

    @GetMapping
    public List<Usuario> getUsuarios(){
        return listaUsuarios;
    }

    @GetMapping("/{id}")
    public Usuario getUsuarios(@PathVariable Integer id){
        return listaUsuarios.get(id);
    }

    @GetMapping("/{cargo}")
    public List getUsuarios(@PathVariable String cargo){
        List lista = new ArrayList<>();

        for (Usuario u : listaUsuarios){
            if (u.getCargo().equals(cargo)){
                lista.add(u);
            }
        }
        return lista;
    }

    @PostMapping
    public String postUsuarios(@RequestBody Usuario usuario){
        listaUsuarios.add(usuario);
        return "Usuario adicionado com sucesso";
    }

    @PutMapping("/{id}")
    public String updateUsuarios(@RequestBody Usuario usuarioAtualizado ,@PathVariable Integer id){
        for (Usuario u : listaUsuarios){
            if (u.getId().equals(id)){
                u.setNome(usuarioAtualizado.getNome());
                return "Usuario editado com sucesso";
            }
        }
        return "Usuario não encontrado";
    }

    @DeleteMapping("/{id}")
    public String deletarUsuarios(@PathVariable Integer id){
        if (listaUsuarios.get(id).equals(null)){
            return "Usuário não encontrado";
        }
        listaUsuarios.remove(id-1);
        return "Usuario deletado com sucesso";
    }
}