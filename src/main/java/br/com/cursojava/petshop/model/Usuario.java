package br.com.cursojava.petshop.model;

import br.com.cursojava.petshop.enums.TipoUsuario;
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

    private String senha;

    private TipoUsuario tipoUsuario;

}
