import modules.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        ArrayList<UsoDeVaga> usosDeVaga = new ArrayList<>();

        // Dados iniciais
        LocalDateTime entrada = LocalDateTime.now().minusMinutes(30);
        LocalDateTime saida = LocalDateTime.now();
        ArrayList<Vaga> vagas = new ArrayList<>();
        Cliente cliente = new Cliente("João", "123456789");
        Estacionamento estacionamento = new Estacionamento("Estacionamento 1", 10, 10);
        UsoDeVaga usoDeVaga;
        Vaga vagaCriar;
        Veiculo veiculo = new Veiculo("ABC1234");

        for (int i = 0; i < 10; i++) {
            vagaCriar = new Vaga(i, i + 1);
            vagas.add(vagaCriar);
            usoDeVaga = new UsoDeVaga(vagas, entrada, saida, 0, false, false, false);
            usosDeVaga.add(usoDeVaga);
        }

        // Exibir os usos de vaga criados
        for (int i = usosDeVaga.size() - 1; i >= 0; i--) {
            System.out.println("A vaga teve entrada " + usosDeVaga.get(i).getEntrada() + "E saida em " + usosDeVaga.get(i).getSaida());
        }
    }


}
