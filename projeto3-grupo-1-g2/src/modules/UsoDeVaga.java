package modules;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class UsoDeVaga {
    private double FRACAO_USO = 0.25;
    private double VALOR_FRACAO = 4.0;
    private double VALOR_MAXIMO = 50.0;

    private Vaga vaga;

    private LocalDateTime entrada;
    private LocalDateTime saida;
    private servicosDisponiveis servicoUtilizado[];

    private enum servicosDisponiveis {
        Estacionamento(0),
        Manobrista(5),
        Lavagem(20),
        Polimento(45);

        private int value;

        servicosDisponiveis(int p_value) {
            this.value = p_value;
        }

        public int getValue() {
            return this.value;
        }

    }

    public UsoDeVaga(Vaga vaga, LocalDateTime entrada, LocalDateTime saida,
            boolean usadoManobrista, boolean usadoLavagem, boolean usadoPolimento) {
        this.vaga = vaga;
        this.entrada = entrada;
        this.saida = saida;
        this.servicoUtilizado = new servicosDisponiveis[4];
        this.servicoUtilizado[0] = (servicosDisponiveis.Estacionamento);
        for (int i = 0; i < servicoUtilizado.length; i++) {
            if(i == 0)
                this.servicoUtilizado[i] = servicosDisponiveis.Estacionamento;
            else if (usadoManobrista)
            {
                this.servicoUtilizado[i] = servicosDisponiveis.Estacionamento;
                usadoManobrista = false;
            }
            else if (usadoLavagem)
            {
                this.servicoUtilizado[i] = servicosDisponiveis.Lavagem;
                usadoLavagem = false;
            }
            else if (usadoPolimento)
            {
                this.servicoUtilizado[i] = servicosDisponiveis.Polimento;
                usadoPolimento = false;
            }
        }
    }

    public double sair(Integer p_horario) throws Exception {
        LocalDateTime v_horario = LocalDateTime.now().plusMinutes(p_horario);
        return sairComParametro(v_horario);
    }

    private double sairComParametro(LocalDateTime p_horario) throws Exception {
        this.saida = p_horario;
        long minutos = calcularMinutos();
        for (int i = 0; i < servicoUtilizado.length; i++) {
            if (minutos < 60 && servicoUtilizado[i] == servicosDisponiveis.Lavagem)
                throw new Exception("A saida da vaga não pode ser efetivada pois ainda não finzalizou a lavagem");
            else if (minutos < 120 && servicoUtilizado[i] == servicosDisponiveis.Polimento)
                throw new Exception("A saida da vaga não pode ser efetivada pois ainda não finzalizou o polimento");
        }
        vaga.sair();
        return calcularValor();
    }

    private long calcularMinutos() {
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
            minutos += 15; // Restaura valor padrão de minutos
        }

        if (valor > this.VALOR_MAXIMO) {
            valor = this.VALOR_MAXIMO;
        }

        // Adicionar valores dos serviços utilizados
        for (int i = 0; i < servicoUtilizado.length; i++) {
            if (servicoUtilizado[i] != null) {
                valor += servicoUtilizado[i].getValue();
            }
        }

        return valor;
    }

    public double getValorPago() {
        return calcularValor();
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

    public Vaga getVaga() {
        return this.vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }
}
