package br.com.forcamp.estacionamento.utils.Estacionamento;

import br.com.forcamp.estacionamento.dao.impl.JdbcTemplateEstacionamentoDaoImpl;
import br.com.forcamp.estacionamento.enums.EstacionamentoEnums;
import br.com.forcamp.estacionamento.exception.cliente.PlacaException;
import br.com.forcamp.estacionamento.model.estacionamento.Estacionamento;

/**
 * Classe de utilitários para saídas de carros.
 * Esta classe contém métodos para validar se um carro já saiu e se um estacionamento é nulo.
 */
public class SaidaUtils {

    /**
     * Valida se um carro já saiu.
     *
     * @param jdbcTemplate O objeto JdbcTemplateEstacionamentoDaoImpl a ser usado para verificar se o carro já saiu.
     * @param placa A placa do carro a ser verificada.
     * @throws PlacaException Se o carro já saiu.
     */
    public static void validarCarroJaSaiu(JdbcTemplateEstacionamentoDaoImpl jdbcTemplate, String placa) throws PlacaException {
        if (jdbcTemplate.carroJaSaiuDoRegistro(placa)) {
            throw new PlacaException(EstacionamentoEnums.PLACA.getMessage() + placa + EstacionamentoEnums.CARRO_JA_SAIU.getMessage());
        }
    }

    /**
     * Valida se um estacionamento é nulo.
     *
     * @param estacionamento O estacionamento a ser verificado.
     * @param placa A placa do carro a ser verificada.
     * @throws PlacaException Se o estacionamento é nulo.
     */
    public static void validarEstacionamentoNaoNulo(Estacionamento estacionamento, String placa) throws PlacaException {
        if (estacionamento == null) {
            throw new PlacaException(EstacionamentoEnums.NENHUM_CARRO_ENCONTRADO.getMessage() + placa);
        }
    }
}
