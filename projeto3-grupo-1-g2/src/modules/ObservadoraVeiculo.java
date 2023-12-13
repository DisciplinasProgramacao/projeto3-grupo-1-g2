package modules;

import java.util.ArrayList;
import java.util.List;

public class ObservadoraVeiculo implements IObservadoraVeiculo {
    private Veiculo veiculo;
    private List<Estacionamento> observadores;


    @Override
    public void registrar(Estacionamento estacionamento) {
        if(this.observadores == null){
            observadores = new ArrayList<Estacionamento>();
        }
        observadores.add(estacionamento);
    }

    @Override
    public void notifica() {
        for (Estacionamento estacionamento: observadores) {
            estacionamento.update();
        }
    }
}
