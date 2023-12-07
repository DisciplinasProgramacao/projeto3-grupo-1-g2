package modules;

import java.time.LocalDate;
import java.util.List;

public class Mensalista extends Cliente{


    public Mensalista(String p_nome, String p_cpf) {
        super(p_nome, p_cpf);
    }

    @Override
    public double arrecadadoTotal() {
        return 500.00;
    }

    @Override
    public double arrecadadoNoMes(int p_mes) {
        return 500.00;
    }

    @Override
    public void addObservador() {
        ObservadoraCliente observador = new ObservadoraCliente();
        this.setObservadores(observador);
    }

    public static void gerarRelatorioUsoMensalistas(List<Mensalista> mensalistas) {
        int mesAtual = LocalDate.now().getMonthValue();
        int anoAtual = LocalDate.now().getYear();
        int totalUsosMensalistas = 0;

        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório de Uso dos Clientes Mensalistas\n");
        relatorio.append("Mês/Ano Atual: ").append(mesAtual).append("/").append(anoAtual).append("\n");

        for (Mensalista mensalista : mensalistas) {
            int totalUsosMensalista = mensalista.getVeiculos().stream()
                .mapToInt(veiculo -> veiculo.getUsos().size())
                .sum();
            totalUsosMensalistas += totalUsosMensalista;
        }

        for (Mensalista mensalista : mensalistas) {
            int usosMensalista = mensalista.getVeiculos().stream()
                .mapToInt(veiculo -> (int) veiculo.getUsos().stream()
                    .filter(uso -> uso.getEntrada().getMonthValue() == mesAtual &&
                                   uso.getEntrada().getYear() == anoAtual)
                    .count())
                .sum();

            double percentualUso = totalUsosMensalistas > 0 ? 
                                   (double) usosMensalista / totalUsosMensalistas * 100 : 0;

            relatorio.append("Cliente: ").append(mensalista.getNome())
                    .append(", CPF: ").append(mensalista.getCpf())
                    .append(", Total de Usos no Mês: ").append(usosMensalista)
                    .append(", Percentual de Uso: ").append(String.format("%.2f", percentualUso)).append("%")
                    .append("\n");
        }

        System.out.println(relatorio.toString());
    }

}
