package test.modules;
import modules.Cliente;
import modules.Estacionamento;
import modules.Horista;
import modules.Veiculo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EstacionamentoTest {

    private Estacionamento estacionamento;

    @Before
    public void setUp() {
        estacionamento = new Estacionamento("Estacionamento Teste", 5, 10);
    }

    @Test
    public void testAddCliente() {
        Cliente cliente = new Horista("Cliente1", "123456");
        estacionamento.addCliente(cliente);

        assertTrue(estacionamento.clienteExiste(cliente));
    }

    @Test
    public void testEstacionar() {
        Cliente cliente = new Horista("Cliente2", "789012");
        Veiculo veiculo = new Veiculo("Placa123");
        cliente.addVeiculo(veiculo);
        estacionamento.addCliente(cliente);
        boolean estacionado = estacionamento.estacionar(veiculo);
        assertTrue(true);
    }

    @Test
    public void testsair() {
        Cliente cliente = new Horista("Cliente3", "345678");
        Veiculo veiculo = new Veiculo("Placa456");
        cliente.addVeiculo(veiculo);
        estacionamento.addCliente(cliente);
        estacionamento.estacionar(veiculo);


        boolean saiuComSucesso = estacionamento.sair(veiculo);

        assertTrue(saiuComSucesso);
    }




    @Test
    public void testTotalArrecadado() {
        Cliente cliente1 = new Horista("João", "123456789");
        cliente1.arrecadadoTotal();

        Cliente cliente2 = new Horista("Maria", "123456759");
        cliente2.arrecadadoTotal();

        estacionamento.addCliente(cliente1);
        estacionamento.addCliente(cliente2);

        double resultado = estacionamento.totalArrecadado();

        assertEquals(0, resultado, 0.001);
    }

    @Test
    public void testTotalArrecadadoNoMes() {
        Cliente cliente1 = new Horista("João", "123456789");
        Cliente cliente2 = new Horista("Maria", "123456759");
        estacionamento.addCliente(cliente1);
        estacionamento.addCliente(cliente2);

        double resultado = estacionamento.totalArrecadadoNoMes(2);

        assertEquals(0, resultado, 0.001);
    }

    @Test
    public void testValorMedioPorUso() {
        Cliente cliente1 = new Horista("João", "123456789");
        Cliente cliente2 = new Horista("Maria", "123456759");
        estacionamento.addCliente(cliente1);
        estacionamento.addCliente(cliente2);

        double resultado = estacionamento.valorMedioPorUso();

        assertEquals(0, resultado, 0.001);
    }

    @Test
    public void testAddClienteToEstacionamento() {
        Cliente cliente = new Horista("Cliente4", "987654");
        List<Veiculo> veiculosCliente = new ArrayList<>();
        veiculosCliente.add(new Veiculo("Placa789"));

        assertTrue(estacionamento.addClienteToEstacionamento(cliente));
        assertTrue(estacionamento.clienteExiste(cliente));
    }

    @Test
    public void testGetClientesVeiculos() {
        Cliente cliente1 = new Horista("João", "123456789");
        Cliente cliente2 = new Horista("Maria", "123456759");
        estacionamento.addCliente(cliente1);
        estacionamento.addCliente(cliente2);

        assertEquals(2, estacionamento.getClientesVeiculos().size());
    }
}
