package br.com.cursojava.petshop.repository;

import br.com.cursojava.petshop.model.Loja;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LojaRepository extends CrudRepository<Loja,Long> {
}
