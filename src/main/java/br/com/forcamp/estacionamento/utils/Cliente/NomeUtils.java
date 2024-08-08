package br.com.forcamp.estacionamento.utils.Cliente;

import br.com.forcamp.estacionamento.exception.cliente.NomeException;

/**
 * Classe de utilitários para nomes.
 * Esta classe contém métodos para validar um nome.
 */
public class NomeUtils {

    /**
     * Valida um nome.
     *
     * @param nome O nome a ser validado.
     * @throws NomeException Se o nome for inválido.
     */
    public static void validarNome(String nome) throws NomeException {
        NomeException.validarNome(nome);
    }
}

