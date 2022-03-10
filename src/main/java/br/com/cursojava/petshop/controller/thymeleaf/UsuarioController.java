package br.com.cursojava.petshop.controller.thymeleaf;

import br.com.cursojava.petshop.model.Usuario;
import br.com.cursojava.petshop.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("UsuarioControllerThymeleaf")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/cadastro-usuario")
    public String getCadastroUsuarios(Model model){
        model.addAttribute("usuarios", usuarioService.getUsuarios());
        return "usuarios";
    }

    @GetMapping("/adicionar-usuario")
    public String getAdicionarUsuarios(Usuario usuario){
        return "adicionar-usuario";
    }

    @PostMapping("/salvar-usuario")
    public String salvarUsuario(Usuario usuario, BindingResult bindingResult){
        usuarioService.criarUsuario(usuario);
        return "redirect:/cadastro-usuario";
    }
}
