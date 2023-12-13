
package modules;

import java.util.*;

/**
 * Representa um estacionamento que gerencia o estacionamento de veículos para clientes.
 * Cada estacionamento possui um local, quantidade de fileiras, quantidade de vagas por fileira,
 * uma lista de clientes associados e uma lista de vagas disponíveis.
 *
 */

    //#region atributos
public class Estacionamento implements IObservadorEstacionamento{
    private int id;
    private String local;
    public HashMap<Cliente, List<Veiculo>> clientesVeiculos;
    private int quantFileiras;
    private int vagasPorFileira;
    private ArrayList<Vaga> vagas;
    //#endregion

    //#region Construtores

    /**
     * Construtor da classe Estacionamento.
     *
     * @param local O local do estacionamento.
     * @param quantFileiras A quantidade de fileiras no estacionamento.
     * @param vagasPorFileira A quantidade de vagas por fileira no estacionamento.
     */
    public Estacionamento(String local, int quantFileiras, int vagasPorFileira) {
        this.local = local;
        this.quantFileiras = quantFileiras;
        this.vagasPorFileira = vagasPorFileira;
        this.clientesVeiculos = new HashMap<>();
        this.vagas = new ArrayList<>();
        this.criarVagas();
        this.vagas = gerarVagas(1);
    }

    /**
     * Adiciona um cliente ao estacionamento.
     *
     * @param cliente O cliente a ser adicionado.
     */
    public void addCliente(Cliente cliente) {
        clientesVeiculos.put(cliente, cliente.getVeiculos());
    }

    /**
     * Cria as vagas no estacionamento com base na quantidade de fileiras e vagas por fileira.
     */
    private void criarVagas() {
        for (int i = 0; i < this.quantFileiras; i++) {
            for (int j = 0; j < this.vagasPorFileira; j++) {
                vagas.add(new Vaga(Integer.parseInt(String.valueOf(i) + String.valueOf(j))));
            }
        }
    }

    /**
     * Estaciona um veículo no estacionamento.
     *
     * @param veiculo O veículo a ser estacionado.
     * @param usadoManobrista Indica se foi utilizado o serviço de manobrista.
     * @param usadoLavagem Indica se foi utilizado o serviço de lavagem.
     * @param usadoPolimento Indica se foi utilizado o serviço de polimento.
     * @return TRUE se o veículo foi estacionado com sucesso, FALSE caso contrário.
     */
    public boolean estacionar(Veiculo veiculo,  boolean usadoManobrista, boolean usadoLavagem, boolean usadoPolimento) {
        for (Cliente cliente : clientesVeiculos.keySet()) {
            boolean possuiVeiculo = cliente.possuiVeiculo(veiculo.getPlaca());

            if (possuiVeiculo) {
                for (Vaga vaga : vagas) {
                    if (vaga.disponivel()) {
                        veiculo.estacionar(vaga, usadoManobrista, usadoLavagem, usadoPolimento);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Gera as vagas no estacionamento com base no número fornecido.
     *
     * @param numero O número usado para gerar as vagas.
     * @return A lista de vagas gerada.
     */

    private ArrayList<Vaga> gerarVagas(int numero) {
        for (int i = 0; i < this.quantFileiras; i++) {
            for (int j = 0; j < this.vagasPorFileira; j++) {
                vagas.add(new Vaga(
                        Integer.parseInt(String.valueOf(this.quantFileiras) + String.valueOf(this.vagasPorFileira))));
            }
        }
        return vagas;
    }

    /**
     * Permite que um veículo saia do estacionamento e calcula o valor a ser pago.
     *
     * @param veiculo O veículo que está saindo.
     * @param p_valorParaAdicionarNoDateTimeNow O valor a ser adicionado ao DateTime.now().
     * @return TRUE se o veículo saiu com sucesso, FALSE caso contrário.
     */
    public boolean sair(Veiculo veiculo, Integer p_valorParaAdicionarNoDateTimeNow) {
        try {
            veiculo.sair(p_valorParaAdicionarNoDateTimeNow);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * Adiciona um cliente ao estacionamento, associando a lista de veículos do cliente.
     * Se o cliente ainda não existe no estacionamento, é adicionado; caso contrário, a operação é ignorada.
     *
     * @param cliente O cliente a ser adicionado ao estacionamento.
     * @return TRUE se o cliente foi adicionado com sucesso, FALSE se o cliente já existe no estacionamento.
     */
    public boolean addClienteToEstacionamento(Cliente cliente) {
        List<Veiculo> veiculosCliente = cliente.getVeiculos();

        if (!clientesVeiculos.containsKey(cliente)) {
            clientesVeiculos.put(cliente, veiculosCliente);
            return true;
        }
        return false;
    }

    /**
     * Gera um relatório contendo informações sobre a arrecadação total do estacionamento.
     *
     * @return O relatório formatado da arrecadação total do estacionamento.
     */
    public String relatorioArrecadacaoTotal() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório de Arrecadação Total do Estacionamento\n");

        for (Cliente cliente : clientesVeiculos.keySet()) {
           relatorio.append("Cliente: ").append(cliente.getNome())
                   .append(", CPF: ").append(cliente.getCpf())
                   .append(", Arrecadação Total: R$").append(cliente.arrecadadoTotal())
                   .append("\n");
       }

        return relatorio.toString();
    }

    /**
     * Gera um relatório contendo informações sobre a arrecadação total do estacionamento no mês especificado.
     *
     * @param mes O mês para o qual se deseja obter o relatório de arrecadação.
     * @return O relatório formatado da arrecadação total do estacionamento no mês especificado.
     */
    public String relatorioArrecadacaoNoMes(int mes) {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório de Arrecadação no Mês ").append(mes).append("\n");

        for (Cliente cliente : clientesVeiculos.keySet()) {
            double arrecadacaoNoMes = cliente.arrecadadoNoMes(mes);
            relatorio.append("Cliente: ").append(cliente.getNome())
                    .append(", CPF: ").append(cliente.getCpf())
                    .append(", Arrecadação no Mês: R$").append(arrecadacaoNoMes)
                    .append("\n");
        }

        return relatorio.toString();
    }

    /**
     * Gera um relatório contendo informações sobre a arrecadação total do estacionamento no mês especificado.
     *
     * @return O relatório formatado da arrecadação total do estacionamento no mês especificado.
     */
    public String relatorioValorMedioPorUso() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório do Valor Médio por Uso no Estacionamento\n");

        double valorTotal = 0;
        int qtd = 0;

        for (Cliente cliente : clientesVeiculos.keySet()) {
            double arrecadacaoTotalCliente = cliente.arrecadadoTotal();
            valorTotal += arrecadacaoTotalCliente;
            qtd++;

            relatorio.append("Cliente: ").append(cliente.getNome())
                    .append(", CPF: ").append(cliente.getCpf())
                    .append(", Arrecadação Total: R$").append(arrecadacaoTotalCliente)
                    .append("\n");
        }

        double valorMedioPorUso = qtd > 0 ? valorTotal / qtd : 0;

        relatorio.append("Valor Médio por Uso no Estacionamento: R$").append(valorMedioPorUso).append("\n");

        return relatorio.toString();
    }

    public String getLocal() {
        return local;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean clienteExiste(Cliente cliente) {
        return clientesVeiculos.containsKey(cliente);
    }

    public Cliente[] top5Clientes(){
        ArrayList<Cliente> clientes = null;
        for (Cliente cliente : clientesVeiculos.keySet())
        {
            clientes.add(cliente);
        }
        Collections.sort(clientes, Comparator.comparingDouble(Cliente::arrecadadoTotal).reversed());

        int tamanhoArray = Math.min(5, clientes.size());
        Cliente[] topClientes = new Cliente[tamanhoArray];

        for (int i = 0; i < tamanhoArray; i++) {
            topClientes[i] = clientes.get(i);
        }

        return topClientes;
    }

    public HashMap<Cliente, List<Veiculo>> getClientesVeiculos() {
        return this.clientesVeiculos;
    }

    @Override
    public void update() {
        top5Clientes();
    }
}