package modules;

import java.util.List;

public class ObservadoraCliente implements IObservadoraCliente {

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
