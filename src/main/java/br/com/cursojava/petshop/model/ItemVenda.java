package br.com.cursojava.petshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "venda_id,item_id")})
public class ItemVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venda_id")
    @JsonBackReference
    private Venda venda;

    @ManyToOne
    private Item item;

    private BigDecimal valorUnitario;

    private Integer quantidade;
}
