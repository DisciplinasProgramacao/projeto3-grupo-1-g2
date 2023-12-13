package test.modules;

import java.time.LocalDateTime;
import modules.UsoDeVaga;
import modules.Vaga;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsoDeVagaTest {

    @Test
    void testCalcularValor() {
        Vaga vaga = new Vaga(1);

        LocalDateTime entrada = LocalDateTime.now().minusMinutes(30);
        LocalDateTime saida = LocalDateTime.now();

        UsoDeVaga usoDeVaga = new UsoDeVaga(vaga, entrada, saida, false, false, false);
        double valor = usoDeVaga.calcularValor();
        assertEquals(8.0, valor);
    }

    @Test
    void testSair() {
        Vaga vaga = new Vaga(5);

        LocalDateTime entrada = LocalDateTime.now().minusMinutes(30);
        LocalDateTime saida = LocalDateTime.now();

        UsoDeVaga usoDeVaga = new UsoDeVaga(vaga, entrada, saida, false, true, false);
        double valor = 0;
        try {
            valor = usoDeVaga.sair(120);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals(70.0, valor);
    }

    @Test
    void testSetAndGet() {
        Vaga vaga = new Vaga(1);

        LocalDateTime entrada = LocalDateTime.now().minusMinutes(30);
        LocalDateTime saida = LocalDateTime.now();

        UsoDeVaga usoDeVaga = new UsoDeVaga(vaga, entrada, saida, false, false, false);

        assertEquals(8.0, usoDeVaga.getValorPago());

        LocalDateTime novaEntrada = LocalDateTime.now().minusHours(1);
        usoDeVaga.setEntrada(novaEntrada);
        assertEquals(novaEntrada, usoDeVaga.getEntrada());

        LocalDateTime novaSaida = LocalDateTime.now();
        usoDeVaga.setSaida(novaSaida);
        assertEquals(novaSaida, usoDeVaga.getSaida());

        Vaga novasVaga = new Vaga(1);
        usoDeVaga.setVaga(novasVaga);
        assertEquals(novasVaga, usoDeVaga.getVaga());
    }
}
