package br.com.cursojava.petshop.service;

import br.com.cursojava.petshop.exceptions.PetShopException;
import br.com.cursojava.petshop.model.Venda;
import br.com.cursojava.petshop.repository.VendaRepository;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    
    private final LojaService lojaService;
    
    private final ClienteService clienteService;

    public VendaService(VendaRepository vendaRepository, LojaService lojaService, ClienteService clienteService) {
        this.vendaRepository = vendaRepository;
        this.lojaService = lojaService;
        this.clienteService = clienteService;
    }

    public Venda gravaVenda(Venda venda) {

        if (venda.getId() != null) {
            throw new PetShopException("Para realizar uma venda, não deve ser informado o id");
        }

        if (venda.getLoja().getId() == null) {
            throw new PetShopException("Deve ser informado o código de uma loja!");
        } else {
            lojaService.validaLojaExistente(venda.getLoja());
        }
        
        if (venda.getCliente().getId() == null){
            throw new PetShopException("Deve ser informado o código de um Cliente!");            
        } else {
            lojaService.validaLojaExistente(venda.getLoja());
        }

        return vendaRepository.save(venda);
    }
}
