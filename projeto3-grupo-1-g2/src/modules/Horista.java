package modules;

import java.util.HashMap;

public class Horista extends Cliente{

    public Horista(String cliente3, String s) {
        super();
    }

    @Override
    public double arrecadadoTotal() {
        double totalArrecadado = 0;
        for(String placa : this.veiculos.keySet()) {
            totalArrecadado += this.veiculos.get(placa).totalArrecadado();
        }
//        for (Veiculo veiculo : veiculos) {
//            totalArrecadado += veiculo.totalArrecadado();
//        }
        return totalArrecadado;
    }

    @Override
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
