package br.com.forcamp.estacionamento.exception;

import br.com.forcamp.estacionamento.exception.cliente.DataException;
import br.com.forcamp.estacionamento.exception.cliente.PlacaException;
import br.com.forcamp.estacionamento.exception.estacionamento.VagaEmUsoException;
import br.com.forcamp.estacionamento.model.ErroResposta;
import br.com.forcamp.estacionamento.model.RespostaPadrao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

/**
 * Classe de manipulação global de exceções.
 * Esta classe é responsável por lidar com exceções lançadas em todo o aplicativo.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Manipula exceções de negócio.
     *
     * @ExceptionHandler Esta anotação é usada para indicar que este método deve ser usado para manipular a exceção especificada (neste caso, NegocioException).
     * @ResponseStatus Esta anotação é usada para definir o status HTTP que deve ser retornado quando esta exceção é lançada (neste caso, BAD_REQUEST).
     *
     * @param e A exceção de negócio lançada.
     * @return Uma resposta de erro contendo a mensagem da exceção.
     */
    @ExceptionHandler(NegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErroResposta> handleNegocioException(NegocioException e) {
        var erroResposta = ErroResposta.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .mensagem(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(erroResposta.build());
    }

    /**
     * Manipula exceções técnicas.
     *
     * @ExceptionHandler Esta anotação é usada para indicar que este método deve ser usado para manipular a exceção especificada (neste caso, TecnicoException).
     *
     * @param e A exceção técnica lançada.
     * @return Uma resposta de erro contendo a mensagem da exceção.
     */
    @ExceptionHandler(TecnicoException.class)
    public ResponseEntity<Object> handleTecnicoException(TecnicoException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Manipula exceções de mensagens HTTP não legíveis.
     *
     * @ExceptionHandler Esta anotação é usada para indicar que este método deve ser usado para manipular a exceção especificada (neste caso, HttpMessageNotReadableException).
     *
     * @param ex A exceção de mensagem HTTP não legível lançada.
     * @param request A solicitação web que causou a exceção.
     * @return Uma resposta de erro contendo detalhes sobre a exceção.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RespostaPadrao> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        RespostaPadrao errorDetails = new RespostaPadrao(HttpStatus.BAD_REQUEST, "Corpo da requisição obrigatório está ausente ou inválido", request.toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Manipula exceções de placa.
     *
     *  @ExceptionHandler Esta anotação é usada para indicar que este método deve ser usado para manipular a exceção especificada (neste caso, PlacaException).
     *  @ResponseStatus Esta anotação é usada para definir o status HTTP que deve ser retornado quando esta exceção é lançada (neste caso, BAD_REQUEST).
     *
     * @param e A exceção de placa lançada.
     * @return Uma resposta de erro contendo a mensagem da exceção.
     */
    @ExceptionHandler(PlacaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErroResposta> handlePlacaException(PlacaException e) {
        var erroResposta = ErroResposta.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .mensagem(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(erroResposta.build());
    }

    /**
     * Manipula exceções de data.
     *
     * @ExceptionHandler Esta anotação é usada para indicar que este método deve ser usado para manipular a exceção especificada (neste caso, DataException).
     * @ResponseStatus Esta anotação é usada para definir o status HTTP que deve ser retornado quando esta exceção é lançada (neste caso, BAD_REQUEST).
     *
     * @param e A exceção de data lançada.
     * @return Uma resposta de erro contendo a mensagem da exceção.
     */
    @ExceptionHandler(DataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErroResposta> handleDataException(DataException e) {
        var erroResposta = ErroResposta.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .mensagem(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(erroResposta.build());
    }

    /**
     * Manipula exceções de vaga em uso.
     *
     * @ExceptionHandler Esta anotação é usada para indicar que este método deve ser usado para manipular a exceção especificada (neste caso, VagaEmUsoException).
     * @ResponseStatus Esta anotação é usada para definir o status HTTP que deve ser retornado quando esta exceção é lançada (neste caso, BAD_REQUEST).
     *
     * @param e A exceção de vaga em uso lançada.
     * @return Uma resposta de erro contendo a mensagem da exceção.
     */
    @ExceptionHandler(VagaEmUsoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErroResposta> handleVagaEmUsoException(VagaEmUsoException e) {
        var erroResposta = ErroResposta.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .mensagem(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(erroResposta.build());
    }
}
