package br.com.forcamp.estacionamento.usecase;

import br.com.forcamp.estacionamento.dao.impl.ArmazenamentoJsonDaoImpl;
import br.com.forcamp.estacionamento.dao.impl.JdbcTemplateClienteDaoImpl;
import br.com.forcamp.estacionamento.dtos.ClienteRequestDTO;
import br.com.forcamp.estacionamento.enums.ClienteEnums;
import br.com.forcamp.estacionamento.exception.cliente.*;
import br.com.forcamp.estacionamento.model.cliente.Cliente;
import br.com.forcamp.estacionamento.model.RespostaPadrao;
import br.com.forcamp.estacionamento.utils.Cliente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço para gerenciamento de clientes.
 */
@Service
public class ClienteService {

    // Declaração de dependências
    private final JdbcTemplateClienteDaoImpl jdbcTemplateClienteDaoImpl;
    private final ArmazenamentoJsonDaoImpl armazenamentoJsonDaoImpl;

    /**
     * Construtor da classe ClienteService.
     *
     * @param jdbcTemplateClienteDaoImpl Implementação do DAO para Cliente usando JdbcTemplate.
     * @param armazenamentoJsonDaoImpl Implementação do DAO para armazenamento em JSON.
     */
    @Autowired
    public ClienteService(JdbcTemplateClienteDaoImpl jdbcTemplateClienteDaoImpl, ArmazenamentoJsonDaoImpl armazenamentoJsonDaoImpl) {
        this.jdbcTemplateClienteDaoImpl = jdbcTemplateClienteDaoImpl;
        this.armazenamentoJsonDaoImpl = armazenamentoJsonDaoImpl;
    }

    /**
     * Salva os dados cadastrais de um cliente.
     *
     * @param request Dados do cliente a ser cadastrado.
     * @return Resposta indicando o sucesso ou falha do cadastro.
     * @throws NomeException Se o nome for inválido.
     * @throws PlacaException Se a placa for inválida.
     * @throws RgException Se o RG for inválido.
     * @throws TelefoneException Se o telefone for inválido.
     * @throws DataException Se a data for inválida.
     */
    public ResponseEntity<RespostaPadrao> salvarDadosCadastrais(ClienteRequestDTO request) throws NomeException, PlacaException, RgException, TelefoneException, DataException {
        Cliente clienteExistente = jdbcTemplateClienteDaoImpl.buscarClientePorRg(request.getRg());
        if (clienteExistente != null) {
            return new ResponseEntity<>(new RespostaPadrao(ClienteEnums.CLIENTE_JA_EXISTE.getMessage()), HttpStatus.BAD_REQUEST);
        }

        NomeUtils.validarNome(request.getNome());
        RgUtils.validarRg(request.getRg());
        TelefoneUtils.validarTelefone(request.getTelefone());
        PlacaUtils.validarPlaca(request.getPlacaVeiculo());
        String dataEntradaConvertida = DataUtils.converterData(request.getDataRegistro());

        Cliente cliente = Cliente.builder()
                .nome(request.getNome())
                .rg(request.getRg())
                .telefone(request.getTelefone())
                .placaVeiculo(request.getPlacaVeiculo())
                .dataRegistro(dataEntradaConvertida)
                .build();

        jdbcTemplateClienteDaoImpl.salvarDadosCadastrais(cliente);
        armazenamentoJsonDaoImpl.salvarClienteJson(cliente);
        return new ResponseEntity<>(new RespostaPadrao(ClienteEnums.CADASTRO_COM_SUCESSO.getMessage()), HttpStatus.OK);
    }

    /**
     * Método para buscar todos os clientes.
     *
     * @return Resposta contendo a lista de clientes ou uma mensagem de erro.
     */
    public ResponseEntity<?> buscarTodosClientes() {
        List<Cliente> clientes = jdbcTemplateClienteDaoImpl.findAll();
        String message = BuscarTodosClienteUtils.verificarClientes(clientes);
        if (message != null) {
            return new ResponseEntity<>(new RespostaPadrao(message), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        }
    }

    /**
     * Método para buscar um cliente por RG.
     *
     * @param request Dados do cliente a ser buscado.
     * @return Resposta contendo os dados do cliente ou uma mensagem de erro.
     */
    public ResponseEntity<?> buscarClientePorRg(ClienteRequestDTO request) {
        Cliente cliente = jdbcTemplateClienteDaoImpl.buscarClientePorRg(request.getRg());
        Object resposta = BuscarClientePorRgUtils.verificarCliente(cliente);
        if (resposta instanceof RespostaPadrao) {
            return new ResponseEntity<>(resposta, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(resposta, HttpStatus.OK);
        }
    }

    /**
     * Método para deletar um cliente por RG.
     *
     * @param rg RG do cliente a ser deletado.
     * @return Mensagem indicando o sucesso ou falha da operação.
     */
    public String deletarClientePorRg(String rg) {
        ClienteRequestDTO request = new ClienteRequestDTO();
        request.setRg(rg);
        ResponseEntity<?> response = buscarClientePorRg(request);
        String resposta = DeletarClientePorRgUtils.verificarEExcluirCliente(response, rg);
        if (resposta.equals(ClienteEnums.CLIENTE_EXCLUIDO.getMessage())) {
            jdbcTemplateClienteDaoImpl.excluirClientePorRg(rg);
        }
        return resposta;
    }

    /**
     * Método para deletar clientes por data de registro.
     *
     * @param dataRegistro Data de registro dos clientes a serem deletados.
     * @return Mensagem indicando o sucesso ou falha da operação.
     */
    public String deletarClientesPorDataRegistro(String dataRegistro) {
        List<Cliente> clientes = jdbcTemplateClienteDaoImpl.buscarClientesPorDataRegistro(dataRegistro);
        String resposta = DeletarClientesPorDataUtils.verificarEExcluirClientes(clientes, dataRegistro);
        if (resposta.equals(ClienteEnums.EXCLUIR_TODOS.getMessage())) {
            jdbcTemplateClienteDaoImpl.excluirClientesPorDataRegistro(dataRegistro);
        }
        return resposta;
    }
}