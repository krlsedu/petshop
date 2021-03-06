package br.com.cursojava.petshop.controller.thymeleaf;

import br.com.cursojava.petshop.model.Cliente;
import br.com.cursojava.petshop.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("ClienteControllerThymeleaf")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/listar-clientes")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.getClientes());
        return "cliente/listar-clientes";
    }

    @GetMapping("/adicionar-cliente")
    public String adicionarCliente(Model model, @RequestParam(required = false) Long id) {
        Cliente cliente = clienteService.getClienteById(id);
        model.addAttribute("cliente", cliente);
        return "cliente/adicionar-cliente";
    }

    @PostMapping("/salvar-cliente")
    public String salvarCliente(Cliente cliente){
        clienteService.criarOuAlterarCLiente(cliente);
        return "redirect:/listar-clientes";
    }

    @GetMapping("/deletar-cliente/{id}")
    public String deletaCliente(@PathVariable Long id){
        clienteService.deletaClientePorId(id);
        return "redirect:/listar-clientes";
    }
}
