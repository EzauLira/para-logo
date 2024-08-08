package br.com.forcamp.estacionamento.exception.estacionamento;

import br.com.forcamp.estacionamento.exception.NegocioException;

/**
 * Exceção lançada quando ocorre um erro relacionado à vaga inválida.
 */
public class VagaInvalidaException extends NegocioException {
    public VagaInvalidaException(String message) {
        super(message);
    }
}
