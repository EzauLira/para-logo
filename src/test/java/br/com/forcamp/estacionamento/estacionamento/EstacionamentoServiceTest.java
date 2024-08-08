package br.com.forcamp.estacionamento.estacionamento;

import br.com.forcamp.estacionamento.dao.impl.ArmazenamentoJsonDaoImpl;
import br.com.forcamp.estacionamento.dtos.EstacionamentoRequestDTO;
import br.com.forcamp.estacionamento.exception.cliente.DataException;
import br.com.forcamp.estacionamento.exception.cliente.PlacaException;
import br.com.forcamp.estacionamento.exception.estacionamento.VagaEmUsoException;
import br.com.forcamp.estacionamento.exception.estacionamento.VagaException;
import br.com.forcamp.estacionamento.exception.estacionamento.VagaInvalidaException;
import br.com.forcamp.estacionamento.model.RespostaPadrao;
import br.com.forcamp.estacionamento.model.estacionamento.Estacionamento;
import br.com.forcamp.estacionamento.dao.impl.JdbcTemplateEstacionamentoDaoImpl;
import br.com.forcamp.estacionamento.usecase.EstacionamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EstacionamentoServiceTest {

    @Mock
    private JdbcTemplateEstacionamentoDaoImpl jdbcTemplateEstacionamentoDaoImpl;

    @Mock
    private ArmazenamentoJsonDaoImpl armazenamentoJsonDaoImpl;

    @InjectMocks
    private EstacionamentoService estacionamentoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrarEntrada_Sucesso() throws PlacaException, DataException, VagaInvalidaException, VagaException {
        EstacionamentoRequestDTO request = new EstacionamentoRequestDTO();
        request.setPlaca("ABC1234");
        request.setDataEntrada("01/01/2020");
        request.setHoraEntrada("12:00");
        RespostaPadrao respostaEsperada = new RespostaPadrao("Veículo de placa: ABC1234 entrou no estacionamento! Vagas disponíveis: 1");

        when(jdbcTemplateEstacionamentoDaoImpl.getVagasDisponiveis()).thenReturn(1);
        doNothing().when(jdbcTemplateEstacionamentoDaoImpl).registrarEntrada(any(Estacionamento.class));

        ResponseEntity<RespostaPadrao> resposta = estacionamentoService.registrarEntrada(request);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(respostaEsperada.getMessage(), resposta.getBody().getMessage());
    }

    @Test
    void registrarSaida_Sucesso() throws PlacaException, DataException, VagaEmUsoException {
        EstacionamentoRequestDTO request = new EstacionamentoRequestDTO();
        request.setPlaca("ABC1234");
        request.setDataSaida("01/01/2020");
        request.setHoraSaida("18:00");
        RespostaPadrao respostaEsperada = new RespostaPadrao("Veículo de placa: ABC1234 saiu do estacionamento! Vagas disponíveis: 1");

        Estacionamento estacionamentoEntrada = Estacionamento.builder()
                .placa("ABC1234")
                .dataEntrada("01/01/2020")
                .horaEntrada("12:00")
                .build();

        when(jdbcTemplateEstacionamentoDaoImpl.bucarCarroPorPlaca("ABC1234")).thenReturn(estacionamentoEntrada);
        doNothing().when(jdbcTemplateEstacionamentoDaoImpl).registrarSaida(any(Estacionamento.class));
        when(jdbcTemplateEstacionamentoDaoImpl.getVagasDisponiveis()).thenReturn(1);

        ResponseEntity<RespostaPadrao> resposta = estacionamentoService.registrarSaida(request);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(respostaEsperada.getMessage(), resposta.getBody().getMessage());
    }

    @Test
    void buscarVeiculoPorPlaca_Sucesso() {
        EstacionamentoRequestDTO request = new EstacionamentoRequestDTO();
        request.setPlaca("ABC1234");

        when(jdbcTemplateEstacionamentoDaoImpl.bucarCarroPorPlaca("ABC1234")).thenReturn(new Estacionamento());

        ResponseEntity<?> resposta = estacionamentoService.bucarCarroPorPlaca(request);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void listarTodosRegistros_Sucesso() {
        when(jdbcTemplateEstacionamentoDaoImpl.findAll()).thenReturn(Collections.singletonList(new Estacionamento()));

        ResponseEntity<?> resposta = estacionamentoService.listarTodosRegistros();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void listarVeiculosAtivos_Sucesso() {
        when(jdbcTemplateEstacionamentoDaoImpl.listarCarrosAtivos()).thenReturn(Collections.singletonList(new Estacionamento()));

        ResponseEntity<?> resposta = estacionamentoService.listarCarrosAtivos();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void listarVeiculosInativos_Sucesso() {
        when(jdbcTemplateEstacionamentoDaoImpl.listarCarrosQueSairam()).thenReturn(Collections.singletonList(new Estacionamento()));

        ResponseEntity<?> resposta = estacionamentoService.listarCarrosQueSairam();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void excluirRegistrosPorData_Sucesso() throws DataException {
        EstacionamentoRequestDTO request = new EstacionamentoRequestDTO();
        request.setDataEntrada("01/01/2020");
        RespostaPadrao respostaEsperada = new RespostaPadrao("Registros excluídos com sucesso");

        when(jdbcTemplateEstacionamentoDaoImpl.excluirRegistrosPorData("01/01/2020")).thenReturn(1);
        doNothing().when(jdbcTemplateEstacionamentoDaoImpl).registrarEntrada(any(Estacionamento.class));

        ResponseEntity<RespostaPadrao> resposta = estacionamentoService.excluirRegistrosPorData(request);
    }

    @Test
    void calcularCusto_Sucesso() {
        String placa = "ABC1234";
        Double custoEsperado = 10.0;
        RespostaPadrao respostaEsperada = new RespostaPadrao("Custo calculado com sucesso");

        when(jdbcTemplateEstacionamentoDaoImpl.calcularCusto(placa)).thenReturn(custoEsperado);  // Garanta que isso retorne um valor válido

        ResponseEntity<RespostaPadrao> resposta = estacionamentoService.calcularCusto(placa);

    }

}
