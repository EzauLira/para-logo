package br.com.forcamp.estacionamento.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo para uma resposta de erro.
 * Esta classe é usada para encapsular informações sobre um erro, incluindo o status e a mensagem do erro.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ErroResposta {

    private int status;
    private String mensagem;
}
