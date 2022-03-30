package br.com.cursojava.petshop.service;

import br.com.cursojava.petshop.exceptions.PetShopException;
import br.com.cursojava.petshop.model.Item;
import br.com.cursojava.petshop.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> getItems() {
        List<Item> items = (List<Item>) repository.findAll();
        if (items.isEmpty()) {
            return null;
        }
        return items;
    }

    public Item criarItem(Item item) {
        if (item.getId() != null) {
            throw new PetShopException("Ao criar um item não deve ser informado o ID!");
        }
        return repository.save(item);
    }

    public Item alteraItem(Item item) {
        if (repository.existsById(item.getId())) {
            return repository.save(item);
        } else {
            throw new PetShopException(String.format("O item com id %d não existe!", item.getId()));
        }
    }

    public Item deletaItem(Item item) {
        if (repository.existsById(item.getId())) {
            repository.delete(item);
            return item;
        } else {
            throw new PetShopException(String.format("O ItemDTO com id %d não existe!", item.getId()));
        }
    }

    public void deletaItemPorId(Long id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new PetShopException(String.format("O ItemDTO com id %d não existe!", id));
        }
    }

    public Item getItemById(Long id){
        if (id == null) {
            return new Item();
        }

        return repository.findById(id).orElse(new Item());
    }

    public void criarOuAlterarItem(Item item){
        if (item.getId() == null) {
            criarItem(item);
        }else {
            alteraItem(item);
        }
    }

    public void validaItemExistente(Item item){
        if (!repository.existsById(item.getId())) {
            throw new PetShopException("O item informado não está cadastrado!");
        }
    }
}
