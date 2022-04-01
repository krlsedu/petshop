package br.com.cursojava.petshop.mapper;

import br.com.cursojava.petshop.dto.UsuarioDTO;
import br.com.cursojava.petshop.model.Usuario;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public static Usuario dtoToEntity(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setIdade(usuarioDTO.getIdade());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setTipoUsuario(usuarioDTO.getTipoUsuario());
        return usuario;
    }

    public static List<Usuario> dtoToEntity(List<UsuarioDTO> usuarioDTOList){
        List<Usuario> list = new ArrayList<>();
        for (UsuarioDTO usuarioDTO : usuarioDTOList) {
            Usuario dtoToEntity = dtoToEntity(usuarioDTO);
            list.add(dtoToEntity);
        }
        return list;
    }

    public static UsuarioDTO entityToDTO(Usuario usuario){
        return objectMapper.convertValue(usuario,UsuarioDTO.class);
    }

    public static List<UsuarioDTO> entityToDTO(List<Usuario> usuarios){
        return usuarios.stream().map(UsuarioMapper::entityToDTO).collect(Collectors.toList());
    }
}
