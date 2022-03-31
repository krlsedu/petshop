package br.com.cursojava.petshop.controller;

import br.com.cursojava.petshop.dto.VendaDTO;
import br.com.cursojava.petshop.mapper.MapperEntidadeDTO;
import br.com.cursojava.petshop.model.Venda;
import br.com.cursojava.petshop.service.VendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public void gravaVenda(@Valid @RequestBody VendaDTO vendaDTO) {
        vendaService.gravaVenda(mapper.toEntity(vendaDTO));
    }


    @GetMapping(value = "/listar-vendas", produces = "application/json")
    public ResponseEntity<List<VendaDTO>> getVendas() {
        List<VendaDTO> vendaDTOS = mapper.toDto(vendaService.getVendas());
        return new ResponseEntity<>(vendaDTOS, HttpStatus.OK);
    }

}
