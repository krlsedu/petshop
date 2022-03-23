package br.com.cursojava.petshop.dto;

import lombok.Data;

import java.util.List;

@Data
public class ErroDTO {
    private Integer statusCode;
    private List<DetalheErroDTO> erros;
}
