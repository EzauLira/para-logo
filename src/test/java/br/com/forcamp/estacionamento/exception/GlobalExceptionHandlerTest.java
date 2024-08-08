package br.com.forcamp.estacionamento.exception;

import br.com.forcamp.estacionamento.enums.ExceptionEnums;
import br.com.forcamp.estacionamento.exception.cliente.DataException;
import br.com.forcamp.estacionamento.exception.cliente.PlacaException;
import br.com.forcamp.estacionamento.exception.estacionamento.VagaEmUsoException;
import br.com.forcamp.estacionamento.model.ErroResposta;
import br.com.forcamp.estacionamento.model.RespostaPadrao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleNegocioException() {
        NegocioException exception = new NegocioException("Teste");
        ResponseEntity<ErroResposta> resposta = handler.handleNegocioException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertEquals("Teste", resposta.getBody().getMensagem());
    }

    @Test
    void testHandleTecnicoException() {
        TecnicoException exception = new TecnicoException("Teste");
        ResponseEntity<Object> resposta = handler.handleTecnicoException(exception);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, resposta.getStatusCode());
        assertEquals("Teste", resposta.getBody());
    }

    @Test
    void testHandleHttpMessageNotReadableException() {
        HttpMessageNotReadableException exception = new HttpMessageNotReadableException("Teste");
        WebRequest request = mock(WebRequest.class);
        when(request.toString()).thenReturn("Teste");
        ResponseEntity<RespostaPadrao> resposta = handler.handleHttpMessageNotReadableException(exception, request);
        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertEquals("Corpo da requisição obrigatório está ausente ou inválido", resposta.getBody().getMessage());
    }

    @Test
    void testHandlePlacaException() {
        PlacaException exception = new PlacaException("Teste");
        ResponseEntity<ErroResposta> resposta = handler.handlePlacaException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertEquals("Teste", resposta.getBody().getMensagem());
    }

    @Test
    void testHandleDataException() {
        DataException exception = new DataException();
        ResponseEntity<ErroResposta> resposta = handler.handleDataException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertEquals(ExceptionEnums.DATA_EXCEPTION.getMessage(), resposta.getBody().getMensagem());
    }

    @Test
    void testHandleVagaEmUsoException() {
        VagaEmUsoException exception = new VagaEmUsoException("Teste");
        ResponseEntity<ErroResposta> resposta = handler.handleVagaEmUsoException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertEquals("Teste", resposta.getBody().getMensagem());
    }
}
