package br.com.cursojava.petshop.controller;

import br.com.cursojava.petshop.dto.UsuarioDTO;
import br.com.cursojava.petshop.mapper.UsuarioMapper;
import br.com.cursojava.petshop.model.Usuario;
import br.com.cursojava.petshop.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //Create do Crud
    @PostMapping(value = "/criar-usuario", consumes = "application/json")
    public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody UsuarioDTO usuario) {
        Usuario usuarioConvertido = UsuarioMapper.dtoToEntity(usuario);
        usuario = UsuarioMapper.entityToDTO(usuarioService.criarUsuario(usuarioConvertido));
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    //Read do CRUD
    @GetMapping("/todos-usuarios")
    public ResponseEntity<List<UsuarioDTO>> getTodosUsuarios() {
        return new ResponseEntity<>(UsuarioMapper.entityToDTO(usuarioService.getUsuarios()), HttpStatus.OK);
    }

    @GetMapping("/usuarios/{nome}")
    public ResponseEntity<List<UsuarioDTO>> getUsuariosPorNome(@PathVariable String nome) {
        return new ResponseEntity<>(UsuarioMapper.entityToDTO(usuarioService.getUsuariosPorNome(nome)), HttpStatus.OK);
    }

    //Alter do Crud
    @PutMapping(value = "/altera-usuario", consumes = "application/json")
    public ResponseEntity<UsuarioDTO> alteraUsuario(@RequestBody UsuarioDTO usuario) {
        Usuario usuarioConvertido = UsuarioMapper.dtoToEntity(usuario);
        usuario = UsuarioMapper.entityToDTO(usuarioService.alteraUsuario(usuarioConvertido));
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    //Delete do CRUD
    @DeleteMapping(value = "/deleta-usuario", consumes = "application/json")
    public ResponseEntity<UsuarioDTO> deletaUsuario(@RequestBody UsuarioDTO usuario){
        Usuario deletaUsuario = usuarioService.deletaUsuario(UsuarioMapper.dtoToEntity(usuario));
        return new ResponseEntity<>(UsuarioMapper.entityToDTO(deletaUsuario), HttpStatus.OK);
    }
}
