package br.com.cursojava.petshop.controller;

import br.com.cursojava.petshop.service.CEPService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CEPController {

    private final CEPService service;

    public CEPController(CEPService service) {
        this.service = service;
    }

    @GetMapping("/consulta-cep/{cep}")
    private ResponseEntity<String> getInfoCep(@PathVariable String cep) throws UnirestException {
        return new ResponseEntity<>(service.getInfoCep(cep), HttpStatus.OK);
    }

}
