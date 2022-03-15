package br.com.cursojava.petshop.service;

import br.com.cursojava.petshop.model.Animal;
import br.com.cursojava.petshop.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> getAnimais() {
        List<Animal> animalList = (List<Animal>) animalRepository.findAll();
        if (animalList.isEmpty()) {
            return null;
        }
        return animalList;
    }

    public Animal criarAnimal(Animal animal) {
        if (animal.getId() != null) {
            throw new RuntimeException("Ao criar um animal não deve ser informado o ID!");
        }
        return animalRepository.save(animal);
    }

    public Animal alteraAnimal(Animal animal) {
        if (animalRepository.existsById(animal.getId())) {
            return animalRepository.save(animal);
        } else {
            throw new RuntimeException(String.format("O animal com id %d não existe!", animal.getId()));
        }
    }

    public Animal deletaAnimal(Animal animal) {
        if (animalRepository.existsById(animal.getId())) {
            animalRepository.delete(animal);
            return animal;
        } else {
            throw new RuntimeException(String.format("O animal com id %d não existe!", animal.getId()));
        }
    }

    public void deletaAnimalPorId(Long id){
        if (animalRepository.existsById(id)) {
            animalRepository.deleteById(id);
        } else {
            throw new RuntimeException(String.format("O animal com id %d não existe!", id));
        }
    }

    public Animal getAnimalById(Long id){

        if (id == null) {
            return new Animal();
        }

        return animalRepository.findById(id).orElse(new Animal());
    }

    public void criarOuSalvarAnimal(Animal animal){
        if (animal.getId() == null) {
            criarAnimal(animal);
        }else {
            alteraAnimal(animal);
        }
    }
}
