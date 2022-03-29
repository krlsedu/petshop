package br.com.cursojava.petshop.controller;

import br.com.cursojava.petshop.dto.LojaDTO;
import br.com.cursojava.petshop.mapper.MapperEntidadeDTO;
import br.com.cursojava.petshop.model.Loja;
import br.com.cursojava.petshop.service.LojaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LojaController {

    private final LojaService service;

    private final MapperEntidadeDTO<Loja, LojaDTO> mapperEntidadeDTO;

    public LojaController(LojaService service) {
        this.service = service;
        this.mapperEntidadeDTO = new MapperEntidadeDTO<>(Loja.class, LojaDTO.class);
    }

    @PostMapping(value = "/criar-loja", consumes = "application/json")
    public ResponseEntity<LojaDTO> criarLoja(@Valid @RequestBody LojaDTO LojaDTO) {
        LojaDTO = mapperEntidadeDTO.toDto(service.criarLoja(mapperEntidadeDTO.toEntity(LojaDTO)));
        return new ResponseEntity<>(LojaDTO, HttpStatus.CREATED);
    }

    //Read do CRUD
    @GetMapping("/todos-lojas")
    public ResponseEntity<List<LojaDTO>> getTodosAnimais() {
        List<LojaDTO> dtoList = mapperEntidadeDTO.toDto(service.getLojas());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    //Alter do Crud
    @PutMapping(value = "/altera-loja", consumes = "application/json")
    public ResponseEntity<LojaDTO> alteraLoja(@Valid @RequestBody LojaDTO LojaDTO) {
        LojaDTO = mapperEntidadeDTO.toDto(service.alteraLoja(mapperEntidadeDTO.toEntity(LojaDTO)));
        return new ResponseEntity<>(LojaDTO, HttpStatus.ACCEPTED);
    }

    //Delete do CRUD
    @DeleteMapping(value = "/deleta-loja", consumes = "application/json")
    public ResponseEntity<LojaDTO> deletaLoja(@RequestBody LojaDTO LojaDTO) {
        LojaDTO = mapperEntidadeDTO.toDto(service.deletaLoja(mapperEntidadeDTO.toEntity(LojaDTO)));
        return new ResponseEntity<>(LojaDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/Loja/{id}")
    public ResponseEntity<String> deletaLoja(@PathVariable Long id) {
        service.deletaLojaPorId(id);
        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }
}
