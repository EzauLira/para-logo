package br.com.forcamp.estacionamento.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para solicitações de estacionamento.
 * Esta classe é usada para transferir dados sobre um estacionamento entre processos ou componentes do sistema.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstacionamentoRequestDTO {

    private String placa, dataEntrada, horaEntrada, dataSaida, horaSaida;

    public void setDataRegistro(String s) {
    }
}