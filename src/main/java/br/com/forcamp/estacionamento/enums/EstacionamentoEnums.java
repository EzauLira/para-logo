package br.com.forcamp.estacionamento.enums;

import lombok.Getter;

/**
 * Enumeração para mensagens relacionadas a estacionamentos.
 * Esta enumeração define várias mensagens que podem ser usadas ao lidar com estacionamentos.
 */
@Getter
public enum EstacionamentoEnums {

    CUSTO_CALCULO("Custo para veículo de placa informada: "),
    CARRO_NAO_ENCONTRADO("Não existe nenhum veículo registrado no estacionamento com a placa informada."),
    REGISTRO_EXCLUIDOS("Registros excluídos com sucesso."),
    EXCLUIR_REGISTRO_POR_DATA("Nenhum registro encontrado para a data fornecida."),
    LISTA_DE_CARROS_QUE_SAIRAM("Ainda nenhum carro saiu do estacionamento, ou não há registros de veículos."),
    VERIFICAR_LISTA_VAZIA("Nenhum carro ativo encontrado."),
    VERIFICAR_ESTACIONAMENTO_NULO("Não foi encontrado nenhum carro com o Placa informado."),
    VERIFICAR_LISTA_COMPLETA("Não há nenhum carro cadastrado."),
    ERRO_DATA_FORNECIDA("Erro ao processar a data fornecida."),
    PLACA("Veículo de placa: "),
    CARRO_JA_ESTA_ESTACIONADO(" já está no estacionamento!"),
    CARRO_JA_SAIU(" já saiu do estacionamento!"),
    SAIU_DO_ESTACIONAMENTO(" saiu do estacionamento! Vagas disponíveis: "),
    ENTROU_NO_ESTACIONAMENTO(" entrou no estacionamento! Vagas disponíveis: "),
    VALIDAR_VAGA("Não há vagas disponíveis."),
    FORMATO_PLACA("A placa do carro deve ser do formato: XXX1234 ou AAA9A99 (7 dígitos)."),
    NENHUM_CARRO_ENCONTRADO("Nenhum carro encontrado com a placa: ");

    private final String message;

    EstacionamentoEnums(String message)
    {
        this.message = message;
    }

}
