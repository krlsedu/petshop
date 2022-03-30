package br.com.cursojava.petshop.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class VendaDTO {
    private Long id;

    private Date dataVenda;

    private LojaIdDTO loja;

    private ClienteIdDTO cliente;

    private BigDecimal valorTotal;
}
