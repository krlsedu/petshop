package br.com.cursojava.petshop.dto;

import br.com.cursojava.petshop.enums.TipoItem;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ItemDTO {
    private Long id;

    @NotNull(message = "O campo nome deve ser informado")
    private String nome;

    @NotNull(message = "O campo valor deve ser informado")
    @Min(value = 0, message = "O valor não pode ser menor que zero")
    private BigDecimal valor;

    @NotNull(message = "O campo tipo deve ser informado")
    private TipoItem tipo;
}
