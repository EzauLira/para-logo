package br.com.forcamp.estacionamento.model.estacionamento;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo para um Estacionamento.
 * Esta classe representa um estacionamento, com campos para placa, data e hora de entrada, e data e hora de saída.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Estacionamento {

    private String placa, dataEntrada, horaEntrada, dataSaida, horaSaida;
}