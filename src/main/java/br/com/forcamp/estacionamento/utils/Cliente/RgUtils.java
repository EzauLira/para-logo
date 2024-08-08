package br.com.forcamp.estacionamento.utils.Cliente;

import br.com.forcamp.estacionamento.exception.cliente.RgException;

/**
 * Classe de utilitários para RGs.
 * Esta classe contém métodos para validar um RG.
 */
public class RgUtils {

    /**
     * Valida um RG.
     *
     * @param rg O RG a ser validado.
     * @throws RgException Se o RG for inválido.
     */
    public static void validarRg(String rg) throws RgException {
        new RgException(rg);
    }
}
