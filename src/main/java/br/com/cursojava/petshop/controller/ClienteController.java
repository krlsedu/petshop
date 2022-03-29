package br.com.cursojava.petshop.controller;

import br.com.cursojava.petshop.dto.ClienteDTO;
import br.com.cursojava.petshop.mapper.MapperEntidadeDTO;
import br.com.cursojava.petshop.model.Cliente;
import br.com.cursojava.petshop.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClienteController {

    private final ClienteService service;

    private final MapperEntidadeDTO<Cliente, ClienteDTO> mapperEntidadeDTO;

    public ClienteController(ClienteService service) {
        this.service = service;
        this.mapperEntidadeDTO = new MapperEntidadeDTO<>(Cliente.class, ClienteDTO.class);
    }

    @PostMapping(value = "/criar-cliente", consumes = "application/json")
    public ResponseEntity<ClienteDTO> criarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        clienteDTO = mapperEntidadeDTO.toDto(service.criarCliente(mapperEntidadeDTO.toEntity(clienteDTO)));
        return new ResponseEntity<>(clienteDTO, HttpStatus.CREATED);
    }

    //Read do CRUD
    @GetMapping("/todos-clientes")
    public ResponseEntity<List<ClienteDTO>> getTodosAnimais() {
        List<ClienteDTO> dtoList = mapperEntidadeDTO.toDto(service.getClientes());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    //Alter do Crud
    @PutMapping(value = "/altera-cliente", consumes = "application/json")
    public ResponseEntity<ClienteDTO> alteraCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        clienteDTO = mapperEntidadeDTO.toDto(service.alteraCliente(mapperEntidadeDTO.toEntity(clienteDTO)));
        return new ResponseEntity<>(clienteDTO, HttpStatus.ACCEPTED);
    }

    //Delete do CRUD
    @DeleteMapping(value = "/deleta-cliente", consumes = "application/json")
    public ResponseEntity<ClienteDTO> deletaCliente(@RequestBody ClienteDTO clienteDTO) {
        clienteDTO = mapperEntidadeDTO.toDto(service.deletaCliente(mapperEntidadeDTO.toEntity(clienteDTO)));
        return new ResponseEntity<>(clienteDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/cliente/{id}")
    public ResponseEntity<String> deletaCliente(@PathVariable Long id) {
        service.deletaClientePorId(id);
        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }
}
