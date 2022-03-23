package br.com.cursojava.petshop.dto;

import br.com.cursojava.petshop.enums.TipoAnimal;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class AnimalDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private TipoAnimal tipo;

    private String nome;

    private String raca;

    private Integer idade;
}
