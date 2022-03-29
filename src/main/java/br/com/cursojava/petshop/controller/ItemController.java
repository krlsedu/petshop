package br.com.cursojava.petshop.controller;

import br.com.cursojava.petshop.dto.ItemDTO;
import br.com.cursojava.petshop.mapper.MapperEntidadeDTO;
import br.com.cursojava.petshop.model.Item;
import br.com.cursojava.petshop.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ItemController {

    private final ItemService service;

    private final MapperEntidadeDTO<Item, ItemDTO> mapperEntidadeDTO;

    public ItemController(ItemService service) {
        this.service = service;
        this.mapperEntidadeDTO = new MapperEntidadeDTO<>(Item.class, ItemDTO.class);
    }

    @PostMapping(value = "/criar-item", consumes = "application/json")
    public ResponseEntity<ItemDTO> criarItem(@Valid @RequestBody ItemDTO ItemDTO) {
        ItemDTO = mapperEntidadeDTO.toDto(service.criarItem(mapperEntidadeDTO.toEntity(ItemDTO)));
        return new ResponseEntity<>(ItemDTO, HttpStatus.CREATED);
    }

    //Read do CRUD
    @GetMapping("/todos-items")
    public ResponseEntity<List<ItemDTO>> getTodosAnimais() {
        List<ItemDTO> dtoList = mapperEntidadeDTO.toDto(service.getItems());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    //Alter do Crud
    @PutMapping(value = "/altera-item", consumes = "application/json")
    public ResponseEntity<ItemDTO> alteraItem(@Valid @RequestBody ItemDTO ItemDTO) {
        ItemDTO = mapperEntidadeDTO.toDto(service.alteraItem(mapperEntidadeDTO.toEntity(ItemDTO)));
        return new ResponseEntity<>(ItemDTO, HttpStatus.ACCEPTED);
    }

    //Delete do CRUD
    @DeleteMapping(value = "/deleta-item", consumes = "application/json")
    public ResponseEntity<ItemDTO> deletaItem(@RequestBody ItemDTO ItemDTO) {
        ItemDTO = mapperEntidadeDTO.toDto(service.deletaItem(mapperEntidadeDTO.toEntity(ItemDTO)));
        return new ResponseEntity<>(ItemDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/Item/{id}")
    public ResponseEntity<String> deletaItem(@PathVariable Long id) {
        service.deletaItemPorId(id);
        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }
}
