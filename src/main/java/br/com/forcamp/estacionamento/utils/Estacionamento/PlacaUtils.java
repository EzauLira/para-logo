package br.com.forcamp.estacionamento.utils.Estacionamento;

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
        new PlacaException(placa);

    }
}
