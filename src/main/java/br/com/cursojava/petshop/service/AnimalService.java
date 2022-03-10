package br.com.cursojava.petshop.service;

import br.com.cursojava.petshop.model.Animal;
import br.com.cursojava.petshop.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository repository;

    public AnimalService(AnimalRepository repository) {
        this.repository = repository;
    }

    public List<Animal> getAnimais() {
        return (List<Animal>) repository.findAll();
    }

    public Animal criarAnimal(Animal animal) {
        if (animal.getId() != null) {
            throw new RuntimeException("Ao criar um animal não deve ser informado o ID!");
        }
        return repository.save(animal);
    }

    public Animal alteraAnimal(Animal animal) {
        if (repository.existsById(animal.getId())) {
            return repository.save(animal);
        } else {
            throw new RuntimeException(String.format("O anima com id %d não existe!", animal.getId()));
        }
    }

    public Animal deletaAnimal(Animal animal) {
        if (repository.existsById(animal.getId())) {
            repository.delete(animal);
            return animal;
        } else {
            throw new RuntimeException(String.format("O animal com id %d não existe!", animal.getId()));
        }
    }
}
