package modules;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsoDeVagaTest {

    @Test
    void testCalcularValor() {
        ArrayList<Vaga> vagas = new ArrayList<>();
        vagas.add(new Vaga());
        
        LocalDateTime entrada = LocalDateTime.now().minusMinutes(30);
        LocalDateTime saida = LocalDateTime.now();
        
        UsoDeVaga usoDeVaga = new UsoDeVaga(vagas, entrada, saida, 0, false, false, false);
        double valor = usoDeVaga.calcularValor();
        assertEquals(8.0, valor);
    }

    @Test
    void testSair() {
        ArrayList<Vaga> vagas = new ArrayList<>();
        vagas.add(new Vaga());

        LocalDateTime entrada = LocalDateTime.now().minusMinutes(30);
        LocalDateTime saida = LocalDateTime.now();

        UsoDeVaga usoDeVaga = new UsoDeVaga(vagas, entrada, saida, 0, false, true, false);
        assertThrows(Exception.class, usoDeVaga::sair);
    }

    @Test
    void testSetAndGet() {
        ArrayList<Vaga> vagas = new ArrayList<>();
        vagas.add(new Vaga());

        LocalDateTime entrada = LocalDateTime.now().minusMinutes(30);
        LocalDateTime saida = LocalDateTime.now();

        UsoDeVaga usoDeVaga = new UsoDeVaga(vagas, entrada, saida, 0, false, false, false);

        usoDeVaga.setValorPago(10.0);
        assertEquals(10.0, usoDeVaga.getValorPago());

        LocalDateTime novaEntrada = LocalDateTime.now().minusHours(1);
        usoDeVaga.setEntrada(novaEntrada);
        assertEquals(novaEntrada, usoDeVaga.getEntrada());

        LocalDateTime novaSaida = LocalDateTime.now();
        usoDeVaga.setSaida(novaSaida);
        assertEquals(novaSaida, usoDeVaga.getSaida());

        ArrayList<Vaga> novasVagas = new ArrayList<>();
        usoDeVaga.setVaga(novasVagas);
        assertEquals(novasVagas, usoDeVaga.getVaga());
    }
}
