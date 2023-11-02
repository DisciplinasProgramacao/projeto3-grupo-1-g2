package test.modules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import modules.Cliente;
import modules.Veiculo;

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
        assertEquals(0, cliente.arrecadadoPorVeiculo("ABC1234"), 0);
    }

    @Test
    void testArrecadadoTotal() {
        Cliente cliente = new Cliente("João", "123456789");
        Veiculo veiculo = new Veiculo("ABC1234");
        cliente.addVeiculo(veiculo);
        assertEquals(0, cliente.arrecadadoTotal(), 0);
    }

    @Test
    void testArrecadadoNoMes() {
        Cliente cliente = new Cliente("João", "123456789");
        Veiculo veiculo = new Veiculo("ABC1234");
        cliente.addVeiculo(veiculo);
        assertEquals(0, cliente.arrecadadoNoMes(1), 0);
    }
}