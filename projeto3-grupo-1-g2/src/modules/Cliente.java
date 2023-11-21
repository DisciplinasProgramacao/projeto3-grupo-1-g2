package modules;

import java.util.HashMap;

public abstract class Cliente{
    protected String nome;
    protected String cpf;
    protected HashMap<String, Veiculo> veiculos;

    public void addVeiculo(Veiculo p_veiculo) {
        if(veiculos == null){
            veiculos = new HashMap<>();
        }
        veiculos.put(p_veiculo.getPlaca(), p_veiculo);
    }

    public Veiculo possuiVeiculo(String p_placa) {
        if(veiculos == null){
            veiculos = new HashMap<>();
        }
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

    public int totalDeUsos(){
        int totalArrecadado = 0;
        for(String placa : veiculos.keySet()) {
            totalArrecadado += veiculos.get(placa).totalDeUsos();
        }
//        for (Veiculo veiculo : veiculos) {
//            totalArrecadado += veiculo.totalDeUsos();
//        }
        return totalArrecadado;
    }


    public abstract double arrecadadoTotal();

    public abstract double arrecadadoNoMes(int p_mes);



}
