package modules;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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



    /**
     * Gera um relatório detalhado de todos os usos dos veículos associados a este cliente
     * dentro de um período especificado. O relatório inclui informações como a placa do veículo,
     * data e hora de entrada e saída, e o valor pago por cada uso de vaga.
     *
     * @param inicio Data e hora de início do período para o qual o relatório é gerado.
     *               Deve ser um objeto LocalDateTime formatado.
     * @param fim    Data e hora de fim do período para o qual o relatório é gerado.
     *               Deve ser um objeto LocalDateTime formatado.
     * @return       String contendo o relatório formatado. O relatório é estruturado
     *               com a placa de cada veículo seguida pelos detalhes de cada uso
     *               (data de entrada, data de saída, valor pago) dentro do período especificado.
     *               Se um veículo não teve uso no período, ele será listado sem detalhes adicionais.
     *               Se a data de saída de um uso não estiver disponível, será exibido "N/A" no lugar.
     */
    public String gerarRelatorioPorData(LocalDateTime inicio, LocalDateTime fim) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório de Uso dos Veículos\n");
        relatorio.append("Período: ").append(formatter.format(inicio)).append(" - ").append(formatter.format(fim)).append("\n");

        for (Veiculo veiculo : this.veiculos) {
            relatorio.append("Veículo: ").append(veiculo.getPlaca()).append("\n");
            List<UsoDeVaga> usosFiltrados = veiculo.filtrarUsosPorData(inicio, fim);

            for (UsoDeVaga uso : usosFiltrados) {
                relatorio.append("   Data de Entrada: ").append(formatter.format(uso.getEntrada()));
                relatorio.append(", Data de Saída: ").append(uso.getSaida() != null ? formatter.format(uso.getSaida()) : "N/A");
                relatorio.append(", Valor Pago: R$").append(uso.getValorPago()).append("\n");
            }
        }

        return relatorio.toString();
    }

    /**
     * Gera um relatório detalhado de todos os usos de estacionamento para cada veículo do cliente
     * em um período especificado.
     *
     * @param inicio Data e hora de início do período.
     * @param fim Data e hora de fim do período.
     * @return String contendo o relatório formatado.
     */
    public String gerarRelatorioHistoricoUso(LocalDateTime inicio, LocalDateTime fim) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório de Histórico de Uso do Estacionamento\n");
        relatorio.append("Cliente: ").append(this.nome).append("\n");
        relatorio.append("Período: ").append(formatter.format(inicio)).append(" - ").append(formatter.format(fim)).append("\n");

        for (Veiculo veiculo : this.veiculos) {
            relatorio.append("Veículo: ").append(veiculo.getPlaca()).append("\n");
            List<UsoDeVaga> usosFiltrados = veiculo.filtrarUsosPorData(inicio, fim);

            for (UsoDeVaga uso : usosFiltrados) {
                relatorio.append("   Data de Entrada: ").append(formatter.format(uso.getEntrada()));
                relatorio.append(", Data de Saída: ").append(uso.getSaida() != null ? formatter.format(uso.getSaida()) : "N/A");
                relatorio.append(", Valor Pago: R$").append(uso.getValorPago()).append("\n");
            }
        }

        return relatorio.toString();
    }
  
}
