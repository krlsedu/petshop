package br.com.cursojava.petshop.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ItemVendaDTO {

    @NotNull(message = "É obrigatório informar o item")
    private ItemIdDTO item;

    @NotNull(message = "É obrigatório informar o valor unitár")
    private BigDecimal valorUnitario;

    private Integer quantidade;
}
