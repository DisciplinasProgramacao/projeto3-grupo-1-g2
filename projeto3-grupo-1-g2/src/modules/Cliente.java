package modules;

import java.util.HashMap;

public class Cliente {
    private String nome;
    private String cpf;
    private HashMap<String, Veiculo> veiculos;

    public Cliente(String p_nome, String p_cpf) {
        nome = p_nome;
        cpf = p_cpf;
        veiculos = new HashMap<>();
    }

    public void addVeiculo(Veiculo p_veiculo) {
        veiculos.put(p_veiculo.getPlaca(), p_veiculo);
    }

    public Veiculo possuiVeiculo(String p_placa) {
        for(String placa : veiculos.keySet()) {
            if(placa.equals(p_placa)) {
                return veiculos.get(placa);
            }
        }
//        for (Veiculo veiculo : veiculos) {
//            if (veiculo.getPlaca().equals(p_placa)) {
//                return veiculo;
//            }
//        }
        return null;
    }

    public int totalDeUsos() {
        int totalArrecadado = 0;
        for(String placa : veiculos.keySet()) {
            totalArrecadado += veiculos.get(placa).totalDeUsos();
        }
//        for (Veiculo veiculo : veiculos) {
//            totalArrecadado += veiculo.totalDeUsos();
//        }
        return totalArrecadado;
    }

    public double arrecadadoPorVeiculo(String p_placa) {
        for(String placa : veiculos.keySet()) {
            if(placa.equals(p_placa)) {
                return veiculos.get(placa).totalArrecadado();
            }
        }
//        for (Veiculo veiculo : veiculos) {
//            if (veiculo.getPlaca().equals(p_placa)) {
//                return veiculo.totalArrecadado();
//            }
//        }
        return 0;
    }

    public double arrecadadoTotal() {
        double totalArrecadado = 0;
        for(String placa : veiculos.keySet()) {
            totalArrecadado += veiculos.get(placa).totalArrecadado();
        }
//        for (Veiculo veiculo : veiculos) {
//            totalArrecadado += veiculo.totalArrecadado();
//        }
        return totalArrecadado;
    }

    public double arrecadadoNoMes(int p_mes) {
        double total = 0;
        for(String placa : veiculos.keySet()) {
            total += veiculos.get(placa).arrecadadoNoMes(p_mes);
        }
//        for (Veiculo veiculo : veiculos) {
//            total += veiculo.arrecadadoNoMes(p_mes);
//        }
        return total;
    }
}
