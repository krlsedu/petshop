package br.com.cursojava.petshop.controller;

import br.com.cursojava.petshop.model.Usuario;
import br.com.cursojava.petshop.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/index")
    public String index() {
        return "Olá";
    }

    @GetMapping("/todos-usuarios")
    public ResponseEntity<List<Usuario>> getTodosUsuarios() {
        List<Usuario> usuarios = usuarioService.getUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PostMapping(value = "/salva-usuario", consumes = "application/json")
    public ResponseEntity<Usuario> salvaUsuario(@RequestBody Usuario usuario) {
        usuario = usuarioService.salvaUsuario(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }
}
