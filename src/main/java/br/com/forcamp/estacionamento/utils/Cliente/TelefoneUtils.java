package br.com.forcamp.estacionamento.utils.Cliente;

import br.com.forcamp.estacionamento.exception.cliente.TelefoneException;

/**
 * Classe de utilitários para telefones.
 * Esta classe contém métodos para validar um telefone.
 */
public class TelefoneUtils {

    /**
     * Valida um telefone.
     *
     * @param telefone O telefone a ser validado.
     * @throws TelefoneException Se o telefone for inválido.
     */
    public static void validarTelefone(String telefone) throws TelefoneException {
        new TelefoneException(telefone);

    }
}
