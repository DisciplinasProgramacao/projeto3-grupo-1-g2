package modules;

public class Horista extends Cliente {

    public Horista(String p_nome, String p_cpf) {
        super(p_nome, p_cpf);
    }

    @Override
    public double arrecadadoTotal() {
        double totalArrecadado = this.getVeiculos().stream()
                .mapToDouble(Veiculo::totalArrecadado)
                .sum();
        return totalArrecadado;
    }

    @Override
    public double arrecadadoNoMes(int p_mes) {
        double totalMes = 0;
        for (Veiculo veiculo : this.getVeiculos()) {
            totalMes += veiculo.arrecadadoNoMes(p_mes);
        }
        return totalMes;
    }

}
