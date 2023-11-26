package modules;
public class Horista extends Cliente{


    public Horista(String p_nome, String p_cpf) {
        super(p_nome, p_cpf);
    }

    @Override
    public double arrecadadoTotal() {
        double totalArrecadado = 0;
        for(Veiculo veiculo : this.getVeiculos() ) {
            totalArrecadado += veiculo.totalArrecadado();
        }
//        for (Veiculo veiculo : veiculos) {
//            totalArrecadado += veiculo.totalArrecadado();
//        }
        return totalArrecadado;
    }

    @Override
    public double arrecadadoNoMes(int p_mes) {
        double totalMes = 0;
        for(Veiculo veiculo : this.getVeiculos() )  {
            totalMes += veiculo.arrecadadoNoMes(p_mes);
        }
//        for (Veiculo veiculo : veiculos) {
//            total += veiculo.arrecadadoNoMes(p_mes);
//        }
        return totalMes;
    }

}
