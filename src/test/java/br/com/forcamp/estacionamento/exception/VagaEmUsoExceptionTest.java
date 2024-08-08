package br.com.forcamp.estacionamento.exception;

import br.com.forcamp.estacionamento.exception.estacionamento.VagaEmUsoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VagaEmUsoExceptionTest {

    @Test
    void testVagaEmUsoException() {
        VagaEmUsoException exception = new VagaEmUsoException("Teste");
        assertEquals("Teste", exception.getMessage());
    }
}
