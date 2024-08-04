package br.com.forcamp.estacionamento.enums;

import lombok.Getter;

/**
 * Enumeração para mensagens de exceção.
 * Esta enumeração define várias mensagens que podem ser usadas ao lidar com exceções.
 */
@Getter
public enum ExceptionEnums {

    DATA_EXCEPTION("Data inválida. Este campo deve conter uma no formato: dd/mm/yyyy."),
    NOME_EXCEPTION("Nome inválido. Este campo deve conter apenas letras, incluindo acentos, e ter entre 2 e 100 caracteres."),
    RG_EXCEPTION("O número do RG deve ser do formato: 123456789 ou 12.123.456-7 (9 dígitos)."),
    TELEFONE_EXCEPTION("O número de telefone deve ser no formato: XX9XXXXYYYY ou (XX)9XXXX-XXXX (11 dígitos).");

    private final String message;

    ExceptionEnums(String message)
    {
        this.message = message;
    }

}
