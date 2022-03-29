package br.com.cursojava.petshop.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class ClienteDTO {
    private Long id;

    @NotNull(message = "O campo nome deve ser informado!")
    @Length(min = 3, message = "O campo nome deve ser conter pelo menos 3 caracteres!")
    private String nome;

    @NotNull(message = "O campo telefone deve ser informado!")
    private String telefone;

    @NotNull(message = "O campo nome deve ser informado!")
    @CPF(message = "Deve ser informado um cpf válido")
    private String cpf;

    @Email(message = "deve ser informado um email válido")
    private String email;
}
