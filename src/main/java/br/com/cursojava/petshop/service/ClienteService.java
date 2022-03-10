package br.com.cursojava.petshop.service;

import br.com.cursojava.petshop.model.Cliente;
import br.com.cursojava.petshop.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> getClientes() {
        return (List<Cliente>) repository.findAll();
    }

    public Cliente criarCliente(Cliente cliente) {
        if (cliente.getId() != null) {
            throw new RuntimeException("Ao criar um cliente não deve ser informado o ID!");
        }
        return repository.save(cliente);
    }

    public Cliente alteraCliente(Cliente cliente) {
        if (repository.existsById(cliente.getId())) {
            return repository.save(cliente);
        } else {
            throw new RuntimeException(String.format("O cliente com id %d não existe!", cliente.getId()));
        }
    }

    public Cliente deletaCliente(Cliente cliente) {
        if (repository.existsById(cliente.getId())) {
            repository.delete(cliente);
            return cliente;
        } else {
            throw new RuntimeException(String.format("O Cliente com id %d não existe!", cliente.getId()));
        }
    }
}
