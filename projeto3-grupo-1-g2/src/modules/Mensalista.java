package modules;

public class Mensalista extends Cliente{



    public Mensalista(String cliente1, String s) {
        super();
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
