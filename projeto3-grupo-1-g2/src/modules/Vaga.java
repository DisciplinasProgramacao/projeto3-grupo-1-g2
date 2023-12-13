package modules;

/**
 * Representa uma vaga de estacionamento em um estacionamento.
 * Cada vaga possui um número único e pode estar ocupada ou disponível.
 */
public class Vaga {
    //#region atributos
    private int numero;
    private boolean ocupada;
    //#endregion

    //#region Construtores

    /**
     * Construtor da classe Vaga.
     *
     * @param numero O número único associado à vaga.
     */
    public Vaga(int numero) {
        this.numero = numero;
        this.ocupada = false; 
    }

    /**
     * Estaciona um veículo na vaga, marcando-a como ocupada.
     *
     * @return TRUE se o veículo foi estacionado com sucesso, FALSE se a vaga já estiver ocupada.
     */
    public boolean estacionar() {
        if (!ocupada) {
            ocupada = true;
            return true;
        }
        return false; 
    }

    /**
     * Libera a vaga, marcando-a como disponível.
     *
     * @return TRUE se a vaga foi liberada com sucesso, FALSE se a vaga já estiver disponível.
     */
    public boolean sair() {
        if (ocupada) {
            ocupada = false;
            return true; 
        }
        return false;
    }

    /**
     * Verifica se a vaga está disponível.
     *
     * @return TRUE se a vaga está disponível, FALSE se a vaga está ocupada.
     */
    public boolean disponivel() {
        return !ocupada;
    }
    
    public int getNumero() {
        return numero;
    }
}
