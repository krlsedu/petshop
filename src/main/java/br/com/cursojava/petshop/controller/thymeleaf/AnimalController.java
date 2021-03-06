package br.com.cursojava.petshop.controller.thymeleaf;

import br.com.cursojava.petshop.model.Animal;
import br.com.cursojava.petshop.model.Usuario;
import br.com.cursojava.petshop.service.AnimalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("AnimalControllerThymeleaf")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/listar-animais")
    public String listarAnimais(Model model){
        model.addAttribute("animais", animalService.getAnimais());
        return "animal/listar-animais";
    }

    @GetMapping("/adicionar-animal")
    public String adicionarUsuario(Model model, @RequestParam(required = false) Long id){
        Animal animal = animalService.getAnimalById(id);
        model.addAttribute("animal", animal);
        return "animal/adicionar-animal";
    }

    @PostMapping("/salvar-animal")
    public String salvarAnimal(Animal animal){
        animalService.criarOuSalvarAnimal(animal);
        return "redirect:/listar-animais";
    }

    @GetMapping("/deletar-animal/{id}")
    public String deletaAnimal(@PathVariable Long id){
        animalService.deletaAnimalPorId(id);
        return "redirect:/listar-animais";
    }

}
