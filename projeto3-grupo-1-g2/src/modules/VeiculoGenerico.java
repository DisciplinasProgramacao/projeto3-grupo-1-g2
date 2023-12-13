package modules;

public interface VeiculoGenerico {
    public void estacionar(Vaga vaga);

    public double sair(Integer p_horario) throws Exception;

    public double totalArrecadado();

    public double arrecadadoNoMes(int mes);

    public int totalDeUsos();
}
