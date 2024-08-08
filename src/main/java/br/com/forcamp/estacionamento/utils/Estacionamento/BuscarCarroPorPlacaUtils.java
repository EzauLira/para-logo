package br.com.forcamp.estacionamento.utils.Estacionamento;

import java.util.Optional;

import br.com.forcamp.estacionamento.enums.EstacionamentoEnums;
import br.com.forcamp.estacionamento.model.RespostaPadrao;
import br.com.forcamp.estacionamento.model.estacionamento.Estacionamento;

/**
 * Classe de utilitários para buscar um carro por placa.
 * Esta classe contém métodos para verificar se um estacionamento é nulo.
 */
public class BuscarCarroPorPlacaUtils {

    /**
     * Verifica se um estacionamento é nulo.
     *
     * @param estacionamento O estacionamento a ser verificado.
     * @return Uma resposta padrão indicando que o estacionamento é nulo, ou vazio se o estacionamento não for nulo.
     */
    public static Optional<RespostaPadrao> verificarEstacionamentoNulo(Estacionamento estacionamento) {
        if (estacionamento == null) {

            return Optional.of(new RespostaPadrao(EstacionamentoEnums.VERIFICAR_ESTACIONAMENTO_NULO.getMessage()));
        }
        return Optional.empty();
    }
}
