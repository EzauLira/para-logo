package br.com.forcamp.estacionamento.dao;

import br.com.forcamp.estacionamento.model.cliente.Cliente;
import br.com.forcamp.estacionamento.model.estacionamento.Estacionamento;

/**
 * Interface para operações de armazenamento em JSON.
 * Esta interface define métodos para salvar e atualizar dados de clientes e estacionamentos em formato JSON.
 */
public interface IArmazenamentoJsonDao {

    /**
     * Salva os dados de um cliente em formato JSON.
     *
     * @param cliente O cliente cujos dados serão salvos.
     */
    public void salvarClienteJson(Cliente cliente);

    /**
     * Salva os dados de entrada de um estacionamento em formato JSON.
     *
     * @param estacionamento O estacionamento cujos dados de entrada serão salvos.
     */
    public void salvarEntadaJson(Estacionamento estacionamento);

    /**
     * Atualiza os dados de saída de um estacionamento em formato JSON.
     *
     * @param estacionamento O estacionamento cujos dados de saída serão atualizados.
     */
    public void atualizarSaidaJson(Estacionamento estacionamento);

}
