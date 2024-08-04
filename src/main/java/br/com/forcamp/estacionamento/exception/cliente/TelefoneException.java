package br.com.forcamp.estacionamento.exception.cliente;

import br.com.forcamp.estacionamento.enums.ExceptionEnums;
import br.com.forcamp.estacionamento.exception.NegocioException;

/**
 * Exceção lançada quando ocorre um erro relacionado ao telefone.
 */
public class TelefoneException extends NegocioException {

    /**
     * Verifica se o número do telefone tem uma numeração e padrão correto.
     */
    public TelefoneException(String telefone) throws TelefoneException
    {
        super(ExceptionEnums.TELEFONE_EXCEPTION.getMessage());
        String telefoneNumerico = telefone.replaceAll("[^0-9]", "");

        if (telefoneNumerico.length() != 11) {
            throw this;
        }
    }
}
