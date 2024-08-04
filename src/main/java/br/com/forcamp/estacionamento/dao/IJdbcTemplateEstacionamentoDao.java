package br.com.forcamp.estacionamento.dao;

import br.com.forcamp.estacionamento.model.estacionamento.Estacionamento;

import java.util.List;


/**
 * Interface para operações de banco de dados relacionadas ao Estacionamento usando JdbcTemplate.
 * Esta interface define métodos para registrar entrada e saída de carros, verificar se um carro já está registrado ou saiu do registro,
 * buscar carros por placa, listar todos os carros ou apenas os ativos ou que saíram, calcular custo, excluir registros por data,
 * e gerenciar vagas disponíveis no estacionamento.
 */
public interface IJdbcTemplateEstacionamentoDao {
    public void registrarEntrada(Estacionamento estacionamento);
    public void registrarSaida(Estacionamento estacionamento);
    public boolean carroJaRegistrado(String placa);
    public boolean carroJaSaiuDoRegistro(String placa);
    public Estacionamento bucarCarroPorPlaca(String placa);
    public List<Estacionamento> findAll();
    public List<Estacionamento> listarCarrosAtivos();
    public List<Estacionamento> listarCarrosQueSairam();
    public double calcularCusto(String placa);
    public int excluirRegistrosPorData(String dataEntrada);
    public void diminuirVagas();
    public void aumentarVagas();
    public int getVagasDisponiveis();
}
