package br.com.cursojava.petshop.service;

import br.com.cursojava.petshop.exceptions.PetShopException;
import br.com.cursojava.petshop.model.Loja;
import br.com.cursojava.petshop.repository.LojaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LojaService {

    private final LojaRepository repository;

    public LojaService(LojaRepository repository) {
        this.repository = repository;
    }

    public List<Loja> getLojas() {
        List<Loja> lojas = (List<Loja>) repository.findAll();
        if (lojas.isEmpty()) {
            return null;
        }
        return lojas;
    }

    public Loja criarLoja(Loja loja) {
        if (loja.getId() != null) {
            throw new PetShopException("Ao criar um loja não deve ser informado o ID!");
        }
        return repository.save(loja);
    }

    public Loja alteraLoja(Loja loja) {
        if (repository.existsById(loja.getId())) {
            return repository.save(loja);
        } else {
            throw new PetShopException(String.format("O loja com id %d não existe!", loja.getId()));
        }
    }

    public Loja deletaLoja(Loja loja) {
        if (repository.existsById(loja.getId())) {
            repository.delete(loja);
            return loja;
        } else {
            throw new PetShopException(String.format("O Loja com id %d não existe!", loja.getId()));
        }
    }

    public void deletaLojaPorId(Long id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new PetShopException(String.format("O Loja com id %d não existe!", id));
        }
    }

    public Loja getLojaById(Long id){
        if (id == null) {
            return new Loja();
        }

        return repository.findById(id).orElse(new Loja());
    }

    public void criarOuAlterarLoja(Loja loja){
        if (loja.getId() == null) {
            criarLoja(loja);
        }else {
            alteraLoja(loja);
        }
    }
}
