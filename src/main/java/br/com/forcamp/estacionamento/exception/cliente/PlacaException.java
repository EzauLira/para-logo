package br.com.forcamp.estacionamento.exception.cliente;

import br.com.forcamp.estacionamento.exception.NegocioException;

/**
 * Exceção lançada quando ocorre um erro relacionado à placa.
 */
public class PlacaException extends NegocioException {

    public PlacaException(String mensagem) {
        super(mensagem);
    }
}