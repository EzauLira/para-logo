package br.com.forcamp.estacionamento.dao;

import br.com.forcamp.estacionamento.model.cliente.Cliente;

import java.util.List;

/**
 * Interface para operações de banco de dados relacionadas ao Cliente usando JdbcTemplate.
 * Esta interface define métodos para salvar, buscar, excluir e listar dados de clientes.
 */
public interface IJdbcTemplateClienteDao {

    public void salvarDadosCadastrais(Cliente cliente);
    List<Cliente> findAll();
    Cliente buscarClientePorRg(String rg);
    int excluirClientePorRg(String rg);
    int excluirClientesPorDataRegistro(String dataRegistro);
    List<Cliente> buscarClientesPorDataRegistro(String dataRegistro);
}
