package br.com.forcamp.estacionamento.exception;

/**
 * Exceção genérica para erros de negócio.
 */
public class NegocioException extends Exception {

    public NegocioException(String message){
        super(message);
    }
}
