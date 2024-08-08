package br.com.forcamp.estacionamento.utils.Estacionamento;

import br.com.forcamp.estacionamento.enums.EstacionamentoEnums;
import br.com.forcamp.estacionamento.model.RespostaPadrao;
import br.com.forcamp.estacionamento.model.estacionamento.Estacionamento;
import br.com.forcamp.estacionamento.model.estacionamento.EstacionamentoComCusto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe de utilitários para listar carros ativos.
 * Esta classe contém métodos para verificar se a lista de carros ativos está vazia e para criar uma lista de carros ativos com custo.
 */
public class ListarCarrosAtivosUtils {

    /**
     * Verifica se a lista de carros ativos está vazia.
     *
     * @param carrosAtivos A lista de carros ativos a ser verificada.
     * @return Uma resposta padrão indicando que a lista está vazia, ou vazio se a lista não estiver vazia.
     */
    public static Optional<RespostaPadrao> verificarListaVazia(List<Estacionamento> carrosAtivos) {
        if (carrosAtivos.isEmpty()) {
            return Optional.of(new RespostaPadrao(EstacionamentoEnums.VERIFICAR_LISTA_VAZIA.getMessage()));
        }
        return Optional.empty();
    }

    /**
     * Cria uma lista de carros ativos com custo.
     *
     * @param carrosAtivos A lista de carros ativos.
     * @return A lista de carros ativos com custo.
     */
    public static List<EstacionamentoComCusto> criarListaComCusto(List<Estacionamento> carrosAtivos) {
        List<EstacionamentoComCusto> carrosAtivosComCusto = new ArrayList<>();
        for (Estacionamento carro : carrosAtivos) {
            EstacionamentoComCusto carroComCusto = new EstacionamentoComCusto(carro);
            carrosAtivosComCusto.add(carroComCusto);
        }
        return carrosAtivosComCusto;
    }
}
