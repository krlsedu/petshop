package br.com.cursojava.petshop.exceptions;

public class PetShopException extends RuntimeException{
    private final int statusCode;

    public PetShopException(String mensagem){
        super(mensagem);
        this.statusCode = 400;
    }

    public PetShopException(String mensagem, int statusCode){
        super(mensagem);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
