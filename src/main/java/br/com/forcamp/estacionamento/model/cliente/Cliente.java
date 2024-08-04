package br.com.forcamp.estacionamento.model.cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo para um Cliente.
 * Esta classe representa um cliente, com campos para nome, RG, telefone, placa do veículo e data de registro.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Cliente {

        private String nome, rg, telefone, placaVeiculo;
        private String dataRegistro;
}
