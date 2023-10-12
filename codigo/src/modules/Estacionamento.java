
package modules;
public class Estacionamento {

    private String nome;
    private Cliente[] id;
    private Vaga[] vagas;
    private int quantFileiras;
    private int vagasPorFileira;

    public Estacionamento() {
    }

    public Estacionamento(String nome, int quantFileiras, int vagasPorFileira) {
        this.nome = nome;
        this.quantFileiras = quantFileiras;
        this.vagasPorFileira = vagasPorFileira;
    }

    public void addVeiculo(Veiculo veiculo, String idCli) {
        for(int i = 0; i < id.length ; i++){
           if(id[i].equals(id)) {
               id[i].addVeiculo(veiculo);
            }
        }
    }

    public void addCliente(Cliente cliente) {

    }

    private void gerarVagas() {

    }

    public void estacionar(String placa) {

    }

    public double sair(String placa) {

    }

    public double totalArrecadado() {

    }

    public double arrecadacaoNoMes(int mes) {

    }

    public double valorMedioPorUso() {

    }

    public String top5Clientes(int mes) {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cliente[] getId() {
        return id;
    }

    public void setId(Cliente[] id) {
        this.id = id;
    }

    public Vaga[] getVagas() {
        return vagas;
    }

    public void setVagas(Vaga[] vagas) {
        this.vagas = vagas;
    }

    public int getQuantFileiras() {
        return quantFileiras;
    }

    public void setQuantFileiras(int quantFileiras) {
        this.quantFileiras = quantFileiras;
    }

    public int getVagasPorFileira() {
        return vagasPorFileira;
    }

    public void setVagasPorFileira(int vagasPorFileira) {
        this.vagasPorFileira = vagasPorFileira;
    }
}
