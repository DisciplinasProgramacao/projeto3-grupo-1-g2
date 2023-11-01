package modules;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class UsoDeVaga {
    private double FRACAO_USO = 0.25;
    private double VALOR_FRACAO = 4.0;
    private double VALOR_MAXIMO = 50.0;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago;
    private servicosDisponiveis servicoUtilizado[];

    private enum servicosDisponiveis {
        Estacionamento,
        Manobrista,
        Lavagem,
        Polimento
    }

    public UsoDeVaga(ArrayList<Vaga> vaga, LocalDateTime entrada, LocalDateTime saida, double valorPago,
            boolean usadoManobrista, boolean usadoLavagem, boolean usadoPolimento) {
        this.vaga = vaga;
        this.entrada = entrada;
        this.saida = saida;
        this.valorPago = valorPago;
        this.servicoUtilizado = new servicosDisponiveis[4];
        this.servicoUtilizado[0] = (servicosDisponiveis.Estacionamento);
        for (int i = 0; i < servicoUtilizado.length; i++) {
            if (usadoManobrista)
                this.servicoUtilizado[i] = (servicosDisponiveis.Estacionamento);
            else if (usadoLavagem)
                this.servicoUtilizado[i] = (servicosDisponiveis.Lavagem);
            else if (usadoPolimento)
                this.servicoUtilizado[i] = (servicosDisponiveis.Polimento);
        }
    }

    public void sair() throws Exception {
        this.saida = LocalDateTime.now();
        long minutos = calcularMinutos();
        for (int i = 0; i < servicoUtilizado.length; i++) {
            if(minutos < 60 && servicoUtilizado[i] == servicosDisponiveis.Lavagem)
            throw new Exception("Não é possível retirar seu carro agora, o tempo mínimo de Lavagem é 1 hora");
            else if(minutos < 120 && servicoUtilizado[i] == servicosDisponiveis.Polimento)
            throw new Exception("Não é possível retirar seu carro agora, o tempo mínimo de Polimento são 2 horas");
        }
    }

    private long calcularMinutos(){
        long horas = ChronoUnit.HOURS.between(this.entrada, this.saida);
        long minutos = ChronoUnit.MINUTES.between(this.entrada, this.saida);
        minutos += horas * 60; // Converte horas para minutos
        return minutos;
    }

    public double calcularValor() {
        double valor = 0.0;
        long minutos = calcularMinutos();

        if (minutos <= 15) {
            valor = VALOR_FRACAO;
        } else {
            minutos -= 15; // Subtrai os primeiros 15 minutos
            valor = this.VALOR_FRACAO; // Valor da primeira fração de 15 minutos

            if (minutos > 0) {
                int fracoes = (int) Math.ceil((double) minutos / (FRACAO_USO * 60)); // Calcula as fraçoes restantes
                                                                                     // após 15 minutos
                valor += fracoes * this.VALOR_FRACAO;
            }
            minutos += 15; //Restaura valor padrão de minutos
        }

        if (valor > this.VALOR_MAXIMO) {
            valor = this.VALOR_MAXIMO;
        }

        for (int i = 0; i < servicoUtilizado.length; i++) {
            switch(servicoUtilizado[i])
            {
                case Estacionamento:
                break;
                case Manobrista:
                valor+=5;
                break;
                case Lavagem:
                valor+=20;
                break;
                case Polimento:
                valor+=45;
                break;
            }
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
