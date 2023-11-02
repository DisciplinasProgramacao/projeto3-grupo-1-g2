package test.modules;

import java.time.LocalDateTime;
import java.util.ArrayList;

import modules.UsoDeVaga;
import modules.Vaga;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsoDeVagaTest {

    @Test
    void testCalcularValor() {
        Vaga vaga = new Vaga(1, 1);

        LocalDateTime entrada = LocalDateTime.now().minusMinutes(30);
        LocalDateTime saida = LocalDateTime.now();

        UsoDeVaga usoDeVaga = new UsoDeVaga(vaga, entrada, saida, 0, false, false, false);
        double valor = usoDeVaga.calcularValor();
        assertEquals(8.0, valor);
    }

    @Test
    void testSair() {
        Vaga vaga = new Vaga(5, 8);

        LocalDateTime entrada = LocalDateTime.now().minusMinutes(30);
        LocalDateTime saida = LocalDateTime.now();

        UsoDeVaga usoDeVaga = new UsoDeVaga(vaga, entrada, saida, 0, false, true, false);
        assertThrows(Exception.class, usoDeVaga::sair);
    }

    @Test
    void testSetAndGet() {
        Vaga vaga = new Vaga(1, 1);

        LocalDateTime entrada = LocalDateTime.now().minusMinutes(30);
        LocalDateTime saida = LocalDateTime.now();

        UsoDeVaga usoDeVaga = new UsoDeVaga(vaga, entrada, saida, 0, false, false, false);

        usoDeVaga.setValorPago(10.0);
        assertEquals(10.0, usoDeVaga.getValorPago());

        LocalDateTime novaEntrada = LocalDateTime.now().minusHours(1);
        usoDeVaga.setEntrada(novaEntrada);
        assertEquals(novaEntrada, usoDeVaga.getEntrada());

        LocalDateTime novaSaida = LocalDateTime.now();
        usoDeVaga.setSaida(novaSaida);
        assertEquals(novaSaida, usoDeVaga.getSaida());

        Vaga novasVaga = new Vaga(1, 1);
        usoDeVaga.setVaga(novasVaga);
        assertEquals(novasVaga, usoDeVaga.getVaga());
    }
}
