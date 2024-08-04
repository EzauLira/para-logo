package br.com.forcamp.estacionamento.utils.Estacionamento;

import br.com.forcamp.estacionamento.enums.EstacionamentoEnums;
import br.com.forcamp.estacionamento.exception.cliente.PlacaException;

import java.util.function.Predicate;

/**
 * Classe de utilitários para entradas de carros.
 * Esta classe contém métodos para validar se um carro já está registrado.
 */
public class EntradaUtils {

    /**
     * Valida se um carro já está registrado.
     *
     * @param placa A placa do carro a ser validada.
     * @param carroJaRegistrado Um predicado que verifica se um carro já está registrado.
     * @throws PlacaException Se a placa for inválida ou se o carro já estiver registrado.
     */
    public static void validarCarroJaRegistrado(String placa, Predicate<String> carroJaRegistrado) throws PlacaException {
        String placaAlfanumerica = placa.replaceAll("[^a-zA-Z0-9]", "");

        if (placaAlfanumerica.length() != 7) {
            throw new PlacaException(EstacionamentoEnums.FORMATO_PLACA.getMessage());
        }

        if (carroJaRegistrado.test(placa)) {
            throw new PlacaException(EstacionamentoEnums.PLACA.getMessage() + placa + EstacionamentoEnums.CARRO_JA_ESTA_ESTACIONADO.getMessage());
        }
    }
}
