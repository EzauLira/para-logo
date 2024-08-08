package br.com.forcamp.estacionamento.exception.cliente;

import br.com.forcamp.estacionamento.enums.ExceptionEnums;
import br.com.forcamp.estacionamento.exception.NegocioException;

/**
 * Exceção lançada quando ocorre um erro relacionado à data.
 */
public class DataException extends NegocioException {

    public DataException() {
        super(ExceptionEnums.DATA_EXCEPTION.getMessage());
    }
}
