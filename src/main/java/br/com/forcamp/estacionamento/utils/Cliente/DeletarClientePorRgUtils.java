package br.com.forcamp.estacionamento.utils.Cliente;

import br.com.forcamp.estacionamento.enums.ClienteEnums;
import br.com.forcamp.estacionamento.model.cliente.Cliente;
import org.springframework.http.ResponseEntity;

/**
 * Classe de utilitários para deletar um cliente por RG.
 * Esta classe contém métodos para verificar e excluir um cliente.
 */
public class DeletarClientePorRgUtils {

    /**
     * Verifica e exclui um cliente.
     *
     * @param response A resposta da solicitação para excluir o cliente.
     * @param rg O RG do cliente a ser excluído.
     * @return Uma mensagem indicando o resultado da operação de exclusão.
     */
    public static String verificarEExcluirCliente(ResponseEntity<?> response, String rg) {
        if (response.getBody() instanceof Cliente) {
            return ClienteEnums.CLIENTE_EXCLUIDO.getMessage();
        } else {
            return ClienteEnums.CLIENTE_NAO_ENCONTRADO.getMessage();
        }
    }
}
