package modules;

public class Vaga {
    private int numero;
    private boolean ocupada;

    public Vaga(int numero) {
        this.numero = numero;
        this.ocupada = false; 
    }

    public boolean estacionar() {
        if (!ocupada) {
            ocupada = true;
            return true;
        }
        return false; 
    }

    public boolean sair() {
        if (ocupada) {
            ocupada = false;
            return true; 
        }
        return false;
    }

    public boolean disponivel() {
        return !ocupada;
    }
    
    public int getNumero() {
        return numero;
    }
}
