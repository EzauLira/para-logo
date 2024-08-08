package br.com.forcamp.estacionamento.utils.Cliente;

import br.com.forcamp.estacionamento.enums.ClienteEnums;
import br.com.forcamp.estacionamento.model.cliente.Cliente;

import java.util.List;

/**
 * Classe de utilitários para buscar todos os clientes.
 * Esta classe contém métodos para verificar se existem clientes.
 */
public class BuscarTodosClienteUtils {

    /**
     * Verifica se existem clientes.
     *
     * @param clientes A lista de clientes a ser verificada.
     * @return Uma mensagem indicando se existem clientes ou não.
     */
    public static String verificarClientes(List<Cliente> clientes) {
        if (clientes.isEmpty()) {
            return ClienteEnums.NENHUM_CLIENTE_CADASTRADO.getMessage();
        } else {
            return null;
        }
    }
}
