package modules;

import java.time.LocalDateTime;

public interface FabricaUsoVagas {

    public UsoDeVaga CriaUsoVaga(Vaga vaga, LocalDateTime entrada, LocalDateTime saida, boolean usadoManobrista, boolean usadoLavagem, boolean usadoPolimento);
}
