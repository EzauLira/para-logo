package br.com.forcamp.estacionamento.utils.Estacionamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.forcamp.estacionamento.enums.EstacionamentoEnums;
import br.com.forcamp.estacionamento.model.RespostaPadrao;
import br.com.forcamp.estacionamento.model.estacionamento.Estacionamento;
import br.com.forcamp.estacionamento.model.estacionamento.EstacionamentoComCusto;

/**
 * Classe de utilitários para listar todos os registros.
 * Esta classe contém métodos para verificar se a lista completa de registros está vazia e para criar uma lista completa de registros com custo.
 */
public class ListarTodosRegistrosUtils {

    /**
     * Verifica se a lista completa de registros está vazia.
     *
     * @param estaciona A lista completa de registros a ser verificada.
     * @return Uma resposta padrão indicando que a lista está vazia, ou vazio se a lista não estiver vazia.
     */
    public static Optional<RespostaPadrao> verificarListaCompleta(List<Estacionamento> estaciona) {
        if (estaciona.isEmpty()) {
            return Optional.of(new RespostaPadrao(EstacionamentoEnums.VERIFICAR_LISTA_COMPLETA.getMessage()));
        }
        return Optional.empty();
    }

    /**
     * Cria uma lista completa de registros com custo.
     *
     * @param carrosAtivos A lista completa de registros.
     * @return A lista completa de registros com custo.
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
