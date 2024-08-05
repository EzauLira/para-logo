package br.com.forcamp.estacionamento.cliente;

import br.com.forcamp.estacionamento.dtos.ClienteRequestDTO;
import br.com.forcamp.estacionamento.enums.ClienteEnums;
import br.com.forcamp.estacionamento.exception.cliente.*;
import br.com.forcamp.estacionamento.model.RespostaPadrao;
import br.com.forcamp.estacionamento.model.cliente.Cliente;
import br.com.forcamp.estacionamento.dao.impl.JdbcTemplateClienteDaoImpl;
import br.com.forcamp.estacionamento.dao.impl.ArmazenamentoJsonDaoImpl;
import br.com.forcamp.estacionamento.usecase.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private JdbcTemplateClienteDaoImpl jdbcTemplateClienteDaoImpl;

    @Mock
    private ArmazenamentoJsonDaoImpl armazenamentoJsonDaoImpl;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void salvarDadosCadastrais_Sucesso() throws NomeException, RgException, TelefoneException, PlacaException, DataException {
        ClienteRequestDTO request = new ClienteRequestDTO("Nome Teste", "123456789", "99999999999", "ABC1234", "01/01/2020");
        Cliente cliente = Cliente.builder()
                .nome("Nome Teste")
                .rg("123456789")
                .telefone("99999999999")
                .placaVeiculo("ABC1234")
                .dataRegistro("01/01/2020")
                .build();

        when(jdbcTemplateClienteDaoImpl.buscarClientePorRg("123456789")).thenReturn(null);
        doNothing().when(jdbcTemplateClienteDaoImpl).salvarDadosCadastrais(any(Cliente.class));
        doNothing().when(armazenamentoJsonDaoImpl).salvarClienteJson(any(Cliente.class));

        ResponseEntity<RespostaPadrao> resposta = clienteService.salvarDadosCadastrais(request);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(ClienteEnums.CADASTRO_COM_SUCESSO.getMessage(), resposta.getBody().getMessage());
    }

    @Test
    void salvarDadosCadastrais_ClienteJaExiste() throws NomeException, RgException, TelefoneException, PlacaException, DataException {
        ClienteRequestDTO request = new ClienteRequestDTO("Nome Teste", "123456789", "99999999999", "ABC1234", "01/01/2020");
        Cliente cliente = Cliente.builder().rg("123456789").build();

        when(jdbcTemplateClienteDaoImpl.buscarClientePorRg("123456789")).thenReturn(cliente);

        ResponseEntity<RespostaPadrao> resposta = clienteService.salvarDadosCadastrais(request);

        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertEquals(ClienteEnums.CLIENTE_JA_EXISTE.getMessage(), resposta.getBody().getMessage());
    }

    @Test
    void buscarTodosClientes_Sucesso() {
        when(jdbcTemplateClienteDaoImpl.findAll()).thenReturn(Collections.singletonList(new Cliente()));

        ResponseEntity<?> resposta = clienteService.buscarTodosClientes();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void buscarTodosClientes_NenhumClienteEncontrado() {
        when(jdbcTemplateClienteDaoImpl.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<?> resposta = clienteService.buscarTodosClientes();

        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
        assertEquals(ClienteEnums.NENHUM_CLIENTE_CADASTRADO.getMessage(), ((RespostaPadrao) resposta.getBody()).getMessage());
    }

    @Test
    void buscarClientePorRg_Sucesso() {
        Cliente cliente = Cliente.builder().rg("123456789").build();

        when(jdbcTemplateClienteDaoImpl.buscarClientePorRg("123456789")).thenReturn(cliente);

        ResponseEntity<?> resposta = clienteService.buscarClientePorRg(new ClienteRequestDTO(null, "123456789", null, null, null));

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void buscarClientePorRg_ClienteNaoEncontrado() {
        when(jdbcTemplateClienteDaoImpl.buscarClientePorRg("123456789")).thenReturn(null);

        ResponseEntity<?> resposta = clienteService.buscarClientePorRg(new ClienteRequestDTO(null, "123456789", null, null, null));

        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
        assertEquals(ClienteEnums.CLIENTE_NAO_ENCONTRADO.getMessage(), ((RespostaPadrao) resposta.getBody()).getMessage());
    }

    @Test
    void deletarClientePorRg_Sucesso() {
        Cliente cliente = Cliente.builder().rg("123456789").build();

        when(jdbcTemplateClienteDaoImpl.buscarClientePorRg("123456789")).thenReturn(cliente);
        doReturn(1).when(jdbcTemplateClienteDaoImpl).excluirClientePorRg("123456789");
        String mensagem = clienteService.deletarClientePorRg("123456789");

        assertEquals(ClienteEnums.CLIENTE_EXCLUIDO.getMessage(), mensagem);
    }

    @Test
    void deletarClientesPorDataRegistro_Sucesso() {
        Cliente cliente = Cliente.builder().dataRegistro("01/01/2020").build();

        when(jdbcTemplateClienteDaoImpl.buscarClientesPorDataRegistro("01/01/2020")).thenReturn(Collections.singletonList(cliente));
        doReturn(1).when(jdbcTemplateClienteDaoImpl).excluirClientesPorDataRegistro("01/01/2020");


        String mensagem = clienteService.deletarClientesPorDataRegistro("01/01/2020");

        assertEquals(ClienteEnums.EXCLUIR_TODOS.getMessage(), mensagem);
    }
}
