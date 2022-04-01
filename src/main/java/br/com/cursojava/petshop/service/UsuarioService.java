package br.com.cursojava.petshop.service;

import br.com.cursojava.petshop.exceptions.PetShopException;
import br.com.cursojava.petshop.model.Usuario;
import br.com.cursojava.petshop.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getUsuarios() {
        List<Usuario> usuarioList = (List<Usuario>) usuarioRepository.findAll();
        if (usuarioList.isEmpty()) {
            return null;
        }
        return usuarioList;
    }

    public List<Usuario> getUsuariosPorNome(String nome) {
        return usuarioRepository.findByNome(nome);
    }

    public Usuario criarUsuario(Usuario usuario) {
        if (usuario.getId() != null) {
            throw new PetShopException("Ao criar um usuário não deve ser informado o ID!");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario alteraUsuario(Usuario usuario) {
        if (usuarioRepository.existsById(usuario.getId())) {
            return usuarioRepository.save(usuario);
        } else {
            throw new PetShopException(String.format("O usuário com id %d não existe!", usuario.getId()));
        }
    }

    public Usuario deletaUsuario(Usuario usuario) {
        if (usuarioRepository.existsById(usuario.getId())) {
            usuarioRepository.delete(usuario);
            return usuario;
        } else {
            throw new PetShopException(String.format("O usuário com id %d não existe!", usuario.getId()));
        }
    }

    public void deletaUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new PetShopException(String.format("O usuário com id %d não existe!", id));
        }
    }

    public Usuario getUsuarioById(Long id) {
        if (id == null) {
            return new Usuario();
        }
        return usuarioRepository.findById(id).orElse(null);
    }

    public void criaOuAlteraUsuario(Usuario usuario) {
        if (usuario.getId() == null) {
            criarUsuario(usuario);
        } else {
            alteraUsuario(usuario);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new PetShopException("Usuário ou senha incorretos!");
        }
        var lista = new SimpleGrantedAuthority(usuario.getTipoUsuario().getChave());
        return new User(email, usuario.getSenha(), Collections.singleton(lista));
    }
}
