package br.com.cursojava.petshop.dto;

import br.com.cursojava.petshop.enums.TipoAnimal;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class AnimalDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "O campo tipo deve ser informado!")
    private TipoAnimal tipo;

    @NotNull(message = "O campo nome deve ser informado!")
    @Length(min = 3, message = "O campo nome deve ser conter pelo menos 3 caracteres!")
    private String nome;

    @NotNull(message = "O campo raça deve ser informado!")
    @Length(min = 3, message = "O campo raça deve ser conter pelo menos 3 caracteres!")
    private String raca;

    @NotNull(message = "O campo idade deve ser informado!")
    @Min(value = 0, message = "A idade não pode ser menor que zero")
    private Integer idade;
}
