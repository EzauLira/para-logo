package br.com.forcamp.estacionamento.utils.Estacionamento;

import br.com.forcamp.estacionamento.enums.EstacionamentoEnums;
import br.com.forcamp.estacionamento.exception.estacionamento.VagaException;
import br.com.forcamp.estacionamento.exception.estacionamento.VagaInvalidaException;

import java.util.function.Supplier;

/**
 * Classe de utilitários para vagas.
 * Esta classe contém métodos para validar se há vagas disponíveis.
 */
public class VagasUtils {

    /**
     * Valida se há vagas disponíveis.
     *
     * @param getVagasDisponiveis Um fornecedor que retorna o número de vagas disponíveis.
     * @throws VagaException Se não houver vagas disponíveis.
     * @throws VagaInvalidaException Se a vaga for inválida.
     */
    public static void validarVagasDisponiveis(Supplier<Integer> getVagasDisponiveis) throws VagaException, VagaInvalidaException {
        if (getVagasDisponiveis.get() <= 0) {
            throw new VagaInvalidaException(EstacionamentoEnums.VALIDAR_VAGA.getMessage());
        }
    }
}
