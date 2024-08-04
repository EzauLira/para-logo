package br.com.forcamp.estacionamento.estacionamento;

import br.com.forcamp.estacionamento.controller.EstacionamentoController;
import br.com.forcamp.estacionamento.dtos.EstacionamentoRequestDTO;
import br.com.forcamp.estacionamento.exception.cliente.DataException;
import br.com.forcamp.estacionamento.exception.cliente.PlacaException;
import br.com.forcamp.estacionamento.exception.estacionamento.VagaEmUsoException;
import br.com.forcamp.estacionamento.exception.estacionamento.VagaException;
import br.com.forcamp.estacionamento.exception.estacionamento.VagaInvalidaException;
import br.com.forcamp.estacionamento.model.RespostaPadrao;
import br.com.forcamp.estacionamento.usecase.EstacionamentoService;
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

class EstacionamentoControllerTest {

    @Mock
    private EstacionamentoService estacionamentoService;

    @InjectMocks
    private EstacionamentoController estacionamentoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrarEntrada_Sucesso() throws PlacaException, DataException, VagaInvalidaException, VagaException {
        EstacionamentoRequestDTO request = new EstacionamentoRequestDTO();
        request.setPlaca("ABC1234");
        RespostaPadrao respostaEsperada = new RespostaPadrao("Entrada registrada com sucesso");

        when(estacionamentoService.registrarEntrada(any(EstacionamentoRequestDTO.class))).thenReturn(new ResponseEntity<>(respostaEsperada, HttpStatus.OK));

        ResponseEntity<RespostaPadrao> resposta = estacionamentoController.registrarEntrada(request);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(respostaEsperada.getMessage(), resposta.getBody().getMessage());
    }

    @Test
    void registrarSaida_Sucesso() throws PlacaException, DataException, VagaEmUsoException {
        EstacionamentoRequestDTO request = new EstacionamentoRequestDTO();
        request.setPlaca("ABC1234");
        RespostaPadrao respostaEsperada = new RespostaPadrao("Saída registrada com sucesso");

        when(estacionamentoService.registrarSaida(any(EstacionamentoRequestDTO.class))).thenReturn(new ResponseEntity<>(respostaEsperada, HttpStatus.OK));

        ResponseEntity<RespostaPadrao> resposta = estacionamentoController.registrarSaida(request);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(respostaEsperada.getMessage(), resposta.getBody().getMessage());
    }

    @Test
    void buscarVeiculoPorPlaca_Sucesso() {
        EstacionamentoRequestDTO request = new EstacionamentoRequestDTO();
        request.setPlaca("ABC1234");

        when(estacionamentoService.bucarCarroPorPlaca(any(EstacionamentoRequestDTO.class))).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity<?> resposta = estacionamentoController.buscarVeiculoPorPlaca(request);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void listarTodosRegistros_Sucesso() {
        when(estacionamentoService.listarTodosRegistros()).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity<?> resposta = estacionamentoController.listarTodosRegistros();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void listarVeiculosAtivos_Sucesso() {
        when(estacionamentoService.listarCarrosAtivos()).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity<?> resposta = estacionamentoController.listarVeiculosAtivos();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void listarVeiculosInativos_Sucesso() {
        when(estacionamentoService.listarCarrosQueSairam()).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity<?> resposta = estacionamentoController.listarVeiculosInativos();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void excluirRegistrosPorData_Sucesso() throws DataException {
        EstacionamentoRequestDTO request = new EstacionamentoRequestDTO();
        request.setDataRegistro("01/01/2020");
        RespostaPadrao respostaEsperada = new RespostaPadrao("Registros excluídos com sucesso");

        when(estacionamentoService.excluirRegistrosPorData(any(EstacionamentoRequestDTO.class))).thenReturn(new ResponseEntity<>(respostaEsperada, HttpStatus.OK));

        ResponseEntity<RespostaPadrao> resposta = estacionamentoController.excluirRegistrosPorData(request);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(respostaEsperada.getMessage(), resposta.getBody().getMessage());
    }

    @Test
    void calcularCusto_Sucesso() {
        EstacionamentoRequestDTO request = new EstacionamentoRequestDTO();
        request.setPlaca("ABC1234");
        RespostaPadrao respostaEsperada = new RespostaPadrao("Custo calculado com sucesso");

        when(estacionamentoService.calcularCusto("ABC1234")).thenReturn(new ResponseEntity<>(respostaEsperada, HttpStatus.OK));

        ResponseEntity<RespostaPadrao> resposta = estacionamentoController.calcularCusto(request);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(respostaEsperada.getMessage(), resposta.getBody().getMessage());
    }
}
