package modules;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um cliente que utiliza veículos para realizar serviços.
 * Cada cliente possui um nome, CPF e uma lista de veículos associados.
 */
public abstract class Cliente {
    //#region atributos
    private String nome;
    private String cpf;
    private List<Veiculo> veiculos;
    private List<ObservadoraCliente> observadores;

    //#region Construtores

    /**
     * Construtor da classe Cliente.
     * Inicializa um cliente com o nome e CPF fornecidos e uma lista vazia de veículos.
     *
     * @param p_nome O nome do cliente.
     * @param p_cpf O CPF do cliente.
     */
    public Cliente(String p_nome, String p_cpf) {
        nome = p_nome;
        cpf = p_cpf;
        veiculos = new ArrayList<>();
        observadores = new ArrayList<>();
    }

    /**
     * Adiciona um veículo à lista de veículos do cliente.
     *
     * @param p_veiculo O veículo a ser adicionado.
     */
    public void addVeiculo(Veiculo p_veiculo) {
        veiculos.add(p_veiculo);
    }


    /**
     * Verifica se o cliente possui um veículo com a placa especificada.
     *
     * @param p_placa A placa do veículo a ser verificado.
     * @return TRUE se o cliente possui o veículo, FALSE caso contrário.
     */
    public boolean possuiVeiculo(String p_placa) {
        return veiculos.stream()
                .anyMatch(veiculo -> veiculo.getPlaca().equals(p_placa));
    }

    /**
     * Obtém o total de usos de veículos pelo cliente.
     *
     * @return O total de usos de veículos pelo cliente.
     */
    public int totalDeUsos() {
        int totalDeUsos = 0;
        for (Veiculo veiculo : veiculos) {
            totalDeUsos += veiculo.totalDeUsos();
        }
        return totalDeUsos;
    }

    /**
     * Obtém o valor arrecadado por um veículo específico do cliente.
     *
     * @param p_placa A placa do veículo.
     * @return O valor arrecadado pelo veículo.
     */
    public double arrecadadoPorVeiculo(String p_placa) {
        return veiculos.stream()
                .filter(veiculo -> veiculo.getPlaca().equals(p_placa))
                .findFirst()
                .map(Veiculo::totalArrecadado)
                .orElse(0.0);
    }

    /**
     * Obtém o valor total arrecadado por todos os veículos do cliente.
     *
     * @return O valor total arrecadado pelos veículos do cliente.
     */
    public double arrecadadoTotal() {
        double totalArrecadado = veiculos.stream()
                .mapToDouble(Veiculo::totalArrecadado)
                .sum();
        return totalArrecadado;
    }

    /**
     * Obtém o valor arrecadado pelos veículos do cliente no mês especificado.
     *
     * @param p_mes O mês para o qual se deseja obter a arrecadação.
     * @return O valor arrecadado pelos veículos do cliente no mês.
     */
    public double arrecadadoNoMes(int p_mes) {
        return veiculos.stream()
                .mapToDouble(veiculo -> veiculo.arrecadadoNoMes(p_mes))
                .sum();
    }

    public String getNome() {
        return this.nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public List<Veiculo> getVeiculos() {
        return this.veiculos;
    }

    public List<ObservadoraCliente> getObservadores() {
        return observadores;
    }

    public void setObservadores(ObservadoraCliente observador) {
        this.observadores.add(observador);
    }

    public abstract void addObservador();
}
