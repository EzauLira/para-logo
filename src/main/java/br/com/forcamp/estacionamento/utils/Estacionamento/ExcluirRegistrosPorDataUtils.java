package br.com.forcamp.estacionamento.utils.Estacionamento;

import java.util.Optional;

import br.com.forcamp.estacionamento.enums.EstacionamentoEnums;
import br.com.forcamp.estacionamento.model.RespostaPadrao;

/**
 * Classe de utilitários para excluir registros por data.
 * Esta classe contém métodos para verificar se os registros foram excluídos.
 */
public class ExcluirRegistrosPorDataUtils {

    /**
     * Verifica se os registros foram excluídos.
     *
     * @param registrosExcluidos O número de registros excluídos.
     * @return Uma resposta padrão indicando que os registros foram excluídos, ou vazio se nenhum registro foi excluído.
     */
    public static Optional<RespostaPadrao> verificarRegistrosExcluidos(int registrosExcluidos) {
        if (registrosExcluidos > 0) {
            return Optional.of(new RespostaPadrao(EstacionamentoEnums.REGISTRO_EXCLUIDOS.getMessage()));
        }
        return Optional.empty();
    }
}
