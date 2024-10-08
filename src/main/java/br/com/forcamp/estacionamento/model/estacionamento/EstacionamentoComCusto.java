package br.com.forcamp.estacionamento.model.estacionamento;


import br.com.forcamp.estacionamento.utils.Estacionamento.CustoEstacionamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Modelo para um Estacionamento com Custo.
 * Esta classe estende a classe Estacionamento e adiciona um campo para o custo.
 * Ela também inclui um método para calcular o custo com base na duração da estadia.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstacionamentoComCusto extends Estacionamento {

    private double custo;

    /**
     * Construtor para a classe EstacionamentoComCusto.
     * Este construtor aceita um objeto Estacionamento como parâmetro e inicializa um novo objeto EstacionamentoComCusto com os mesmos valores de placa, data e hora de entrada e saída.
     * Ele também calcula o custo da estadia usando o método calcularCusto().
     *
     * @param estacionamento O objeto Estacionamento a ser usado para inicializar o novo objeto EstacionamentoComCusto.
     */
    public EstacionamentoComCusto(Estacionamento estacionamento) {
        super(estacionamento.getPlaca(), estacionamento.getDataEntrada(), estacionamento.getHoraEntrada(), estacionamento.getDataSaida(), estacionamento.getHoraSaida());
        this.custo = calcularCusto();
    }

    /**
     * Calcula o custo com base na duração da estadia.
     *
     * @return o custo calculado.
     */
    public double calcularCusto() {

        if (this.getDataSaida() == null || this.getHoraSaida() == null) {
            return 0.0;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime dataHoraEntrada = LocalDateTime.parse(this.getDataEntrada() + " " + this.getHoraEntrada(), formatter);
        LocalDateTime dataHoraSaida;

        if (this.getDataSaida() == null || this.getHoraSaida() == null) {
            dataHoraSaida = LocalDateTime.now();
        } else {
            dataHoraSaida = LocalDateTime.parse(this.getDataSaida() + " " + this.getHoraSaida(), formatter);
        }

        Duration duracao = Duration.between(dataHoraEntrada, dataHoraSaida);

        double horasEstacionado = duracao.toMinutes() / 60.0;
        double diasEstacionado = duracao.toDays();

        if (diasEstacionado >= 1) {
            custo = diasEstacionado * CustoEstacionamento.CUSTO_DIA;
        } else if (horasEstacionado <= 1) {
            custo = CustoEstacionamento.CUSTO_HORA_1;
        } else if (horasEstacionado <= 3) {
            custo = CustoEstacionamento.CUSTO_HORA_3;
        } else if (horasEstacionado <= 4) {
            custo = CustoEstacionamento.CUSTO_HORA_4;
        } else if (horasEstacionado <= 5) {
            custo = CustoEstacionamento.CUSTO_HORA_5;
        } else {
            custo = CustoEstacionamento.CUSTO_HORA_RESTANTE;
        }
        return custo;
    }
}
