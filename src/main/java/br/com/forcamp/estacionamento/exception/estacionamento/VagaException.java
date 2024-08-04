package br.com.forcamp.estacionamento.exception.estacionamento;

import br.com.forcamp.estacionamento.exception.NegocioException;

/**
 * Exceção lançada quando ocorre um erro relacionado à vaga.
 */
public class VagaException extends NegocioException {

    public VagaException(String message) {
        super(message);
    }
}
