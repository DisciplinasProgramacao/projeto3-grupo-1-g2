package modules;

import java.util.List;

public class ObservadoraVeiculo implements IObservadoraVeiculo{
    private List<Estacionamento> observadores;

    @Override
    public void registrar(Estacionamento estacionamento) {
        observadores.add(estacionamento);
    }

    @Override
    public void notifica() {
        for (Estacionamento estacionamento: observadores) {
            estacionamento.update();
        }
    }
}
