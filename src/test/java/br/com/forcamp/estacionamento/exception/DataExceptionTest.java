package br.com.forcamp.estacionamento.exception;

import br.com.forcamp.estacionamento.enums.ExceptionEnums;
import br.com.forcamp.estacionamento.exception.cliente.DataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataExceptionTest {

    @Test
    void testDataException() {
        DataException exception = new DataException();
        assertEquals(ExceptionEnums.DATA_EXCEPTION.getMessage(), exception.getMessage());
    }
}
