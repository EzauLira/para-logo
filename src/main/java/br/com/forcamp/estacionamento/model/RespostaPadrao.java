package br.com.forcamp.estacionamento.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Modelo para uma resposta padrão.
 * Esta classe é usada para encapsular informações sobre uma resposta padrão, incluindo o status, a mensagem e os detalhes da resposta.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RespostaPadrao {

    private String message;

    public  RespostaPadrao(HttpStatus httpStatus, String message, String string) {
        this.message = message;
    }
}
