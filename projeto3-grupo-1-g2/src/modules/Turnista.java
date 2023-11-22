package modules;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Turnista extends Cliente{
    private String turno;

    private double pagoParte;

    public Turnista(String p_nome, String p_cpf) {
        super(p_nome, p_cpf);
    }

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

    public boolean verificaTurno(){
        String periodo = obterPeriodoDoDia();
        if(turno.equals(periodo)){
            return true;
        }else{
            entradaEstacionamento(LocalDateTime.now());
            return false;
        }
    }

    @Override
    public double arrecadadoTotal() {
       return 200 + pagoParte;
    }

    @Override
    public double arrecadadoNoMes(int p_mes) {
        return 0;
    }

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
