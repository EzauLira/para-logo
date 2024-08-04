package br.com.forcamp.estacionamento.exception;

import br.com.forcamp.estacionamento.exception.estacionamento.VagaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VagaExceptionTest {

    @Test
    void testVagaException() {
        VagaException exception = new VagaException("Teste");
        assertEquals("Teste", exception.getMessage());
    }
}
