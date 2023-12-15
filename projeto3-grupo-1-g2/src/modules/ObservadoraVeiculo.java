package modules;

import java.util.ArrayList;
import java.util.List;

public class ObservadoraVeiculo implements IObservadoraVeiculo{
    private List<Estacionamento> observadores;

    @Override
    public void registrar(Estacionamento estacionamento) {
        if(observadores == null){
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

    public List<Estacionamento> getObservadores() {
        return observadores;
    }

    public void setObservadores(List<Estacionamento> observadores) {
        this.observadores = observadores;
    }
}
