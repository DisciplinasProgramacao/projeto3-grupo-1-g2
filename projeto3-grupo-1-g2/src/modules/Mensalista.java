package modules;

public class Mensalista extends Cliente{


    public Mensalista(String p_nome, String p_cpf) {
        super(p_nome, p_cpf);
    }

    @Override
    public double arrecadadoTotal() {
        return 500.00;
    }

    @Override
    public double arrecadadoNoMes(int p_mes) {
        return 500.00;
    }

}
