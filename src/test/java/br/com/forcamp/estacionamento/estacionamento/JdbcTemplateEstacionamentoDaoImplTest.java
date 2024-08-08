package br.com.forcamp.estacionamento.estacionamento;

import br.com.forcamp.estacionamento.dao.impl.JdbcTemplateEstacionamentoDaoImpl;
import br.com.forcamp.estacionamento.model.estacionamento.Estacionamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class JdbcTemplateEstacionamentoDaoImplTest {

    @InjectMocks
    private JdbcTemplateEstacionamentoDaoImpl dao;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarEntrada() {
        Estacionamento estacionamento = new Estacionamento();
        dao.registrarEntrada(estacionamento);
        verify(jdbcTemplate).execute(anyString(), any(PreparedStatementCallback.class));
    }

    @Test
    void testRegistrarSaida() {
        Estacionamento estacionamento = new Estacionamento();
        dao.registrarSaida(estacionamento);

        verify(jdbcTemplate).execute(anyString(), any(PreparedStatementCallback.class));
    }

    @Test
    void testCarroJaRegistrado() {
        String placa = "ABC1234";
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(Class.class))).thenReturn(1);
        boolean resultado = dao.carroJaRegistrado(placa);

        verify(jdbcTemplate).queryForObject(anyString(), any(Object[].class), any(Class.class));
        assertEquals(true, resultado);
    }

    @Test
    void testCarroJaSaiuDoRegistro() {
        String placa = "ABC1234";
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(Class.class))).thenReturn(0);
        boolean resultado = dao.carroJaSaiuDoRegistro(placa);

        verify(jdbcTemplate).queryForObject(anyString(), any(Object[].class), any(Class.class));
        assertEquals(false, resultado);
    }

    @Test
    void testFindAll() {
        List<Estacionamento> estacionamentosEsperados = Collections.singletonList(new Estacionamento());
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(estacionamentosEsperados);
        List<Estacionamento> resultado = dao.findAll();

        verify(jdbcTemplate).query(anyString(), any(RowMapper.class));
        assertEquals(estacionamentosEsperados, resultado);
    }

    @Test
    void testListarCarrosAtivos() {
        List<Estacionamento> estacionamentosEsperados = Collections.singletonList(new Estacionamento());
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(estacionamentosEsperados);
        List<Estacionamento> resultado = dao.listarCarrosAtivos();

        verify(jdbcTemplate).query(anyString(), any(RowMapper.class));
        assertEquals(estacionamentosEsperados, resultado);
    }

    @Test
    void testListarCarrosQueSairam() {
        List<Estacionamento> estacionamentosEsperados = Collections.singletonList(new Estacionamento());
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(estacionamentosEsperados);
        List<Estacionamento> resultado = dao.listarCarrosQueSairam();

        verify(jdbcTemplate).query(anyString(), any(RowMapper.class));
        assertEquals(estacionamentosEsperados, resultado);
    }

    @Test
    void testCalcularCusto() {
        String placa = "ABC1234";
        double custoEsperado = 10.0;
        when(jdbcTemplate.execute(anyString(), any(PreparedStatementCallback.class))).thenReturn(custoEsperado);
        double resultado = dao.calcularCusto(placa);

        verify(jdbcTemplate).execute(anyString(), any(PreparedStatementCallback.class));
        assertEquals(custoEsperado, resultado, 0.001);
    }

    @Test
    void testExcluirRegistrosPorData() {
        String dataEntrada = "2022-01-01";
        int countEsperado = 1;
        when(jdbcTemplate.update(anyString(), anyString())).thenReturn(countEsperado);
        int resultado = dao.excluirRegistrosPorData(dataEntrada);

        verify(jdbcTemplate).update(anyString(), anyString());
        assertEquals(countEsperado, resultado);
    }

    @Test
    void testDiminuirVagas() {
        dao.diminuirVagas();
        verify(jdbcTemplate).update(anyString());
    }

    @Test
    void testAumentarVagas() {
        dao.aumentarVagas();
        verify(jdbcTemplate).update(anyString());
    }

    @Test
    void testGetVagasDisponiveis() {
        int vagasEsperadas = 10;
        when(jdbcTemplate.queryForObject(anyString(), any(Class.class))).thenReturn(vagasEsperadas);
        int resultado = dao.getVagasDisponiveis();

        verify(jdbcTemplate).queryForObject(anyString(), any(Class.class));
        assertEquals(vagasEsperadas, resultado);
    }

}
