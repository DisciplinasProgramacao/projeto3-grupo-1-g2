package modules;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Veiculo {

    private String placa;
    private List<UsoDeVaga> usos;

    public Veiculo(String placa) {
        this.placa = placa;
        this.usos = new ArrayList<UsoDeVaga>();
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public List<UsoDeVaga> getUsos() {
        return usos;
    }

    public void estacionar(Vaga vaga) {
        UsoDeVaga uso = new UsoDeVaga(vaga, LocalDateTime.now(), null, 0, false, false, false);
        usos.add(uso);
        vaga.estacionar();
    }

    public double sair() throws Exception {
        UsoDeVaga ultimoUso = usos.get(usos.size() - 1);
        try {
            return ultimoUso.sair();

        } catch (Exception e) {
            throw e;
        }
    }

    public double totalArrecadado() {
        double total = 0;
        for (UsoDeVaga uso : usos) {
            total += uso.getValorPago();
        }
        return total;
    }

    public double arrecadadoNoMes(int mes) {
        double totalMes = 0;
        for (UsoDeVaga uso : usos) {
            if (uso.getEntrada().getMonthValue() == mes) {
                totalMes += uso.getValorPago();
            }
        }
        return totalMes;
    }

    public int totalDeUsos() {
        return usos.size();
    }
}
