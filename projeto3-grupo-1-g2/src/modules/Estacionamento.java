
package modules;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Estacionamento {
    private int id;
    private String local;
    public HashMap<Cliente, List<Veiculo>> clientesVeiculos;
    private int quantFileiras;
    private int vagasPorFileira;
    private ArrayList<Vaga> vagas;

    public Estacionamento(String local, int quantFileiras, int vagasPorFileira) {
        this.local = local;
        this.quantFileiras = quantFileiras;
        this.vagasPorFileira = vagasPorFileira;
        this.clientesVeiculos = new HashMap<>();
        this.vagas = new ArrayList<>();
        this.criarVagas();
        this.vagas = gerarVagas(1);
    }

    public void addCliente(Cliente cliente) {
        clientesVeiculos.put(cliente, cliente.getVeiculos());
    }

    private void criarVagas() {
        for (int i = 0; i < this.quantFileiras; i++) {
            for (int j = 0; j < this.vagasPorFileira; j++) {
                vagas.add(new Vaga(Integer.parseInt(String.valueOf(i) + String.valueOf(j))));
            }
        }
    }

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

    private ArrayList<Vaga> gerarVagas(int numero) {
        for (int i = 0; i < this.quantFileiras; i++) {
            for (int j = 0; j < this.vagasPorFileira; j++) {
                vagas.add(new Vaga(
                        Integer.parseInt(String.valueOf(this.quantFileiras) + String.valueOf(this.vagasPorFileira))));
            }
        }
        return vagas;
    }

    public boolean sair(Veiculo veiculo, Integer p_valorParaAdicionarNoDateTimeNow) {
        try {
            double valor = veiculo.sair(p_valorParaAdicionarNoDateTimeNow);
            System.out.println("O valor pago foi: R$" + valor);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public double totalArrecadado() {
        double valor = 0;
        for (Cliente cliente : clientesVeiculos.keySet()) {
            valor += cliente.arrecadadoTotal();
        }
        return valor;
    }

    public double totalArrecadadoNoMes(int mes) {
        double valor = 0;
        for (Cliente cliente : clientesVeiculos.keySet()) {
            valor += cliente.arrecadadoNoMes(mes);
        }
        return valor;
    }

    public double valorMedioPorUso() {
        double valor = 0;
        int qtd = 0;
        for (Cliente cliente : clientesVeiculos.keySet()) {
            valor += cliente.arrecadadoTotal();
            qtd++;
        }
        return valor / qtd;
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

    public boolean addClienteToEstacionamento(Cliente cliente) {
        List<Veiculo> veiculosCliente = cliente.getVeiculos();

        if (!clientesVeiculos.containsKey(cliente)) {
            clientesVeiculos.put(cliente, veiculosCliente);
            return true;
        }
        return false;
    }

    public HashMap<Cliente, List<Veiculo>> getClientesVeiculos() {
        return this.clientesVeiculos;
    }
}