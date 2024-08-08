package br.com.forcamp.estacionamento.cliente;

import br.com.forcamp.estacionamento.controller.CadastroClienteController;
import br.com.forcamp.estacionamento.dtos.ClienteRequestDTO;
import br.com.forcamp.estacionamento.exception.cliente.*;
import br.com.forcamp.estacionamento.model.RespostaPadrao;
import br.com.forcamp.estacionamento.usecase.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CadastroClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private CadastroClienteController cadastroClienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrarCliente() throws Exception {
        ClienteRequestDTO request = new ClienteRequestDTO();
        request.setRg("12345678");
        RespostaPadrao respostaEsperada = new RespostaPadrao("Cliente cadastrado com sucesso");
        when(clienteService.salvarDadosCadastrais(request)).thenReturn(ResponseEntity.ok(respostaEsperada));
        ResponseEntity<RespostaPadrao> resposta = cadastroClienteController.cadastrarCliente(request);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(respostaEsperada, resposta.getBody());
    }


    @Test
    void cadastrarCliente_Sucesso() throws NomeException, RgException, TelefoneException, PlacaException, DataException {
        ClienteRequestDTO request = new ClienteRequestDTO("Nome Teste", "123456789", "99999999999", "ABC1234", "01/01/2020");
        RespostaPadrao respostaEsperada = new RespostaPadrao("Cliente cadastrado com sucesso!");

        when(clienteService.salvarDadosCadastrais(any(ClienteRequestDTO.class))).thenReturn(new ResponseEntity<>(respostaEsperada, HttpStatus.OK));

        ResponseEntity<?> resposta = cadastroClienteController.cadastrarCliente(request);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(respostaEsperada.getMessage(), ((RespostaPadrao) resposta.getBody()).getMessage());
    }

    @Test
    void cadastrarCliente_DadosInvalidos() throws NomeException, RgException, TelefoneException, PlacaException, DataException {
        ClienteRequestDTO request = new ClienteRequestDTO("Nadia", "12.345.678-9", "(11)958415900", "ABC1234", "01/01/2020");

        when(clienteService.salvarDadosCadastrais(any(ClienteRequestDTO.class))).thenThrow(new NomeException("Nome inválido. Este campo deve conter apenas letras, incluindo acentos, e ter entre 2 e 100 caracteres."));

        try {
            cadastroClienteController.cadastrarCliente(request);
        } catch (NomeException e) {
            assertEquals("Nome inválido. Este campo deve conter apenas letras, incluindo acentos, e ter entre 2 e 100 caracteres.", e.getMessage());
        }
    }


    @Test
    void obterTodosClientes_Sucesso() {
        when(clienteService.buscarTodosClientes()).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity<?> resposta = cadastroClienteController.obterTodosClientes();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void buscarClientePorRg_Sucesso() {
        ClienteRequestDTO request = new ClienteRequestDTO();
        request.setRg("123456789");

        when(clienteService.buscarClientePorRg(any(ClienteRequestDTO.class))).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity<?> resposta = cadastroClienteController.buscarClientePorRg(request);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void deletarClientePorRg_Sucesso() {
        ClienteRequestDTO request = new ClienteRequestDTO();
        request.setRg("123456789");

        when(clienteService.deletarClientePorRg("123456789")).thenReturn("Cliente deletado com sucesso!");

        ResponseEntity<RespostaPadrao> resposta = cadastroClienteController.deletarClientePorRg(request);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals("Cliente deletado com sucesso!", resposta.getBody().getMessage());
    }

    @Test
    void deletarClientesPorDataRegistro_Sucesso() {
        ClienteRequestDTO request = new ClienteRequestDTO();
        request.setDataRegistro("01/01/2020");

        when(clienteService.deletarClientesPorDataRegistro("01/01/2020")).thenReturn("Clientes deletados com sucesso!");

        ResponseEntity<RespostaPadrao> resposta = cadastroClienteController.deletarClientesPorDataRegistro(request);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals("Clientes deletados com sucesso!", resposta.getBody().getMessage());
    }
}
