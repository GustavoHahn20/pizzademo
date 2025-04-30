package com.senac.pizzademo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.senac.pizzademo.model.Usuario;
import com.senac.pizzademo.repository.UsuarioRepository;;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario loginData){
        Usuario usuario = usuarioRepository.findByEmail(loginData.getEmail());

        if(usuario == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario não encontrado");
        }

        if(!usuario.getSenha().equals(loginData.getSenha())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
        }

        return ResponseEntity.ok("Login realizado com sucesso");
    }

}
