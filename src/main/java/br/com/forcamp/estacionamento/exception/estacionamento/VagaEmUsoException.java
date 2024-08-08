package br.com.forcamp.estacionamento.exception.estacionamento;

import br.com.forcamp.estacionamento.exception.NegocioException;

/**
 * Exceção lançada quando ocorre um erro relacionado à vaga em uso.
 */
public class VagaEmUsoException extends NegocioException {

    public VagaEmUsoException(String message) {
        super(message);
    }
}
