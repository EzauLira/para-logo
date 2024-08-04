package br.com.forcamp.estacionamento.utils.Estacionamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.forcamp.estacionamento.enums.EstacionamentoEnums;
import br.com.forcamp.estacionamento.model.RespostaPadrao;
import br.com.forcamp.estacionamento.model.estacionamento.Estacionamento;
import br.com.forcamp.estacionamento.model.estacionamento.EstacionamentoComCusto;

/**
 * Classe de utilitários para listar carros que saíram.
 * Esta classe contém métodos para verificar se a lista de carros que saíram está vazia e para criar uma lista de carros que saíram com custo.
 */
public class ListarCarrosQueSairamUtils {

    /**
     * Verifica se a lista de carros que saíram está vazia.
     *
     * @param carrosQueSairam A lista de carros que saíram a ser verificada.
     * @return Uma resposta padrão indicando que a lista está vazia, ou vazio se a lista não estiver vazia.
     */
    public static Optional<RespostaPadrao> verificarListaVazia(List<Estacionamento> carrosQueSairam) {
        if (carrosQueSairam.isEmpty()) {
            return Optional.of(new RespostaPadrao(EstacionamentoEnums.LISTA_DE_CARROS_QUE_SAIRAM.getMessage()));
        }
        return Optional.empty();
    }

    /**
     * Cria uma lista de carros que saíram com custo.
     *
     * @param carrosQueSairam A lista de carros que saíram.
     * @return A lista de carros que saíram com custo.
     */

    public static List<EstacionamentoComCusto> criarListaComCusto(List<Estacionamento> carrosQueSairam) {
        List<EstacionamentoComCusto> carrosQueSairamComCusto = new ArrayList<>();
        for (Estacionamento carro : carrosQueSairam) {
            EstacionamentoComCusto carroComCusto = new EstacionamentoComCusto(carro);
            carrosQueSairamComCusto.add(carroComCusto);
        }
        return carrosQueSairamComCusto;
    }
}
