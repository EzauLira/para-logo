package br.com.forcamp.estacionamento.cliente;

import br.com.forcamp.estacionamento.dao.impl.JdbcTemplateClienteClienteDaoImpl;
import br.com.forcamp.estacionamento.model.cliente.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JdbcTemplateClienteClienteDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private JdbcTemplateClienteClienteDaoImpl dao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSalvarDadosCadastrais() {
        Cliente cliente = new Cliente();
        dao.salvarDadosCadastrais(cliente);
        verify(jdbcTemplate).execute(anyString(), any(PreparedStatementCallback.class));
    }

    @Test
    void testFindAll() {
        List<Cliente> clientesEsperados = Collections.singletonList(new Cliente());
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(clientesEsperados);
        List<Cliente> resultado = dao.findAll();

        verify(jdbcTemplate).query(anyString(), any(RowMapper.class));
        assertEquals(clientesEsperados, resultado);
    }

    @Test
    void testBuscarClientePorRg() {
        String rg = "12345678";
        Cliente clienteEsperado = new Cliente();
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class))).thenReturn(Collections.singletonList(clienteEsperado));
        Cliente resultado = dao.buscarClientePorRg(rg);

        verify(jdbcTemplate).query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class));
        assertEquals(clienteEsperado, resultado);
    }

    @Test
    void testExcluirClientePorRg() {
        String rg = "12345678";
        int countEsperado = 1;
        when(jdbcTemplate.update(anyString(), anyString())).thenReturn(countEsperado);
        int resultado = dao.excluirClientePorRg(rg);

        verify(jdbcTemplate).update(anyString(), anyString());
        assertEquals(countEsperado, resultado);
    }

    @Test
    void testExcluirClientesPorDataRegistro() {
        String dataRegistro = "2022-01-01";
        int countEsperado = 1;
        when(jdbcTemplate.update(anyString(), anyString())).thenReturn(countEsperado);
        int resultado = dao.excluirClientesPorDataRegistro(dataRegistro);

        verify(jdbcTemplate).update(anyString(), anyString());
        assertEquals(countEsperado, resultado);
    }

    @Test
    void testBuscarClientesPorDataRegistro() {
        String dataRegistro = "2022-01-01";
        List<Cliente> clientesEsperados = Collections.singletonList(new Cliente());
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class))).thenReturn(clientesEsperados);

        List<Cliente> resultado = dao.buscarClientesPorDataRegistro(dataRegistro);

        verify(jdbcTemplate).query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class));
        assertEquals(clientesEsperados, resultado);
    }
}
