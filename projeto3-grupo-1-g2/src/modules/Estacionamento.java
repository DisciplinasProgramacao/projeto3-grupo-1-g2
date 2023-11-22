
package modules;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Estacionamento {
    private int id;
    private String local;
    private HashMap<Cliente, List<Veiculo>> clientesVeiculos;
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

    public boolean estacionar(Veiculo veiculo) {
        for (Cliente cliente : clientesVeiculos.keySet()) {
            boolean possuiVeiculo = cliente.possuiVeiculo(veiculo.getPlaca());

            if (possuiVeiculo) {
                for (Vaga vaga : vagas) {
                    if (vaga.disponivel()) {
                        veiculo.estacionar(vaga);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean sair(Veiculo veiculo) {
        try {
            double valor = veiculo.sair();
            System.out.println("O valor pago foi : " + valor);
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

    public double valoMedioPorUso() {
        double valor = 0;
        int qtd = 0;
        for (Cliente cliente : clientesVeiculos.keySet()) {
            valor += cliente.arrecadadoTotal();
            qtd++;
        }
        return valor / qtd;
    }

    // public String top5Clientes(int mes){
    // double valor = 0;
    // ArrayList<Cliente> top5Mes = new ArrayList<>();
    // for (Cliente cliente: clientes) {
    // valor = cliente.arrecadadoNoMes(mes);
    // for (Cliente cli: top5Mes) {
    // if(valor > cli.arrecadadoNoMes(mes)){
    //
    // }
    // }
    // }
    // }

    public String getLocal() {
        return local;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}