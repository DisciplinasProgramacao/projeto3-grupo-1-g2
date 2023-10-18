package modules;

public class Vaga {

	private String id;
	private boolean disponivel;

	public Vaga(int fila, int numero) {
		
	}

	public boolean estacionar(String placa) {
		disponivel = false;
		return  disponivel;
	}

	public boolean sair(String placa) {
		disponivel = true;
		return  disponivel;
	}

	public boolean disponivel() {
		return disponivel;
	}


}
