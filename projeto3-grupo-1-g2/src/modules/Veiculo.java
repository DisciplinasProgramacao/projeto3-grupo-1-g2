package modules;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Veiculo {
    //#region atributos
    private String placa;
    private List<UsoDeVaga> usos;
    //#endregion

    //#region Construtores

    /**
     * Construtor da classe Veiculo.
     *
     * @param placa A placa única associada ao veículo.
     */
    public Veiculo(String placa) {
        this.placa = placa;
        this.usos = new ArrayList<UsoDeVaga>();
    }

    /**
     * Estaciona o veículo em uma vaga, registrando o uso.
     *
     * @param vaga A vaga onde o veículo será estacionado.
     * @param usadoManobrista Indica se foi utilizado serviço de manobrista.
     * @param usadoLavagem Indica se foi utilizado serviço de lavagem.
     * @param usadoPolimento Indica se foi utilizado serviço de polimento.
     */
    public void estacionar(Vaga vaga,  boolean usadoManobrista, boolean usadoLavagem, boolean usadoPolimento) {
        UsoDeVaga uso = new UsoDeVaga(vaga, LocalDateTime.now(), null, usadoManobrista, usadoLavagem, usadoPolimento);
        usos.add(uso);
        vaga.estacionar();
    }

    /**
     * Registra a saída do veículo da vaga com base no tempo de permanência especificado.
     *
     * @param p_horario O tempo de permanência em minutos.
     * @return O valor a ser pago pelo uso da vaga.
     * @throws Exception Exceção lançada em caso de problemas no processo de saída.
     */
    public double sair(Integer p_horario) throws Exception {
        return sairComParametro(p_horario);
    }

    /**
     * Método privado para registrar a saída do veículo com base no tempo de permanência especificado.
     *
     * @param p_horario O tempo de permanência em minutos.
     * @return O valor a ser pago pelo uso da vaga.
     * @throws Exception Exceção lançada em caso de problemas no processo de saída.
     */
    private double sairComParametro(Integer p_horario) throws Exception {
        UsoDeVaga ultimoUso = usos.get(usos.size() - 1);
        try {
            return ultimoUso.sair(p_horario);

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Calcula o total arrecadado pelos usos de vaga associados a este veículo.
     *
     * @return O total arrecadado.
     */
    public double totalArrecadado() {
        double total = 0;
        for (UsoDeVaga uso : usos) {
            total += uso.getValorPago();
        }
        return total;
    }

    /**
     * Calcula o total arrecadado pelos usos de vaga associados a este veículo em um determinado mês.
     *
     * @param mes O número do mês.
     * @return O total arrecadado no mês especificado.
     */
    public double arrecadadoNoMes(int mes) {
        double totalMes = 0;
        for (UsoDeVaga uso : usos) {
            if (uso.getEntrada().getMonthValue() == mes) {
                totalMes += uso.getValorPago();
            }
        }
        return totalMes;
    }

    /**
     * Gera um relatório formatado com informações sobre os usos de vaga associados a este veículo.
     *
     * @return O relatório formatado.
     */
    public String gerarRelatorioVagas() {
        DateTimeFormatter dfm = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

        Map<LocalDateTime, UsoDeVaga> relatorio = new TreeMap<>();

        for (UsoDeVaga uso : usos) {
            relatorio.put(uso.getEntrada(), uso);
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<LocalDateTime, UsoDeVaga> entry : relatorio.entrySet()) {

            UsoDeVaga uso = entry.getValue();

            sb.append("Vaga: ").append(uso.getVaga().getNumero())
                    .append(", Entrada: ").append(dfm.format(uso.getEntrada()))
                    .append(", Saida: ").append(uso.getSaida() == null ? "N/A" : dfm.format(uso.getSaida()))
                    .append(", Valor Pago: ").append(uso.getValorPago())
                    .append(", Placa Veículo: ").append(this.placa).append("\n");
        }

        return sb.toString();
    }

    /**
     * Obtém o número total de usos de vaga associados a este veículo.
     *
     * @return O número total de usos.
     */
    public int totalDeUsos() {
        return usos.size();
    }

    public String getPlaca() {
        return placa;
    }

    public List<UsoDeVaga> getUsos() {
        return usos;
    }
}
