package modules;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

public interface VeiculoGenerico {
    public void estacionar(Vaga vaga);

    public double sair(Integer p_horario) throws Exception;

    public double totalArrecadado();

    public double arrecadadoNoMes(int mes);

    public int totalDeUsos();
}
