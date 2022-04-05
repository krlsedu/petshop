package br.com.cursojava.petshop.service;

import br.com.cursojava.petshop.model.Usuario;
import br.com.cursojava.petshop.model.Venda;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CEPService {

    @Value("${url-servico-cep}")
    private String URL;

    public String getInfoCep(String cep) throws UnirestException {
        return Unirest.get(URL)
                .queryString("code",cep)
                .asString().getBody();
    }

    public String getInfoCep(Venda venda) throws UnirestException {
        return Unirest.post(URL)
                .header("Authorization","Bearer sakjhdaskjshdkasjhdsk")
                .body(venda)
                .asString().getBody();
    }

    public String getInfoCep(Usuario usuario) throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("127.0.0.1:8088/criar-usuario")
                .header("Content-Type", "application/json")
                .body(usuario)
                .asString();

        return response.getBody();
    }
}
