package modules;

import java.time.LocalDate;
import java.util.List;

/**
 * Representa um cliente do tipo Mensalista, que é uma subclasse de Cliente.
 * Clientes Mensalistas têm métodos específicos para cálculos de arrecadação e relatórios de uso.
 *
 */
public class Mensalista extends Cliente{
    //#region Construtores

    /**
     * Construtor da classe Mensalista.
     *
     * @param p_nome O nome do cliente Mensalista.
     * @param p_cpf O CPF do cliente Mensalista.
     */
    public Mensalista(String p_nome, String p_cpf) {
        super(p_nome, p_cpf);
    }

    /**
     * Retorna o valor total arrecadado pelos veículos do cliente Mensalista.
     *
     * @return O valor total arrecadado pelos veículos do cliente Mensalista.
     */
    @Override
    public double arrecadadoTotal() {
        return 500.00;
    }

    /**
     * Retorna o valor arrecadado pelos veículos do cliente Mensalista no mês especificado.
     *
     * @param p_mes O mês para o qual se deseja calcular a arrecadação.
     * @return O valor arrecadado pelos veículos do cliente Mensalista no mês.
     */
    @Override
    public double arrecadadoNoMes(int p_mes) {
        return 500.00;
    }


    /**
     * Gera um relatório de uso dos clientes Mensalistas para o mês/ano atual.
     *
     * @param mensalistas A lista de clientes Mensalistas para os quais o relatório será gerado.
     */
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
