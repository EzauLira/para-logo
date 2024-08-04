package br.com.forcamp.estacionamento.exception.cliente;

import br.com.forcamp.estacionamento.enums.ExceptionEnums;
import br.com.forcamp.estacionamento.exception.NegocioException;

/**
 * Exceção lançada quando ocorre um erro relacionado ao nome.
 */
public class NomeException extends NegocioException {

    /**
     * verifica se o campo do nome é nulo, é menor que dois dígitos ou maior que cem e se possuí numeros.
     */
    public NomeException(String nome) {
        super(ExceptionEnums.NOME_EXCEPTION.getMessage());
    }

    public static void validarNome(String nome) throws NomeException {
        if (nome == null || nome.trim().length() < 2 || nome.trim().length() > 100 || !nome.matches("^[A-Za-zÀ-ÿ ]+$")) {
            throw new NomeException(nome);
        }
    }
}
