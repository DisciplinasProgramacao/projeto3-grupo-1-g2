package modules;

public class Vaga {
    private int fila;
    private int numero;
    private boolean ocupada;

    public Vaga(int numero) {
        this.numero = numero;
        this.ocupada = false; // Por padrão, a vaga está livre.
    }

    public boolean estacionar() {
        if (!ocupada) {
            ocupada = true;
            return true; // A vaga foi estacionada com sucesso.
        }
        return false; // A vaga já está ocupada.
    }

    public boolean sair() {
        if (ocupada) {
            ocupada = false;
            return true; // O veículo saiu da vaga com sucesso.
        }
        return false; // A vaga já está livre.
    }

    public boolean disponivel() {
        return !ocupada; // Retorna true se a vaga estiver disponível (não ocupada).
    }
    
    public int getNumero() {
        return numero;
    }
}
