package br.com.cursojava.petshop.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String email;

    private Integer idade;

}
