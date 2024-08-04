package br.com.forcamp.estacionamento.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * DTO para solicitações de clientes.
 * Esta classe é usada para transferir dados sobre um cliente entre processos ou componentes do sistema.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ClienteRequestDTO
{
    private String nome, rg, telefone, placaVeiculo;
    private String dataRegistro;
}