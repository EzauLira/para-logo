package br.com.forcamp.estacionamento.utils.Cliente;

import br.com.forcamp.estacionamento.enums.ClienteEnums;
import br.com.forcamp.estacionamento.model.RespostaPadrao;
import br.com.forcamp.estacionamento.model.cliente.Cliente;

/**
 * Classe de utilitários para buscar um cliente por RG.
 * Esta classe contém métodos para verificar se um cliente existe.
 */
public class BuscarClientePorRgUtils {

    /**
     * Verifica se um cliente existe.
     *
     * @param cliente O cliente a ser verificado.
     * @return O cliente, se existir, ou uma resposta padrão indicando que o cliente não foi encontrado.
     */
    public static Object verificarCliente(Cliente cliente) {
        if (cliente == null) {
            return new RespostaPadrao(ClienteEnums.CLIENTE_NAO_ENCONTRADO.getMessage());
        } else {
            return cliente;
        }
    }
}