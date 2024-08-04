package br.com.forcamp.estacionamento.utils.Cliente;

import br.com.forcamp.estacionamento.model.cliente.Cliente;

/**
 * Classe de utilitários para clientes.
 * Esta classe contém métodos para validar se um cliente existe.
 */
public class ClienteUtils {

    /**
     * Valida se um cliente existe.
     *
     * @param cliente O cliente a ser validado.
     * @return Verdadeiro se o cliente existir, falso caso contrário.
     */
    public static boolean validarClienteExiste(Cliente cliente) {
        return cliente != null;
    }
}
