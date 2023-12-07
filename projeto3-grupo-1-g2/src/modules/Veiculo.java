package modules;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Veiculo implements VeiculoGenerico {

    private String placa;
    private List<UsoDeVaga> usos;

    public Veiculo(String placa) {
        this.placa = placa;
        this.usos = new ArrayList<UsoDeVaga>();
    }

    public String getPlaca() {
        return placa;
    }

    public List<UsoDeVaga> getUsos() {
        return usos;
    }

    @Override
    public void estacionar(Vaga vaga) {
        FabricaUsoVagasGenerico factory = new FabricaUsoVagasGenerico();
        UsoDeVaga uso = factory.CriaUsoVaga(vaga, LocalDateTime.now(),null, false, false, false);
        usos.add(uso);
        vaga.estacionar();
    }

    @Override
    public double sair() throws Exception {
        UsoDeVaga ultimoUso = usos.get(usos.size() - 1);
        try {
            return ultimoUso.sair();

        } catch (Exception e) {
            throw e;
        }
    }
    @Override
    public double totalArrecadado() {
        double total = 0;
        for (UsoDeVaga uso : usos) {
            total += uso.getValorPago();
        }
        return total;
    }
    @Override
    public double arrecadadoNoMes(int mes) {
        double totalMes = 0;
        for (UsoDeVaga uso : usos) {
            if (uso.getEntrada().getMonthValue() == mes) {
                totalMes += uso.getValorPago();
            }
        }
        return totalMes;
    }
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
    public int totalDeUsos() {
        return usos.size();
    }
}
