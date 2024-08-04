package br.com.forcamp.estacionamento.utils.Estacionamento;

/**
 * Classe de utilitários para horas.
 * Esta classe contém métodos para validar uma hora.
 */
public class HoraUtils {

    /**
     * Valida uma hora.
     *
     * @param hora A hora a ser validada.
     * @return A hora validada.
     */
    public static String validarHora(String hora) {
        if (hora.contains("-")) {
            return hora.substring(0, hora.lastIndexOf("-"));
        } else {
            return hora;
        }
    }
}
