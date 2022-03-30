package br.com.cursojava.petshop.service;

import br.com.cursojava.petshop.exceptions.PetShopException;
import br.com.cursojava.petshop.model.ItemVenda;
import br.com.cursojava.petshop.model.Venda;
import br.com.cursojava.petshop.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;

    private final LojaService lojaService;

    private final ClienteService clienteService;

    private final ItemVendaService itemVendaService;

    private final ItemService itemService;

    public VendaService(VendaRepository vendaRepository, LojaService lojaService, ClienteService clienteService, ItemVendaService itemVendaService, ItemService itemService) {
        this.vendaRepository = vendaRepository;
        this.lojaService = lojaService;
        this.clienteService = clienteService;
        this.itemVendaService = itemVendaService;
        this.itemService = itemService;
    }

    public void gravaVenda(Venda venda) {

        if (venda.getId() != null) {
            throw new PetShopException("Para realizar uma venda, não deve ser informado o id");
        }

        if (venda.getLoja().getId() == null) {
            throw new PetShopException("Deve ser informado o código de uma loja!");
        } else {
            lojaService.validaLojaExistente(venda.getLoja());
        }

        if (venda.getCliente().getId() == null) {
            throw new PetShopException("Deve ser informado o código de um Cliente!");
        } else {
            clienteService.validaClienteExistente(venda.getCliente());
        }

        venda = vendaRepository.save(venda);

        venda.setValorTotal(BigDecimal.ZERO);
        for (ItemVenda itemVenda : venda.getItensVenda()) {
            itemVenda.setVenda(venda);
            itemService.validaItemExistente(itemVenda.getItem());
            venda.setValorTotal(
                    venda.getValorTotal()
                            .add(
                                    itemVenda.getValorUnitario()
                                            .multiply(
                                                    new BigDecimal(
                                                            itemVenda.getQuantidade()))));
        }

        vendaRepository.save(venda);

        itemVendaService.gravaItemVenda(venda.getItensVenda());

    }
}
