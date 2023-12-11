package modules;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Representa um cliente do tipo Turnista, que é uma subclasse de Cliente.
 * Clientes Turnistas têm métodos específicos para cálculos de arrecadação e controle de turno.
 *
 */
public class Turnista extends Cliente{
    //#region atributos
    private String turno;
    private double pagoParte;
    //#endregion

    //#region Construtores

    /**
     * Construtor da classe Turnista.
     *
     * @param p_nome O nome do cliente Turnista.
     * @param p_cpf O CPF do cliente Turnista.
     */
    public Turnista(String p_nome, String p_cpf) {
        super(p_nome, p_cpf);
    }

    /**
     * Obtém o período do dia atual com base na hora do sistema.
     *
     * @return O período do dia atual (manha, tarde, noite).
     */
    public static String obterPeriodoDoDia() {
        LocalDateTime dateTime = LocalDateTime.now();
        int hora = dateTime.getHour();
        if (hora >= 6 && hora < 12) {
            return "manha";
        } else if (hora >= 12 && hora < 18) {
            return "tarde";
        } else {
            return "noite";
        }
    }

    /**
     * Verifica se o turno do cliente Turnista coincide com o período do dia atual.
     * Se não coincidir, registra a entrada do cliente no estacionamento.
     *
     * @return TRUE se o turno coincide, FALSE se o cliente entra em um turno diferente.
     */
    public boolean verificaTurno(){
        String periodo = obterPeriodoDoDia();
        if(turno.equals(periodo)){
            return true;
        }else{
            entradaEstacionamento(LocalDateTime.now());
            return false;
        }
    }

    /**
     * Calcula o valor total arrecadado pelos veículos do cliente Turnista.
     *
     * @return O valor total arrecadado pelos veículos do cliente Turnista.
     */
    @Override
    public double arrecadadoTotal() {
       return 200 + pagoParte;
    }

    /**
     * Calcula o valor arrecadado pelos veículos do cliente Turnista no mês especificado.
     *
     * @param p_mes O mês para o qual se deseja calcular a arrecadação.
     * @return O valor arrecadado pelos veículos do cliente Turnista no mês.
     */
    @Override
    public double arrecadadoNoMes(int p_mes) {
        return 200 + pagoParte;
    }

    /**
     * Registra a entrada do cliente Turnista no estacionamento e calcula o valor a ser pago.
     *
     * @param entrada A data e hora de entrada no estacionamento.
     * @return O valor a ser pago pelo cliente Turnista.
     */
    public double entradaEstacionamento(LocalDateTime entrada){
        LocalDateTime saida = LocalDateTime.now().plus(30, ChronoUnit.MINUTES);
        boolean isTurno = verificaTurno();
        if(isTurno){
            return 0;
        }else {
            Vaga vaga = new Vaga(10);
            UsoDeVaga usoDeVaga = new UsoDeVaga(vaga, entrada, saida, false, false, false);
            pagoParte = usoDeVaga.calcularValor();
            return pagoParte;
        }
    }

}
