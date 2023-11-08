package test.modules;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import modules.Vaga;
import modules.Veiculo;

class VeiculoTest {

    @Test
    void testCriarVeiculoEObterPlaca() {
        Veiculo veiculo = new Veiculo("ABC1234");
        assertEquals("ABC1234", veiculo.getPlaca());
    }

    @Test
    void testEstacionar() {
        Veiculo veiculo = new Veiculo("ABC1234");
        Vaga vaga = new Vaga(1);
        veiculo.estacionar(vaga);

        assertEquals(1, veiculo.getUsos().size());
        assertEquals(vaga, veiculo.getUsos().get(0).getVaga());
    }

    @Test
    void testSair() {
        Veiculo veiculo = new Veiculo("ABC1234");
        Vaga vaga = new Vaga(1);
        veiculo.estacionar(vaga);
        veiculo.sair();

        assertNotNull(veiculo.getUsos().get(0).getSaida());
        assertFalse(veiculo.getUsos().get(0).getValorPago() > 0);
    }

    @Test
    void testTotalArrecadado() {
        Veiculo veiculo = new Veiculo("ABC1234");
        Vaga vaga1 = new Vaga(1);
        Vaga vaga2 = new Vaga(2);

        veiculo.estacionar(vaga1);
        veiculo.sair();

        veiculo.estacionar(vaga2);
        veiculo.sair();

        double totalEsperado = veiculo.getUsos().get(0).getValorPago() + veiculo.getUsos().get(1).getValorPago();
        assertEquals(totalEsperado, veiculo.totalArrecadado());
    }

    @Test
    void testArrecadadoNoMes() {
        Veiculo veiculo = new Veiculo("ABC1234");
        Vaga vaga1 = new Vaga(1);
        Vaga vaga2 = new Vaga(2);

        veiculo.estacionar(vaga1);
        veiculo.sair();

        veiculo.estacionar(vaga2);
        veiculo.sair();

        int mesAtual = LocalDateTime.now().getMonthValue();
        double totalEsperado = veiculo.getUsos().get(0).getValorPago() + veiculo.getUsos().get(1).getValorPago();
        assertEquals(totalEsperado, veiculo.arrecadadoNoMes(mesAtual));
    }

    @Test
    void testTotalDeUsos() {
        Veiculo veiculo = new Veiculo("ABC1234");
        Vaga vaga1 = new Vaga(1);
        Vaga vaga2 = new Vaga(2);

        veiculo.estacionar(vaga1);
        veiculo.sair();

        veiculo.estacionar(vaga2);
        veiculo.sair();

        assertEquals(2, veiculo.totalDeUsos());
    }
}
