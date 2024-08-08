package br.com.forcamp.estacionamento.usecase;

import br.com.forcamp.estacionamento.dao.impl.ArmazenamentoJsonDaoImpl;
import br.com.forcamp.estacionamento.dao.impl.JdbcTemplateEstacionamentoDaoImpl;
import br.com.forcamp.estacionamento.dtos.EstacionamentoRequestDTO;
import br.com.forcamp.estacionamento.enums.EstacionamentoEnums;
import br.com.forcamp.estacionamento.exception.cliente.DataException;
import br.com.forcamp.estacionamento.exception.cliente.PlacaException;
import br.com.forcamp.estacionamento.exception.estacionamento.VagaException;
import br.com.forcamp.estacionamento.exception.estacionamento.VagaInvalidaException;
import br.com.forcamp.estacionamento.model.RespostaPadrao;
import br.com.forcamp.estacionamento.model.estacionamento.Estacionamento;
import br.com.forcamp.estacionamento.model.estacionamento.EstacionamentoComCusto;
import br.com.forcamp.estacionamento.utils.Estacionamento.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstacionamentoService {

    // Declaração de dependências
    private static JdbcTemplateEstacionamentoDaoImpl jdbcTemplateEstacionamentoDaoImpl;
    @Autowired
    private final ArmazenamentoJsonDaoImpl armazenamentoJsonDaoImpl;

    /**
     * Construtor da classe EstacionamentoService.
     *
     * @param jdbcTemplateEstacionamentoDaoImpl Implementação do DAO para Estacionamento usando JdbcTemplate.
     * @param armazenamentoJsonDaoImpl Implementação do DAO para armazenamento em JSON.
     */
    @Autowired
    public EstacionamentoService(JdbcTemplateEstacionamentoDaoImpl jdbcTemplateEstacionamentoDaoImpl, ArmazenamentoJsonDaoImpl armazenamentoJsonDaoImpl) {
        EstacionamentoService.jdbcTemplateEstacionamentoDaoImpl = jdbcTemplateEstacionamentoDaoImpl;
        this.armazenamentoJsonDaoImpl = armazenamentoJsonDaoImpl;
    }

    /**
     * Registra a entrada de um veículo no estacionamento.
     *
     * @param request Dados da requisição de entrada do veículo.
     * @return Resposta padrão indicando sucesso ou falha.
     * @throws PlacaException se a placa for inválida.
     * @throws DataException se a data ou hora forem inválidas.
     * @throws VagaInvalidaException se a vaga estiver ocupada, ou inválida.
     * @throws VagaException complemento da VagaInvalidaException.
     */
    public ResponseEntity<RespostaPadrao> registrarEntrada(EstacionamentoRequestDTO request) throws PlacaException, DataException, VagaInvalidaException, VagaException {
        EntradaUtils.validarCarroJaRegistrado(request.getPlaca(), placa -> jdbcTemplateEstacionamentoDaoImpl.carroJaRegistrado(placa));
        VagasUtils.validarVagasDisponiveis(() -> jdbcTemplateEstacionamentoDaoImpl.getVagasDisponiveis());

        PlacaUtils.validarPlaca(request.getPlaca());
        String dataEntradaConvertida = DataUtils.converterData(request.getDataEntrada());
        HoraUtils.validarHora(request.getHoraEntrada());

        /**
         * Constrói o objeto Estacionamento para entrada.
         *
         * @param request Dados da requisição.
         */
        Estacionamento estacionamento = Estacionamento.builder()
                .placa(request.getPlaca())
                .dataEntrada(dataEntradaConvertida)
                .horaEntrada(request.getHoraEntrada())
                .build();

        jdbcTemplateEstacionamentoDaoImpl.registrarEntrada(estacionamento);

        armazenamentoJsonDaoImpl.salvarEntadaJson(estacionamento);
        return new ResponseEntity<>(new RespostaPadrao(EstacionamentoEnums.PLACA.getMessage() + request.getPlaca() + EstacionamentoEnums.ENTROU_NO_ESTACIONAMENTO.getMessage() + jdbcTemplateEstacionamentoDaoImpl.getVagasDisponiveis()), HttpStatus.OK);
    }

    /**
     * Registra a saída de um veículo do estacionamento.
     *
     * @param request Dados da requisição de saída do veículo.
     * @return Resposta padrão indicando sucesso ou falha.
     * @throws PlacaException se a placa for inválida.
     * @throws DataException se a data ou hora forem inválidas.
     */
    public ResponseEntity<RespostaPadrao> registrarSaida(EstacionamentoRequestDTO request) {
        try {
            /**
             * Valida e retorna uma mensagem caso algum parâmetro esteja fora do nomrmal.
             */
            SaidaUtils.validarCarroJaSaiu(jdbcTemplateEstacionamentoDaoImpl, request.getPlaca());
            Estacionamento estacionamentoEntrada = jdbcTemplateEstacionamentoDaoImpl.bucarCarroPorPlaca(request.getPlaca());
            SaidaUtils.validarEstacionamentoNaoNulo(estacionamentoEntrada, request.getPlaca());
            SaidaUtils.validarCarroJaSaiu(jdbcTemplateEstacionamentoDaoImpl, request.getPlaca());

            PlacaUtils.validarPlaca(request.getPlaca());
            String dataSaidaConvertida = DataUtils.converterData(request.getDataSaida());
            HoraUtils.validarHora(request.getHoraSaida());

            /**
             * Constrói o objeto Estacionamento para saida.
             *
             * @param request Dados da requisição.
             */
            Estacionamento estacionamento = Estacionamento.builder()
                    .placa(request.getPlaca())
                    .dataEntrada(estacionamentoEntrada.getDataEntrada())
                    .horaEntrada(estacionamentoEntrada.getHoraEntrada())
                    .dataSaida(dataSaidaConvertida)
                    .horaSaida(request.getHoraSaida())
                    .build();

            jdbcTemplateEstacionamentoDaoImpl.registrarSaida(estacionamento);

            armazenamentoJsonDaoImpl.atualizarSaidaJson(estacionamento);

            /**
             * Retorna mensagem adequada para cada parametro.
             */
            return new ResponseEntity<>(new RespostaPadrao(EstacionamentoEnums.PLACA.getMessage() + request.getPlaca() + EstacionamentoEnums.SAIU_DO_ESTACIONAMENTO.getMessage() + jdbcTemplateEstacionamentoDaoImpl.getVagasDisponiveis()), HttpStatus.OK);
        } catch (PlacaException e) {
            return new ResponseEntity<>(new RespostaPadrao(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (DataException e) {
            return new ResponseEntity<>(new RespostaPadrao(EstacionamentoEnums.ERRO_DATA_FORNECIDA.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Busca um veículo no estacionamento pela placa.
     *
     * @param request Dados da requisição contendo a placa do veículo.
     * @return O veículo encontrado ou mensagem de erro se não encontrado.
     */
    public ResponseEntity<?> bucarCarroPorPlaca(EstacionamentoRequestDTO request) {
        Estacionamento estacionamento = jdbcTemplateEstacionamentoDaoImpl.bucarCarroPorPlaca(request.getPlaca());

        Optional<RespostaPadrao> resposta = BuscarCarroPorPlacaUtils.verificarEstacionamentoNulo(estacionamento);
        if (resposta.isPresent()) {
            return new ResponseEntity<>(resposta.get(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(estacionamento, HttpStatus.OK);
        }
    }

    /**
     * Lista todos os registros de veículos no estacionamento.
     *
     * @return Lista de todos os veículos registrados.
     */
    public ResponseEntity<?> listarTodosRegistros() {
        List<Estacionamento> estaciona = jdbcTemplateEstacionamentoDaoImpl.findAll();

        Optional<RespostaPadrao> resposta = ListarTodosRegistrosUtils.verificarListaCompleta(estaciona);
        if (resposta.isPresent()) {
            return new ResponseEntity<>(resposta.get(), HttpStatus.NOT_FOUND);
        }

        List<EstacionamentoComCusto> carrosAtivosComCusto = ListarTodosRegistrosUtils.criarListaComCusto(estaciona);
        return new ResponseEntity<>(carrosAtivosComCusto, HttpStatus.OK);
    }

    /**
     * Lista todos os veículos ativos no estacionamento.
     *
     * @return Lista de veículos ativos.
     */
    public ResponseEntity<?> listarCarrosAtivos() {
        List<Estacionamento> carrosAtivos = jdbcTemplateEstacionamentoDaoImpl.listarCarrosAtivos();

        Optional<RespostaPadrao> resposta = ListarCarrosAtivosUtils.verificarListaVazia(carrosAtivos);
        if (resposta.isPresent()) {
            return new ResponseEntity<>(resposta.get(), HttpStatus.NOT_FOUND);
        }

        List<EstacionamentoComCusto> carrosAtivosComCusto = ListarCarrosAtivosUtils.criarListaComCusto(carrosAtivos);
        return new ResponseEntity<>(carrosAtivosComCusto, HttpStatus.OK);
    }


    /**
     * Lista todos os veículos que já saíram do estacionamento.
     *
     * @return Lista de veículos que já saíram.
     */
    public ResponseEntity<?> listarCarrosQueSairam() {
        List<Estacionamento> carrosQueSairam = jdbcTemplateEstacionamentoDaoImpl.listarCarrosQueSairam();

        Optional<RespostaPadrao> resposta = ListarCarrosQueSairamUtils.verificarListaVazia(carrosQueSairam);
        if (resposta.isPresent()) {
            return new ResponseEntity<>(resposta.get(), HttpStatus.NOT_FOUND);
        }

        List<EstacionamentoComCusto> carrosQueSairamComCusto = ListarCarrosQueSairamUtils.criarListaComCusto(carrosQueSairam);
        return new ResponseEntity<>(carrosQueSairamComCusto, HttpStatus.OK);
    }


    /**
     * Exclui registros de veículos no estacionamento por data de entrada.
     *
     * @param request Dados da requisição contendo a data de entrada.
     * @return Resposta padrão indicando sucesso ou falha.
     * @throws DataException se a data for inválida.
     */
    public ResponseEntity<RespostaPadrao> excluirRegistrosPorData(EstacionamentoRequestDTO request) throws DataException {
        String dataEntradaConvertida = DataUtils.converterData(request.getDataEntrada());
        int registrosExcluidos = jdbcTemplateEstacionamentoDaoImpl.excluirRegistrosPorData(dataEntradaConvertida);

        Optional<RespostaPadrao> resposta = ExcluirRegistrosPorDataUtils.verificarRegistrosExcluidos(registrosExcluidos);
        if (resposta.isPresent()) {
            return new ResponseEntity<>(resposta.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new RespostaPadrao(EstacionamentoEnums.EXCLUIR_REGISTRO_POR_DATA.getMessage()), HttpStatus.OK);
        }
    }

    /**
     * Calcula o custo de permanência de um veículo no estacionamento pela placa.
     *
     * @param placa Placa do veículo.
     * @return Resposta padrão com o custo calculado.
     */
    public ResponseEntity<RespostaPadrao> calcularCusto(String placa) {
        Estacionamento estacionamento = jdbcTemplateEstacionamentoDaoImpl.bucarCarroPorPlaca(placa);

        Optional<RespostaPadrao> resposta = CalcularCustoUtils.verificarEstacionamentoNulo(estacionamento);
        if (resposta.isPresent()) {
            return new ResponseEntity<>(resposta.get(), HttpStatus.NOT_FOUND);
        }

        EstacionamentoComCusto estacionamentoComCusto = new EstacionamentoComCusto(estacionamento);
        double custo = estacionamentoComCusto.calcularCusto();
        return new ResponseEntity<>(new RespostaPadrao(EstacionamentoEnums.CUSTO_CALCULO.getMessage() + custo), HttpStatus.OK);
    }
}
