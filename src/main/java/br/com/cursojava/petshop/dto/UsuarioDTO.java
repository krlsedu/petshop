package br.com.cursojava.petshop.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;

    private String nome;

    private String email;

    private Integer idade;
}
