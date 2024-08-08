package br.com.forcamp.estacionamento.dao.impl;

import br.com.forcamp.estacionamento.dao.IArmazenamentoJsonDao;
import br.com.forcamp.estacionamento.model.cliente.Cliente;
import br.com.forcamp.estacionamento.model.estacionamento.Estacionamento;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Implementação do DAO para armazenamento em JSON.
 * Esta classe é responsável por realizar operações de armazenamento de dados de clientes e estacionamentos em formato JSON.
 */
@Service
public class ArmazenamentoJsonDaoImpl  implements IArmazenamentoJsonDao {

    private static final Logger logger = LoggerFactory.getLogger(ArmazenamentoJsonDaoImpl.class);

    /**
     * Salva os dados de um cliente em formato JSON.
     *
     * @param cliente O cliente cujos dados serão salvos.
     * @Override é usada para indicar que este método está sobrescrevendo um método da superclasse.
     */
    @Override
    public void salvarClienteJson(Cliente cliente) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(cliente);

        try (FileWriter writer = new FileWriter("cliente json"))
        {
            writer.write(json);
        }catch (IOException e)
        {
            logger.error("Ocorreu um erro ao salvar o JSON do cliente", e);
        }
    }

    /**
     * Salva os dados de entrada de um estacionamento em formato JSON.
     *
     * @param estacionamento O estacionamento cujos dados de entrada serão salvos.
     * @Override é usada para indicar que este método está sobrescrevendo um método da superclasse.
     */
    @Override
    public void salvarEntadaJson(Estacionamento estacionamento)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(estacionamento);

        try (FileWriter writer = new FileWriter("vaga json"))
        {
            writer.write(json);
        }catch (IOException e)
        {
            logger.error("Ocorreu um erro ao salvar o JSON da entrada", e);
        }
    }

    /**
     * Atualiza os dados de saída de um estacionamento em formato JSON.
     *
     * @param estacionamento O estacionamento cujos dados de saída serão atualizados.
     * @Override é usada para indicar que este método está sobrescrevendo um método da superclasse.
     */
    @Override
    public void atualizarSaidaJson(Estacionamento estacionamento)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(estacionamento);

        try (FileWriter writer = new FileWriter("vaga json"))
        {
            writer.write(json);
        }catch (IOException e)
        {
            logger.error("Ocorreu um erro ao atualizar o JSON da saida", e);
        }
    }
}
