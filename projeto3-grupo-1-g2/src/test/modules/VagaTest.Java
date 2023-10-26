package test.modules;

import modules.Vaga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VagaTest {

    private Vaga vaga;

    @BeforeEach
    public void setUp() {
        vaga = new Vaga(1, 10);
    }

    @Test
    public void testEstaDisponivelInicial() {
        assertTrue(vaga.disponivel());
    }

    @Test
    public void testEstacionarVaga() {
        assertTrue(vaga.estacionar());
        assertFalse(vaga.disponivel());
    }

    @Test
    public void testEstacionarVagaRepetidamente() {
        assertTrue(vaga.estacionar());
        assertFalse(vaga.estacionar());
    }

    @Test
    public void testSairVaga() {
        assertTrue(vaga.estacionar());
        assertTrue(vaga.sair());
        assertTrue(vaga.disponivel());
    }
}
