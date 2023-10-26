package modules;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class ClienteTest {

    @Test
    void testAddVeiculo() {
        Cliente cliente = new Cliente("João", "123456789");
        Veiculo veiculo = new Veiculo("ABC1234");
        cliente.addVeiculo(veiculo);
    }

    @Test
    void testPossuiVeiculo() {
        Cliente cliente = new Cliente("João", "123456789");
        Veiculo veiculo = new Veiculo("ABC1234");
        cliente.addVeiculo(veiculo);
        assertEquals(veiculo, cliente.possuiVeiculo("ABC1234"));
    }

    @Test
    void testTotalDeUsos() {
        Cliente cliente = new Cliente("João", "123456789");
        Veiculo veiculo = new Veiculo("ABC1234");
        cliente.addVeiculo(veiculo);
        assertEquals(0, cliente.totalDeUsos());
    }

    @Test
    void testArrecadadoPorVeiculo() {
        Cliente cliente = new Cliente("João", "123456789");
        Veiculo veiculo = new Veiculo("ABC1234");
        cliente.addVeiculo(veiculo);
        assertEquals(0, cliente.arrecadadoPorVeiculo("ABC1234"));
    }

    @Test
    void testArrecadadoTotal() {
        Cliente cliente = new Cliente("João", "123456789");
        Veiculo veiculo = new Veiculo("ABC1234");
        cliente.addVeiculo(veiculo);
        assertEquals(0, cliente.arrecadadoTotal());
    }

    @Test
    void testArrecadadoNoMes() {
        Cliente cliente = new Cliente("João", "123456789");
        Veiculo veiculo = new Veiculo("ABC1234");
        cliente.addVeiculo(veiculo);
        assertEquals(0, cliente.arrecadadoNoMes(1));
    }
}