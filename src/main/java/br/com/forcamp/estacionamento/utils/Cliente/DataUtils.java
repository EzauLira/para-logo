package br.com.forcamp.estacionamento.utils.Cliente;

import br.com.forcamp.estacionamento.exception.cliente.DataException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe de utilitários para datas.
 * Esta classe contém métodos para converter uma data de um formato para outro.
 */
public class DataUtils {

    /**
     * Converte uma data do formato "dd/MM/yyyy" para o formato "yyyy-MM-dd".
     *
     * @param data A data a ser convertida.
     * @return A data convertida.
     * @throws DataException Se ocorrer um erro ao converter a data.
     */
    public static String converterData(String data) throws DataException {
        SimpleDateFormat formatEntrada = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatSaida = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate;
        try {
            parsedDate = formatEntrada.parse(data);
        } catch (ParseException e) {
            throw new DataException();
        }
        return formatSaida.format(parsedDate);
    }

}
