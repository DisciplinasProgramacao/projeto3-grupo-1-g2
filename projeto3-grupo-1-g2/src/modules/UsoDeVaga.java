package modules;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Representa o uso de uma vaga de estacionamento por um veículo, incluindo os serviços utilizados.
 * Calcula o valor a ser pago com base no tempo de uso, serviços utilizados e tarifas.
 */
public class UsoDeVaga {
    //#region atributos
    private double FRACAO_USO = 0.25;
    private double VALOR_FRACAO = 4.0;
    private double VALOR_MAXIMO = 50.0;
    private Vaga vaga;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private servicosDisponiveis servicoUtilizado[];
    private Double totalPago;
    //#endregion

    /**
     * Enumeração dos serviços disponíveis durante o uso da vaga.
     */
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

    //#region Construtores

    /**
     * Construtor da classe UsoDeVaga.
     *
     * @param vaga A vaga a ser utilizada.
     * @param entrada A data e hora de entrada na vaga.
     * @param saida A data e hora de saída da vaga.
     * @param usadoManobrista Indica se foi utilizado o serviço de manobrista.
     * @param usadoLavagem Indica se foi utilizado o serviço de lavagem.
     * @param usadoPolimento Indica se foi utilizado o serviço de polimento.
     */
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

    /**
     * Registra a saída do veículo da vaga com base no horário fornecido e calcula o valor a ser pago.
     *
     * @param p_horario O horário de saída do veículo.
     * @return O valor a ser pago pelo uso da vaga.
     * @throws Exception Exceção lançada se a lavagem ou polimento ainda não estiverem finalizados.
     */
    public Double sair(Integer p_horario) throws Exception {
        LocalDateTime v_horario = LocalDateTime.now().plusMinutes(p_horario);
        return sairComParametro(v_horario);
    }

    /**
     * Registra a saída do veículo da vaga com base no horário fornecido e calcula o valor a ser pago.
     *
     * @param p_horario O horário de saída do veículo.
     * @return O valor a ser pago pelo uso da vaga.
     * @throws Exception Exceção lançada se a lavagem ou polimento ainda não estiverem finalizados.
     */
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

    /**
     * Calcula o número total de minutos entre a entrada e saída do veículo na vaga.
     *
     * @return O número total de minutos de ocupação da vaga.
     */
    private long calcularMinutos() {
        long horas = ChronoUnit.HOURS.between(this.entrada, this.saida);
        long minutos = ChronoUnit.MINUTES.between(this.entrada, this.saida);
        minutos += horas * 60;
        return minutos;
    }

    /**
     * Calcula o valor a ser pago pelo uso da vaga com base no tempo de uso, serviços utilizados e tarifas.
     *
     * @return O valor a ser pago pelo uso da vaga.
     */
    public double calcularValor() {
        double valor = 0.0;
        long minutos = calcularMinutos();

        if (minutos <= 15) {
            valor = VALOR_FRACAO;
        } else {
            minutos -= 15;
            valor = this.VALOR_FRACAO;

            if (minutos > 0) {
                int fracoes = (int) Math.ceil((double) minutos / (FRACAO_USO * 60));

                valor += fracoes * this.VALOR_FRACAO;
            }
            minutos += 15;
        }

        if (valor > this.VALOR_MAXIMO) {
            valor = this.VALOR_MAXIMO;
        }

        for (int i = 0; i < servicoUtilizado.length; i++) {
            if (servicoUtilizado[i] != null) {
                valor += servicoUtilizado[i].getValue();
            }
        }

        totalPago = valor;
        return valor;
    }

    public double getValorPago() {
        return totalPago;
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
