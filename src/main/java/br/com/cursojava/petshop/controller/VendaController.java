package br.com.cursojava.petshop.controller;

import br.com.cursojava.petshop.dto.VendaDTO;
import br.com.cursojava.petshop.mapper.MapperEntidadeDTO;
import br.com.cursojava.petshop.model.Venda;
import br.com.cursojava.petshop.service.VendaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class VendaController {

    private final VendaService vendaService;

    private final MapperEntidadeDTO<Venda, VendaDTO> mapper;


    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
        this.mapper = new MapperEntidadeDTO<>(Venda.class, VendaDTO.class);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(value = "/grava-venda", consumes = "application/json")
    public void gravaVenda(@Valid @RequestBody VendaDTO vendaDTO){
        vendaService.gravaVenda(mapper.toEntity(vendaDTO));
    }
}
