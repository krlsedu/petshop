package br.com.cursojava.petshop.mapper;

import br.com.cursojava.petshop.dto.AnimalDTO;
import br.com.cursojava.petshop.model.Animal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

public class AnimalMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public static Animal dtoToEntity(Animal animal){
        return objectMapper.convertValue(animal,Animal.class);
    }

    public static List<Animal> dtoToEntity(List<Animal> animal){
        return animal.stream().map(AnimalMapper::dtoToEntity).collect(Collectors.toList());
    }

    public static AnimalDTO entityToDTO(AnimalDTO usuario){
        return objectMapper.convertValue(usuario,AnimalDTO.class);
    }

    public static List<AnimalDTO> entityToDTO(List<AnimalDTO> animalDTOS){
        return animalDTOS.stream().map(AnimalMapper::entityToDTO).collect(Collectors.toList());
    }
}
