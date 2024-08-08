package br.com.forcamp.estacionamento.exception;

/**
 * Exceção genérica para erros técnicos.
 */
public class TecnicoException extends Exception{

    public TecnicoException(String message){
        super(message);
    }
}
