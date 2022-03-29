package br.com.cursojava.petshop.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class LojaDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "O campo tipo deve ser informado")
    @NotBlank(message = "O campo nome deve ser informado")
    private String nome;

    @NotNull(message = "O campo endereço deve ser informado")
    @NotBlank(message = "O campo endereço deve ser informado")
    private String endereco;
}
