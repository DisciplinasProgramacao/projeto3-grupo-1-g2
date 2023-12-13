package modules;

public class FabricaVeiculoGenerico implements FabricaVeiculo {

    @Override
    public Veiculo CriarVeiculo(String placa) {
            Veiculo veiculo = new Veiculo(placa);
            return veiculo;
        }
}
