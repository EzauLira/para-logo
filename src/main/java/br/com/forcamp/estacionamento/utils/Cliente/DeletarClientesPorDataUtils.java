package br.com.forcamp.estacionamento.utils.Cliente;

import br.com.forcamp.estacionamento.enums.ClienteEnums;
import br.com.forcamp.estacionamento.model.cliente.Cliente;

import java.util.List;

/**
 * Classe de utilitários para deletar clientes por data de registro.
 * Esta classe contém métodos para verificar e excluir clientes.
 */
public class DeletarClientesPorDataUtils {

    /**
     * Verifica e exclui clientes.
     *
     * @param clientes A lista de clientes a ser verificada.
     * @param dataRegistro A data de registro dos clientes a serem excluídos.
     * @return Uma mensagem indicando o resultado da operação de exclusão.
     */
    public static String verificarEExcluirClientes(List<Cliente> clientes, String dataRegistro) {
        if (clientes.isEmpty()) {
            return ClienteEnums.CLIENTE_NAO_ENCONTRADO_DATA.getMessage();
        } else {
            return ClienteEnums.EXCLUIR_TODOS.getMessage();
        }
    }
}
