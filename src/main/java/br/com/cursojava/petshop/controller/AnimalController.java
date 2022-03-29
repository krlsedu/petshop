package br.com.cursojava.petshop.controller;

import br.com.cursojava.petshop.dto.AnimalDTO;
import br.com.cursojava.petshop.mapper.MapperEntidadeDTO;
import br.com.cursojava.petshop.model.Animal;
import br.com.cursojava.petshop.service.AnimalService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@Api(tags = "Cadastros")
public class AnimalController {

    private final AnimalService animalService;

    private final MapperEntidadeDTO<Animal, AnimalDTO> mapperEntidadeDTO;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
        this.mapperEntidadeDTO = new MapperEntidadeDTO<>(Animal.class, AnimalDTO.class);
    }

    @PostMapping(value = "/criar-animal", consumes = "application/json")
    public ResponseEntity<AnimalDTO> criarAnimal(@Valid @RequestBody AnimalDTO animal) {
        animal = mapperEntidadeDTO.toDto(animalService.criarAnimal(mapperEntidadeDTO.toEntity(animal)));
        return new ResponseEntity<>(animal, HttpStatus.CREATED);
    }

    //Read do CRUD
    @GetMapping("/todos-animais")
    public ResponseEntity<List<AnimalDTO>> getTodosAnimais() {
        List<AnimalDTO> usuarios = mapperEntidadeDTO.toDto(animalService.getAnimais());
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    //Alter do Crud
    @PutMapping(value = "/altera-animal", consumes = "application/json")
    public ResponseEntity<AnimalDTO> alteraAnimal(@Valid @RequestBody AnimalDTO animal) {
        animal = mapperEntidadeDTO.toDto(animalService.alteraAnimal(mapperEntidadeDTO.toEntity(animal)));
        return new ResponseEntity<>(animal, HttpStatus.ACCEPTED);
    }

    //Delete do CRUD
    @DeleteMapping(value = "/deleta-animal", consumes = "application/json")
    public ResponseEntity<AnimalDTO> deletaAnimal(@RequestBody AnimalDTO animal) {
        animal = mapperEntidadeDTO.toDto(animalService.deletaAnimal(mapperEntidadeDTO.toEntity(animal)));
        return new ResponseEntity<>(animal, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/animal/{id}")
    public ResponseEntity<String> deletaAnimal(@PathVariable Long id) {
        animalService.deletaAnimalPorId(id);
        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }
}
