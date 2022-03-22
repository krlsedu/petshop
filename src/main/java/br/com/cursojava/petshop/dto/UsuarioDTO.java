package br.com.cursojava.petshop.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UsuarioDTO {

    @ApiModelProperty(position = 1, value = "Id do usuário")
    private Long id;

    @ApiModelProperty(position = 2, required = true, value = "Nome do usuário", example = "José da Silva")
    private String nome;

    @ApiModelProperty(position = 3, required = true, value = "Idade do Usuário", example = "18", dataType = "Inteiro")
    private Integer idade;

    @ApiModelProperty(position = 4, value = "Email do usuário", example = "email@url.com.br")
    private String email;
}
