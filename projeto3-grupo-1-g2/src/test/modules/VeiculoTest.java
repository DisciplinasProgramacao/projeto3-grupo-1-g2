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
        veiculo.estacionar(vaga, true, false, false);

        assertEquals(1, veiculo.getUsos().size());
        assertEquals(vaga, veiculo.getUsos().get(0).getVaga());
    }

    @Test
    void testSair() throws Exception {
        Veiculo veiculo = new Veiculo("ABC1234");
        Vaga vaga = new Vaga(1);
        veiculo.estacionar(vaga, true, false, false);
        veiculo.sair(10);

        assertNotNull(veiculo.getUsos().get(0).getSaida());
        assertFalse(veiculo.getUsos().get(0).getValorPago() > 0);
    }

    @Test
    void testTotalArrecadado() throws Exception {
        Veiculo veiculo = new Veiculo("ABC1234");
        Vaga vaga1 = new Vaga(1);
        Vaga vaga2 = new Vaga(2);

        veiculo.estacionar(vaga1, true, false, false);
        veiculo.sair(12);

        veiculo.estacionar(vaga2, true, false, false);
        veiculo.sair(11);

        double totalEsperado = veiculo.getUsos().get(0).getValorPago() + veiculo.getUsos().get(1).getValorPago();
        assertEquals(totalEsperado, veiculo.totalArrecadado());
    }

    @Test
    void testArrecadadoNoMes() throws Exception {
        Veiculo veiculo = new Veiculo("ABC1234");
        Vaga vaga1 = new Vaga(1);
        Vaga vaga2 = new Vaga(2);

        veiculo.estacionar(vaga1, true, false, false);
        veiculo.sair(23);

        veiculo.estacionar(vaga2, true, false, false);
        veiculo.sair(13);

        int mesAtual = LocalDateTime.now().getMonthValue();
        double totalEsperado = veiculo.getUsos().get(0).getValorPago() + veiculo.getUsos().get(1).getValorPago();
        assertEquals(totalEsperado, veiculo.arrecadadoNoMes(mesAtual));
    }

    @Test
    void testTotalDeUsos() throws Exception {
        Veiculo veiculo = new Veiculo("ABC1234");
        Vaga vaga1 = new Vaga(1);
        Vaga vaga2 = new Vaga(2);

        veiculo.estacionar(vaga1, true, false, false);
        veiculo.sair(21);

        veiculo.estacionar(vaga2, true, false, false);
        veiculo.sair(22);

        assertEquals(2, veiculo.totalDeUsos());
    }
}
