package br.com.forcamp.estacionamento.dao.impl;

import br.com.forcamp.estacionamento.dao.IJdbcTemplateClienteDao;
import br.com.forcamp.estacionamento.model.cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementação do DAO para Cliente usando JdbcTemplate.
 * Esta classe é responsável por realizar operações de banco de dados relacionadas ao Cliente.
 */
@Service
public class JdbcTemplateClienteDaoImpl implements IJdbcTemplateClienteDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Salva os dados cadastrais de um cliente no banco de dados.
     *
     * @param cliente O cliente cujos dados cadastrais serão salvos.
     * @Override é usada para indicar que este método está sobrescrevendo um método da superclasse.
     * @Transactional indica que este método deve ser executado dentro de uma transação.
     */
    @Override
    @Transactional
    public void salvarDadosCadastrais(Cliente cliente)
    {
        String sql = "SELECT public.cadastrar_cliente( ?, ?, ?, ?, ? )";

        jdbcTemplate.execute(sql, (PreparedStatementCallback<Void>) PreparedStatement -> {
            PreparedStatement.setString(1, cliente.getNome());
            PreparedStatement.setString(2, cliente.getRg());
            PreparedStatement.setString(3, cliente.getTelefone());
            PreparedStatement.setString(4, cliente.getPlacaVeiculo());
            PreparedStatement.setDate(5, Date.valueOf(cliente.getDataRegistro()));
            PreparedStatement.execute();
            return null;
        });
    }

    /**
     * Busca todos os clientes no banco de dados.
     *
     * @return Uma lista de todos os clientes.
     */
    public List<Cliente> findAll() {
        String sql = "SELECT * FROM public.clientebase";

        RowMapper<Cliente> rowMapper = new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getString("rg"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setPlacaVeiculo(rs.getString("placaVeiculo"));
                cliente.setDataRegistro(rs.getString("dataRegistro"));
                return cliente;
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    /**
     * Busca um cliente específico por RG no banco de dados.
     *
     * @param rg O RG do cliente a ser buscado.
     * @return O cliente encontrado ou null se nenhum cliente foi encontrado com o RG fornecido.
     */
    public Cliente buscarClientePorRg(String rg) {
        String sql = "SELECT * FROM ClienteBase WHERE rg = ?";
        List<Cliente> clientes = jdbcTemplate.query(sql, new Object[]{rg}, new BeanPropertyRowMapper<>(Cliente.class));
        return clientes.isEmpty() ? null : clientes.get(0);
    }

    /**
     * Exclui um cliente específico por RG do banco de dados.
     *
     * @param rg O RG do cliente a ser excluído.
     * @return O número de linhas afetadas pela operação de exclusão.
     */
    public int excluirClientePorRg(String rg) {
        String sql = "DELETE FROM ClienteBase WHERE rg = ?";
        return jdbcTemplate.update(sql, rg);
    }

    /**
     * Exclui clientes por data de registro do banco de dados.
     *
     * @param dataRegistro A data de registro dos clientes a serem excluídos.
     * @return O número de linhas afetadas pela operação de exclusão.
     */
    public int excluirClientesPorDataRegistro(String dataRegistro) {
        String sql = "DELETE FROM ClienteBase WHERE dataRegistro = CAST(? AS DATE)";
        return jdbcTemplate.update(sql, dataRegistro);

    }

    /**
     * Busca clientes por data de registro no banco de dados.
     *
     * @param dataRegistro A data de registro dos clientes a serem buscados.
     * @return Uma lista de clientes que foram registrados na data fornecida.
     */
    public List<Cliente> buscarClientesPorDataRegistro(String dataRegistro) {
        String sql = "SELECT * FROM ClienteBase WHERE dataRegistro = CAST(? AS DATE)";
        List<Cliente> clientes = jdbcTemplate.query(sql, new Object[]{dataRegistro}, new BeanPropertyRowMapper<>(Cliente.class));
        return clientes;
    }
}