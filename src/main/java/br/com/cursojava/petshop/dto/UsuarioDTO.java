package br.com.cursojava.petshop.dto;

import br.com.cursojava.petshop.enums.TipoUsuario;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UsuarioDTO {

    @ApiModelProperty(position = 1, value = "Id do usuário")
    private Long id;

    @ApiModelProperty(position = 2, required = true, value = "Nome do usuário", example = "José da Silva")
    @NotNull(message = "O nome do usuário deve ser informado!")
    @NotBlank(message = "O nome deve conter informações")
    @Size(min = 5, max = 255, message = "O nome deve conter ente 5 e 255 caracteres!")
    private String nome;

    @ApiModelProperty(position = 3, required = true, value = "Idade do Usuário", example = "18", dataType = "Inteiro")
    @Min(value = 18, message = "A idade mínima é de 18 anos")
    private Integer idade;

    @ApiModelProperty(position = 4, value = "Email do usuário", example = "email@url.com.br")
    @NotNull(message = "O email deve ser informado")
    @Email(message = "O email deve respeitar o padrão usuario@petshop.com.br")
    private String email;


    @NotNull
    private String senha;

    private TipoUsuario tipoUsuario;

}
