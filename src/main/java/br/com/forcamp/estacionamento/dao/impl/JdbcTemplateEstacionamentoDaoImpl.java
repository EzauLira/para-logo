package br.com.forcamp.estacionamento.dao.impl;

import br.com.forcamp.estacionamento.dao.IJdbcTemplateEstacionamentoDao;
import br.com.forcamp.estacionamento.model.estacionamento.Estacionamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementação do DAO para Estacionamento usando JdbcTemplate.
 * Esta classe é responsável por realizar operações de banco de dados relacionadas ao Estacionamento.
 */
@Service
public class JdbcTemplateEstacionamentoDaoImpl implements IJdbcTemplateEstacionamentoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Registra a entrada de um carro no estacionamento.
     *
     * @param estacionamento O estacionamento onde o carro entrará.
     * @Override é usada para indicar que este método está sobrescrevendo um método da superclasse.
     * @Transactional indica que este método deve ser executado dentro de uma transação.
     */
    @Override
    @Transactional
    public void registrarEntrada(Estacionamento estacionamento) {
        String sql = "SELECT public.registrar_entrada( ?, ?, ? )";

        jdbcTemplate.execute(sql, (PreparedStatementCallback<Void>) PreparedStatement -> {
            PreparedStatement.setString(1, estacionamento.getPlaca());
            PreparedStatement.setString(2, estacionamento.getDataEntrada());
            PreparedStatement.setString(3, estacionamento.getHoraEntrada());
            PreparedStatement.execute();
            return null;
        });

        diminuirVagas();
    }

    /**
     * Registra a saída de um carro do estacionamento.
     *
     * @param estacionamento O estacionamento de onde o carro sairá.
     * @Override é usada para indicar que este método está sobrescrevendo um método da superclasse.
     */
    @Override
    public void registrarSaida(Estacionamento estacionamento) {
        String sql = "SELECT public.registrar_saida( ?, ?, ? )";

        jdbcTemplate.execute(sql, (PreparedStatementCallback<Void>) PreparedStatement -> {
            PreparedStatement.setString(1, estacionamento.getPlaca());
            PreparedStatement.setString(2, estacionamento.getDataSaida());
            PreparedStatement.setString(3, estacionamento.getHoraSaida());
            PreparedStatement.execute();
            return null;
        });

        aumentarVagas();
    }

    /**
     * Verifica se um carro já está registrado no estacionamento.
     *
     * @param placa A placa do carro a ser verificado.
     * @return Verdadeiro se o carro já está registrado, falso caso contrário.
     * @Override é usada para indicar que este método está sobrescrevendo um método da superclasse.
     */
    @Override
    public boolean carroJaRegistrado(String placa) {
        String sql = "SELECT COUNT(*) FROM registro WHERE placa = ? AND datasaida IS NULL AND horasaida IS NULL";
        int count = jdbcTemplate.queryForObject(sql, new Object[]{placa}, Integer.class);
        return count > 0;
    }

    /**
     * Verifica se um carro já saiu do registro do estacionamento.
     *
     * @param placa A placa do carro a ser verificado.
     * @return Verdadeiro se o carro já saiu do registro, falso caso contrário.
     * @Override é usada para indicar que este método está sobrescrevendo um método da superclasse.
     */
    @Override
    public boolean carroJaSaiuDoRegistro(String placa) {
        String sql = "SELECT COUNT(*) FROM registro WHERE placa = ? AND TO_DATE(dataentrada, 'YYYY-MM-DD') = CURRENT_DATE AND datasaida IS NOT NULL AND horasaida IS NOT NULL";
        int count = jdbcTemplate.queryForObject(sql, new Object[]{placa}, Integer.class);
        return count > 0;
    }

    /**
     * Busca um carro por placa no estacionamento.
     *
     * @param placa A placa do carro a ser buscado.
     * @return O estacionamento onde o carro está ou null se o carro não foi encontrado.
     * @Override é usada para indicar que este método está sobrescrevendo um método da superclasse.
     */
    @Override
    public Estacionamento bucarCarroPorPlaca(String placa) {
        String sql = "SELECT * FROM registro WHERE placa = ?";
        List<Estacionamento> estacionamentos = jdbcTemplate.query(sql, new Object[]{placa}, new BeanPropertyRowMapper<>(Estacionamento.class));
        return estacionamentos.isEmpty() ? null : estacionamentos.get(0);
    }

    /**
     * Busca todos os carros no estacionamento.
     *
     * @return Uma lista de todos os carros no estacionamento.
     * @Override é usada para indicar que este método está sobrescrevendo um método da superclasse.
     */
    @Override
    public List<Estacionamento> findAll() {
        String sql = "SELECT * FROM public.registro";

        RowMapper<Estacionamento> rowMapper = new RowMapper<Estacionamento>() {
            @Override
            public Estacionamento mapRow(ResultSet rs, int rowNum) throws SQLException {
                Estacionamento estacionamento = new Estacionamento();
                estacionamento.setPlaca(rs.getString("placa"));
                estacionamento.setDataEntrada(rs.getString("dataEntrada"));
                estacionamento.setHoraEntrada(rs.getString("horaEntrada"));
                estacionamento.setDataSaida(rs.getString("dataSaida"));
                estacionamento.setHoraSaida(rs.getString("horaSaida"));
                return estacionamento;
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    /**
     * Lista todos os carros ativos no estacionamento.
     *
     * @return Uma lista de todos os carros ativos no estacionamento.
     * @Override é usada para indicar que este método está sobrescrevendo um método da superclasse.
     */
    @Override
    public List<Estacionamento> listarCarrosAtivos() {
        String sql = "SELECT * FROM registro WHERE dataSaida IS NULL";

        RowMapper<Estacionamento> rowMapper = new BeanPropertyRowMapper<>(Estacionamento.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    /**
     * Lista todos os carros que saíram do estacionamento.
     *
     * @return Uma lista de todos os carros que saíram do estacionamento.
     * @Override é usada para indicar que este método está sobrescrevendo um método da superclasse.
     */
    @Override
    public List<Estacionamento> listarCarrosQueSairam() {
        String sql = "SELECT * FROM registro WHERE dataSaida IS NOT NULL";

        RowMapper<Estacionamento> rowMapper = new BeanPropertyRowMapper<>(Estacionamento.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    /**
     * Calcula o custo para um carro específico.
     *
     * @Override é usada para indicar que este método está sobrescrevendo um método da superclasse.
     * @param placa A placa do carro para o qual o custo será calculado.
     * @return O custo para o carro.
     */
    @Override
    public double calcularCusto(String placa) {
        String sql = "SELECT public.calcular_custo( ? )";
        return jdbcTemplate.execute(sql, (PreparedStatementCallback<Double>) PreparedStatement -> {
            PreparedStatement.setString(1, placa);
            ResultSet rs = PreparedStatement.executeQuery();
            return rs.next() ? rs.getDouble(1) : 0.0;
        });
    }

    /**
     * Exclui registros por data de entrada.
     *
     * @Override é usada para indicar que este método está sobrescrevendo um método da superclasse.
     * @param dataEntrada A data de entrada dos registros a serem excluídos.
     * @return O número de linhas afetadas pela operação de exclusão.
     */
    @Override
    public int excluirRegistrosPorData(String dataEntrada) {
        String sql = "DELETE FROM registro WHERE dataEntrada = ?";
        return jdbcTemplate.update(sql, dataEntrada);
    }

    /**
     * Diminui o número de vagas disponíveis no estacionamento.
     * @Override é usada para indicar que este método está sobrescrevendo um método da superclasse.
     */
    @Override
    public void diminuirVagas() {
        String sql = "UPDATE estacionamento_vagas SET vagas_disponiveis = vagas_disponiveis - 1 WHERE id = 1";
        jdbcTemplate.update(sql);
    }

    /**
     * Aumenta o número de vagas disponíveis no estacionamento.
     * @Override é usada para indicar que este método está sobrescrevendo um método da superclasse.
     */
    @Override
    public void aumentarVagas() {
        String sql = "UPDATE estacionamento_vagas SET vagas_disponiveis = vagas_disponiveis + 1 WHERE id = 1";
        jdbcTemplate.update(sql);
    }

    /**
     * Obtém o número de vagas disponíveis no estacionamento.
     *
     * @Override é usada para indicar que este método está sobrescrevendo um método da superclasse.
     * @return O número de vagas disponíveis.
     */
    @Override
    public int getVagasDisponiveis() {
        String sql = "SELECT vagas_disponiveis FROM estacionamento_vagas WHERE id = 1";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}