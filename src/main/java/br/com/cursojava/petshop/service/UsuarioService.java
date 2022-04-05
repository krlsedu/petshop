package br.com.cursojava.petshop.service;

import br.com.cursojava.petshop.dto.TokenDTO;
import br.com.cursojava.petshop.enums.TipoUsuario;
import br.com.cursojava.petshop.exceptions.PetShopException;
import br.com.cursojava.petshop.model.Usuario;
import br.com.cursojava.petshop.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("userService")
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    private final ClientDetailsService clientDetailsService;

    private final DefaultTokenServices tokenServices;

    public UsuarioService(UsuarioRepository usuarioRepository, ClientDetailsService clientDetailsService, DefaultTokenServices tokenServices) {
        this.usuarioRepository = usuarioRepository;
        this.clientDetailsService = clientDetailsService;
        this.tokenServices = tokenServices;
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
        usuario.setSenha(cripotgrafaSenha(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    private String cripotgrafaSenha(String cripto) {
        return new BCryptPasswordEncoder().encode(cripto);
    }

    public Usuario alteraUsuario(Usuario usuario) {
        if (usuarioRepository.existsById(usuario.getId())) {
            usuario.setSenha(cripotgrafaSenha(usuario.getSenha()));
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

    public TokenDTO autentica(Usuario usuario) {
        if (usuario.getEmail() == null || usuario.getSenha() == null) {
            throw new PetShopException("Usuário ou senha incorretos!");
        }

        Usuario usuarioAutenticado = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioAutenticado == null) {
            throw new PetShopException("Usuário ou senha incorretos!");
        }

        if (!senhaOk(usuario.getSenha(), usuarioAutenticado.getSenha())) {
            throw new PetShopException("Usuário ou senha incorretos!");
        }

        return getAccessToken("pet-shop", usuarioAutenticado);

    }

    public Boolean senhaOk(String senhaInformadaAutenticacao, String senhaGravadaBanco) {
        return new BCryptPasswordEncoder().matches(senhaInformadaAutenticacao, senhaGravadaBanco);
    }

    private TokenDTO getAccessToken(String clientId, Usuario usuario) {
        HashMap<String, String> authorizationParameters = new HashMap<>();
        authorizationParameters.put("scope", "read write trust");
        authorizationParameters.put("username", usuario.getEmail());
        authorizationParameters.put("client_id", clientId);
        authorizationParameters.put("grant", "password");

        Set<GrantedAuthority> authorities = new HashSet<>();
        TipoUsuario tipoUsuario = usuario.getTipoUsuario();
        if (tipoUsuario == null) {
            tipoUsuario = TipoUsuario.USUARIO;
        }
        authorities.add(new SimpleGrantedAuthority(tipoUsuario.getChave()));

        Set<String> responseType = new HashSet<>();
        responseType.add("password");

        ClientDetails clientDetails = getClientDetails(clientId);

        Set<String> resIds = clientDetails.getResourceIds();

        OAuth2Request authorizationRequest = new OAuth2Request(
                authorizationParameters, clientId,
                clientDetails.getAuthorities(),
                true,
                clientDetails.getScope(),
                resIds, "",
                responseType, null);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                usuario.getEmail(), usuario.getSenha(), authorities);

        OAuth2Authentication authenticationRequest = new OAuth2Authentication(
                authorizationRequest, authenticationToken);

        OAuth2AccessToken accessToken = tokenServices
                .createAccessToken(authenticationRequest);

        return tokenToTokenDTO(accessToken);
    }

    private ClientDetails getClientDetails(String clientId) {
        ClientDetails clientDetails;
        try {
            clientDetails = clientDetailsService.loadClientByClientId(clientId);
            tokenServices.setAccessTokenValiditySeconds(clientDetails.getAccessTokenValiditySeconds());
        } catch (ClientRegistrationException e) {
            throw new PetShopException("Não existe um cliente");
        }
        return clientDetails;
    }

    private TokenDTO tokenToTokenDTO(OAuth2AccessToken accessToken) {

        TokenDTO tokenDTO = new TokenDTO();

        tokenDTO.setAccess_token(accessToken.getValue());
        tokenDTO.setExpires_in(accessToken.getExpiresIn());

        return tokenDTO;
    }


}
