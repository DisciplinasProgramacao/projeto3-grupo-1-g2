package test.modules;
import modules.Cliente;
import modules.Estacionamento;
import modules.Vaga;
import modules.Veiculo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class EstacionamentoTest {

    private Estacionamento estacionamento;

    @Before
    public void setUp() {
        // Inicializa um estacionamento antes de cada teste
        estacionamento = new Estacionamento("Estacionamento Teste", 5, 10);
    }

    @Test
    public void addCliente() {
        Cliente cliente = new Cliente("Cliente1", "123456");
        estacionamento.addCliente(cliente);

        assertEquals(cliente, estacionamento.getClientes()[0]);
    }

    @Test
    public void addVeiculo() {
        Cliente cliente = new Cliente("Cliente2", "789012");
        estacionamento.addCliente(cliente);

        Veiculo veiculo = new Veiculo("Placa123");
        estacionamento.addVeiculo(veiculo, "789012");

        assertEquals(veiculo, cliente.possuiVeiculo(veiculo.getPlaca()));
    }

    @Test
    public void estacionar() {
        Vaga vaga = new Vaga(1, 1);
        estacionamento.setVagas(vaga);
        estacionamento.estacionar("Placa123");

        assertTrue(vaga.estacionar());
    }

    @Test
    public void sair() {
        Vaga vaga = new Vaga(1, 1);
        estacionamento.setVagas(vaga);
        estacionamento.estacionar("Placa123");

        boolean saiu = estacionamento.sair("Placa123");
        assertTrue(saiu);
        assertTrue(vaga.disponivel());
    }
}