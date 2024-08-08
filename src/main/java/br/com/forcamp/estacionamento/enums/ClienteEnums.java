package br.com.forcamp.estacionamento.enums;

import lombok.Getter;

/**
 * Enumeração para mensagens relacionadas a clientes.
 * Esta enumeração define várias mensagens que podem ser usadas ao lidar com clientes.
 */
@Getter
public enum ClienteEnums {

    CLIENTE_JA_EXISTE("Cliente já existe no banco de dados"),
    CADASTRO_COM_SUCESSO("Cliente cadastrado com sucesso!"),
    NENHUM_CLIENTE_CADASTRADO("Não há nenhum cliente cadastrado."),
    CLIENTE_NAO_ENCONTRADO("Não foi encontrado nenhum cliente com o RG informado."),
    CLIENTE_EXCLUIDO("Cliente excluído com sucesso!"),
    CLIENTE_NAO_ENCONTRADO_DATA("Nenhum cliente encontrado na data informada."),
    EXCLUIR_TODOS("Todos os clientes da data informada foram excluídos com sucesso!");



    private final String message;

    ClienteEnums(String message)
    {
        this.message = message;
    }

}
