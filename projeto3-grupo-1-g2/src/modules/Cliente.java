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
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(p_placa)) {
                return true;
            }
        }
        return false;
    }

    public int totalDeUsos() {
        int totalDeUsos = 0;
        for (Veiculo veiculo : veiculos) {
            totalDeUsos += veiculo.totalDeUsos();
        }
        return totalDeUsos;
    }

    public double arrecadadoPorVeiculo(String p_placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(p_placa)) {
                return veiculo.totalArrecadado();
            }
        }
        // for (String placa : veiculos.keySet()) {
        // if (placa.equals(p_placa)) {
        // return veiculos.get(placa).totalArrecadado();
        // }
        // }
        return 0;
    }

    public abstract double arrecadadoTotal();

    public abstract double arrecadadoNoMes(int p_mes);
}
