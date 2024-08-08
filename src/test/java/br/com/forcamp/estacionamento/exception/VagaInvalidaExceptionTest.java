package br.com.forcamp.estacionamento.exception;

import br.com.forcamp.estacionamento.exception.estacionamento.VagaInvalidaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VagaInvalidaExceptionTest {

    @Test
    void testVagaInvalidaException() {
        VagaInvalidaException exception = new VagaInvalidaException("Teste");
        assertEquals("Teste", exception.getMessage());
    }
}
