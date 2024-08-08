package br.com.forcamp.estacionamento.utils.Cliente;

import br.com.forcamp.estacionamento.enums.EstacionamentoEnums;
import br.com.forcamp.estacionamento.exception.cliente.PlacaException;

/**
 * Classe de utilitários para placas.
 * Esta classe contém métodos para validar uma placa.
 */
public class PlacaUtils {

    /**
     * Valida uma placa.
     *
     * @param placa A placa a ser validada.
     * @throws PlacaException Se a placa for inválida.
     */
    public static void validarPlaca(String placa) throws PlacaException {
        String placaAlfanumerica = placa.replaceAll("[^a-zA-Z0-9]", "");

        if (placaAlfanumerica.length() != 7) {
            throw new PlacaException(EstacionamentoEnums.FORMATO_PLACA.getMessage());
        }
        new PlacaException(placa);
    }
}
