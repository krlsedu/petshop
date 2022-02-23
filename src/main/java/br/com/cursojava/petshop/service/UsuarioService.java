package br.com.cursojava.petshop.service;

import br.com.cursojava.petshop.model.Usuario;
import br.com.cursojava.petshop.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getUsuarios(){
        return (List<Usuario>) usuarioRepository.findAll();
    }
}
