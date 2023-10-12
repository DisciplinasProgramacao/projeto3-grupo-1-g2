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

    public void sair() {
        this.saida = LocalDateTime.now();
    }

    public double calcularValor() {
        long horas = ChronoUnit.HOURS.between(this.entrada, this.saida);
        long minutos = ChronoUnit.MINUTES.between(this.entrada, this.saida);
        double valor = 0.0;

        minutos += horas * 60; // Converte horas para minutos

        if (minutos <= 15) {
            valor = VALOR_FRACAO;
        } else {
            minutos -= 15; // Subtrai os primeiros 15 minutos
            valor = this.VALOR_FRACAO; // Valor da primeira fração de 15 minutos

            if (minutos > 0) {
                int fracoes = (int) Math.ceil((double) minutos / (FRACAO_USO * 60)); // Calcula as fraçoes restantes após 15 minutos
                valor += fracoes * this.VALOR_FRACAO;
            }
        }

        if (valor > this.VALOR_MAXIMO) {
            valor = this.VALOR_MAXIMO;
        }

        return valor;
    }

    public double getValorPago() {
        return this.valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDateTime getEntrada() {
        return this.entrada;
    }
    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public LocalDateTime getSaida() {
        return this.saida;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    public ArrayList<Vaga> getVaga() {
        return this.vaga;
    }

    public void setVaga(ArrayList<Vaga> vaga) {
        this.vaga = vaga;
    }
}
