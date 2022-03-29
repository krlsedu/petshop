package br.com.cursojava.petshop.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date dataVenda;

    @ManyToOne
    @JoinColumn(name = "id")
    private Loja loja;

    @ManyToOne
    @JoinColumn(name = "id")
    private Cliente cliente;

    private BigDecimal valorTotal;





}
