package br.com.cursojava.petshop.service;

import br.com.cursojava.petshop.model.ItemVenda;
import br.com.cursojava.petshop.repository.ItemVendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemVendaService {

    private final ItemVendaRepository itemVendaRepository;

    public ItemVendaService(ItemVendaRepository itemVendaRepository) {
        this.itemVendaRepository = itemVendaRepository;
    }

    public void gravaItemVenda(List<ItemVenda> itemVendaList){
        itemVendaRepository.saveAll(itemVendaList);
    }
}
