package br.com.cursojava.petshop.repository;

import br.com.cursojava.petshop.model.ItemVenda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemVendaRepository extends CrudRepository<ItemVenda,Long> {
}
