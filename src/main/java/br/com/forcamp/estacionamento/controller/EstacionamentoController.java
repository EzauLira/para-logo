package br.com.forcamp.estacionamento.controller;

import br.com.forcamp.estacionamento.dtos.EstacionamentoRequestDTO;
import br.com.forcamp.estacionamento.exception.cliente.DataException;
import br.com.forcamp.estacionamento.exception.cliente.PlacaException;
import br.com.forcamp.estacionamento.exception.estacionamento.VagaEmUsoException;
import br.com.forcamp.estacionamento.exception.estacionamento.VagaException;
import br.com.forcamp.estacionamento.exception.estacionamento.VagaInvalidaException;
import br.com.forcamp.estacionamento.model.RespostaPadrao;
import br.com.forcamp.estacionamento.usecase.EstacionamentoService;
import br.com.forcamp.estacionamento.dao.impl.JdbcTemplateEstacionamentoDaoImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/v1/estacionamento")
public class EstacionamentoController {

    @Autowired
    private EstacionamentoService estacionamentoService;

    @Autowired
    private JdbcTemplateEstacionamentoDaoImpl jdbcTemplateEstacionamentoDaoImpl;

    @Operation(summary = "Registra a entrada de um veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrada registrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/entrada")
    public ResponseEntity<RespostaPadrao> registrarEntrada(
            @Parameter(description = "Dados da requisição de entrada do veículo")
            @RequestBody EstacionamentoRequestDTO request
    ) throws PlacaException, DataException, VagaInvalidaException, VagaException {
        long startTime = System.currentTimeMillis();
        ResponseEntity<RespostaPadrao> resposta = estacionamentoService.registrarEntrada(request);
        logElapsedTime("registrarEntrada", startTime, request.getPlaca());
        return resposta;
    }

    @Operation(summary = "Registra a saída de um veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saída registrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/saida")
    public ResponseEntity<RespostaPadrao> registrarSaida(
            @Parameter(description = "Dados da requisição de saída do veículo")
            @RequestBody EstacionamentoRequestDTO request
    ) throws PlacaException, DataException, VagaEmUsoException {
        long startTime = System.currentTimeMillis();
        ResponseEntity<RespostaPadrao> resposta = estacionamentoService.registrarSaida(request);
        logElapsedTime("registrarSaida", startTime, request.getPlaca());
        return resposta;
    }

    @Operation(summary = "Obtém o número de vagas disponíveis")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Número de vagas disponíveis obtido com sucesso")
    })
    @GetMapping("/vagas-disponiveis")
    public ResponseEntity<?> obterVagasDisponiveis() {
        int vagasDisponiveis = jdbcTemplateEstacionamentoDaoImpl.getVagasDisponiveis();
        return new ResponseEntity<>(Collections.singletonMap("vagasDisponiveis", vagasDisponiveis), HttpStatus.OK);
    }

    @Operation(summary = "Busca um veículo específico por placa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    @PostMapping("/buscar")
    public ResponseEntity<?> buscarVeiculoPorPlaca(
            @Parameter(description = "Dados da requisição contendo a placa do veículo")
            @RequestBody EstacionamentoRequestDTO request
    ) {
        long startTime = System.currentTimeMillis();
        ResponseEntity<?> resposta = estacionamentoService.bucarCarroPorPlaca(request);
        logElapsedTime("buscarVeiculoPorPlaca", startTime, request.getPlaca());
        return resposta;
    }

    @Operation(summary = "Lista todos os registros de veículos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de registros obtida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum registro encontrado")
    })
    @GetMapping("/todos-registros")
    public ResponseEntity<?> listarTodosRegistros() {
        long startTime = System.currentTimeMillis();
        ResponseEntity<?> resposta = estacionamentoService.listarTodosRegistros();
        logElapsedTime("listarTodosRegistros", startTime, null);
        return resposta;
    }

    @Operation(summary = "Lista todos os veículos ativos no estacionamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de veículos ativos obtida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum veículo ativo encontrado")
    })
    @GetMapping("/veiculos-ativos")
    public ResponseEntity<?> listarVeiculosAtivos() {
        return estacionamentoService.listarCarrosAtivos();
    }

    @Operation(summary = "Lista todos os veículos que já saíram do estacionamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de veículos que saíram obtida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum veículo encontrado")
    })
    @GetMapping("/veiculos-inativos")
    public ResponseEntity<?> listarVeiculosInativos() {
        return estacionamentoService.listarCarrosQueSairam();
    }

    @Operation(summary = "Exclui registros de veículos por data de entrada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registros excluídos com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum registro encontrado para a data fornecida")
    })
    @DeleteMapping("/deletar-por-data")
    public ResponseEntity<RespostaPadrao> excluirRegistrosPorData(
            @Parameter(description = "Data de entrada dos registros a serem excluídos")
            @RequestBody EstacionamentoRequestDTO request
    ) throws DataException {
        return estacionamentoService.excluirRegistrosPorData(request);
    }

    @Operation(summary = "Calcula o custo de permanência de um veículo no estacionamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Custo calculado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    @PostMapping("/calcular-custo")
    public ResponseEntity<RespostaPadrao> calcularCusto(
            @Parameter(description = "Placa do veículo para cálculo do custo")
            @RequestBody EstacionamentoRequestDTO request
    ) {
        return estacionamentoService.calcularCusto(request.getPlaca());
    }

    private void logElapsedTime(String methodName, long startTime, String placa) {
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        if (placa != null) {
            log.info("Método: {}, Placa: {}, Tempo decorrido: {} ms", methodName, placa, elapsedTime);
        } else {
            log.info("Método: {}, Tempo decorrido: {} ms", methodName, elapsedTime);
        }
    }
}