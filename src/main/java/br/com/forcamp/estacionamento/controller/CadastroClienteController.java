package br.com.forcamp.estacionamento.controller;

import br.com.forcamp.estacionamento.dtos.ClienteRequestDTO;
import br.com.forcamp.estacionamento.exception.cliente.*;
import br.com.forcamp.estacionamento.model.RespostaPadrao;
import br.com.forcamp.estacionamento.usecase.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/clientes")
public class CadastroClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Cadastra um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<RespostaPadrao> cadastrarCliente(
            @Parameter(description = "Dados do cliente a ser cadastrado")
            @Valid @RequestBody ClienteRequestDTO request
    ) throws NomeException, RgException, TelefoneException, PlacaException, DataException {
        long startTime = System.currentTimeMillis();
        ResponseEntity<RespostaPadrao> resposta = clienteService.salvarDadosCadastrais(request);
        logElapsedTime("cadastrarCliente", startTime, request.getRg());
        return resposta;
    }

    @Operation(summary = "Obtém todos os clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes obtida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum cliente encontrado")
    })
    @GetMapping("/todos")
    public ResponseEntity<?> obterTodosClientes() {
        long startTime = System.currentTimeMillis();
        ResponseEntity<?> resposta = clienteService.buscarTodosClientes();
        logElapsedTime("obterTodosClientes", startTime, null);
        return resposta;
    }

    @Operation(summary = "Busca um cliente específico por RG")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @PostMapping("/buscar")
    public ResponseEntity<?> buscarClientePorRg(
            @Parameter(description = "RG do cliente a ser buscado")
            @Valid @RequestBody ClienteRequestDTO request
    ) {
        long startTime = System.currentTimeMillis();
        ResponseEntity<?> resposta = clienteService.buscarClientePorRg(request);
        logElapsedTime("buscarClientePorRg", startTime, request.getRg());
        return resposta;
    }

    @Operation(summary = "Deleta um cliente específico por RG")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @DeleteMapping("/deletar")
    public ResponseEntity<RespostaPadrao> deletarClientePorRg(
            @Parameter(description = "RG do cliente a ser deletado")
            @Valid @RequestBody ClienteRequestDTO request
    ) {
        long startTime = System.currentTimeMillis();
        String message = clienteService.deletarClientePorRg(request.getRg());
        logElapsedTime("deletarClientePorRg", startTime, request.getRg());
        return ResponseEntity.ok(RespostaPadrao.builder().message(message).build());
    }

    @Operation(summary = "Deleta vários clientes por data de registro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes deletados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Clientes não encontrados")
    })
    @DeleteMapping("/deletar-por-data")
    public ResponseEntity<RespostaPadrao> deletarClientesPorDataRegistro(
            @Parameter(description = "Data de registro dos clientes a serem deletados")
            @Valid @RequestBody ClienteRequestDTO request
    ) {
        long startTime = System.currentTimeMillis();
        String message = clienteService.deletarClientesPorDataRegistro(request.getDataRegistro());
        logElapsedTime("deletarClientesPorDataRegistro", startTime, null);
        return ResponseEntity.ok(RespostaPadrao.builder().message(message).build());
    }

    private void logElapsedTime(String methodName, long startTime, String rg) {
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        if (rg != null) {
            log.info("Método: {}, RG: {}, Tempo decorrido: {} ms", methodName, rg, elapsedTime);
        } else {
            log.info("Método: {}, Tempo decorrido: {} ms", methodName, elapsedTime);
        }
    }
}