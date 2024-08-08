package br.com.forcamp.estacionamento.exception.cliente;

import br.com.forcamp.estacionamento.enums.ExceptionEnums;
import br.com.forcamp.estacionamento.exception.NegocioException;

/**
 * Exceção lançada quando ocorre um erro relacionado ao RG.
 */
public class RgException extends NegocioException {

    /**
     * Verifica se o RG tem um tamanho único de 9 digitos.
     */
    public RgException(String rg) throws RgException
    {
        super(ExceptionEnums.RG_EXCEPTION.getMessage());

        String rgNumerico = rg.replaceAll("[^0-9]", "");

        if (rgNumerico.length() != 9) {
            throw this;
        }
    }
}
