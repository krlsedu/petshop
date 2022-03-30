package br.com.cursojava.petshop.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class VendaDTO {
    private Long id;

    @NotNull(message = "A data da venda deve ser informada!")
    private Date dataVenda;

    @NotNull(message = "A loja da venda deve ser informada!")
    private LojaIdDTO loja;

    @NotNull(message = "O Cliente da venda deve ser informado!")
    private ClienteIdDTO cliente;

    @Null(message = "O valor total da venda não deve ser informado, será calculado com base nos itens!")
    private BigDecimal valorTotal;

    @NotNull(message = "Pelo menos um item deve ser informado")
    private List<ItemVendaDTO> itensVenda;
}
