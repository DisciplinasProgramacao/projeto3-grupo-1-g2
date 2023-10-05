package modules;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class UsoDeVaga {
    private  double FRACAO_USO = 0.25;
    private  double VALOR_FRACAO = 4.0;

    private  double VALOR_MAXIMO = 50.0;

    private ArrayList<Vaga> vaga;

    private LocalDateTime entrada;

    private  LocalDateTime saida;

    private double valorPago;

    public UsoDeVaga(ArrayList<Vaga> vaga, LocalDateTime entrada, LocalDateTime saida, double valorPago) {
        this.vaga = vaga;
        this.entrada = entrada;
        this.saida = saida;
        this.valorPago = valorPago;
    }

    public void Sair() {
        this.saida = LocalDateTime.now();
    }

    public void valorPago() {
        if (this.saida == null) {
            this.saida = LocalDateTime.now();
        }

        long minutos = this.entrada.until(this.saida, ChronoUnit.MINUTES);

        double valor = 0.0;
    }
}
