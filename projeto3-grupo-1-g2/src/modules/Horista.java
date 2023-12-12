package modules;

import java.util.List;

/**
 * Representa um cliente do tipo Horista, que é uma subclasse de Cliente.
 * Clientes Horistas têm métodos específicos para cálculos de arrecadação.
 *
 */
public class Horista extends Cliente {

    //#region Construtores

    /**
     * Construtor da classe Horista.
     *
     * @param p_nome O nome do cliente Horista.
     * @param p_cpf O CPF do cliente Horista.
     */
    public Horista(String p_nome, String p_cpf) {
        super(p_nome, p_cpf);
    }

    /**
     * Calcula o valor total arrecadado pelos veículos do cliente Horista.
     *
     * @return O valor total arrecadado pelos veículos do cliente Horista.
     */
    @Override
    public double arrecadadoTotal() {
        double totalArrecadado = this.getVeiculos().stream()
                .mapToDouble(Veiculo::totalArrecadado)
                .sum();
        return totalArrecadado;
    }

    /**
     * Calcula o valor arrecadado pelos veículos do cliente Horista no mês especificado.
     *
     * @param p_mes O mês para o qual se deseja calcular a arrecadação.
     * @return O valor arrecadado pelos veículos do cliente Horista no mês.
     */
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
    /**
     * Gera um relatório de arrecadação média para um grupo de clientes Horistas no mês especificado.
     *
     * @param horistas A lista de clientes Horistas para os quais o relatório será gerado.
     * @param mes O mês para o qual se deseja gerar o relatório.
     */
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
