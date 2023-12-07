package modules;

import java.time.LocalDateTime;

public class FabricaUsoVagasGenerico implements FabricaUsoVagas {
    @Override
    public UsoDeVaga CriaUsoVaga(Vaga vaga, LocalDateTime entrada, LocalDateTime saida, boolean usadoManobrista, boolean usadoLavagem, boolean usadoPolimento) {
        UsoDeVaga usoDeVaga = new UsoDeVaga(vaga,entrada,saida,usadoManobrista,usadoLavagem,usadoPolimento);
        return usoDeVaga;
    }
}
