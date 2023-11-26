package modules;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    private String nome;
    private String cpf;
    private List<Veiculo> veiculos;

    public Cliente(String p_nome, String p_cpf) {
        nome = p_nome;
        cpf = p_cpf;
        veiculos = new ArrayList<>();
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

    public void addVeiculo(Veiculo p_veiculo) {
        veiculos.add(p_veiculo);
    }

    public boolean possuiVeiculo(String p_placa) {
        return veiculos.stream()
                .anyMatch(veiculo -> veiculo.getPlaca().equals(p_placa));
    }

    public int totalDeUsos() {
        int totalDeUsos = 0;
        for (Veiculo veiculo : veiculos) {
            totalDeUsos += veiculo.totalDeUsos();
        }
        return totalDeUsos;
    }

    public double arrecadadoPorVeiculo(String p_placa) {
        return veiculos.stream()
                .filter(veiculo -> veiculo.getPlaca().equals(p_placa))
                .findFirst()
                .map(Veiculo::totalArrecadado)
                .orElse(0.0);
    }

    public double arrecadadoTotal() {
        double totalArrecadado = veiculos.stream()
                .mapToDouble(Veiculo::totalArrecadado)
                .sum();
        return totalArrecadado;
    }

    public double arrecadadoNoMes(int p_mes) {
        return veiculos.stream()
                .mapToDouble(veiculo -> veiculo.arrecadadoNoMes(p_mes))
                .sum();
    }
}
