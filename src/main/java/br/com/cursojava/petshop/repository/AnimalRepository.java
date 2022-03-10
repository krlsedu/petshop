package br.com.cursojava.petshop.repository;

import br.com.cursojava.petshop.model.Animal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends CrudRepository<Animal,Long> {
}
