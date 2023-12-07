package modules;

import java.util.List;

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

    @Override
    public void addObservador() {
        ObservadoraCliente observador = new ObservadoraCliente();
        this.setObservadores(observador);
    }

    public static void gerarRelatorioArrecadacaoMedia(List<Horista> horistas, int mes) {
        double totalArrecadado = 0;
        int totalClientes = horistas.size();

        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório de Arrecadação Média dos Clientes Horistas\n");
        relatorio.append("Mês Especificado: ").append(mes).append("\n");

        for (Horista horista : horistas) {
            double arrecadadoNoMes = horista.arrecadadoNoMes(mes);
            totalArrecadado += arrecadadoNoMes;
            relatorio.append("Cliente: ").append(horista.getNome())
                    .append(", CPF: ").append(horista.getCpf())
                    .append(", Arrecadado no Mês: R$").append(arrecadadoNoMes)
                    .append("\n");
        }

        double mediaArrecadacao = totalClientes > 0 ? totalArrecadado / totalClientes : 0;
        relatorio.append("Arrecadação Média no Mês Especificado: R$").append(mediaArrecadacao).append("\n");

        System.out.println(relatorio.toString());
    }

}
