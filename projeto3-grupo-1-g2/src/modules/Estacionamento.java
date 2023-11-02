
package modules;

import java.util.ArrayList;

public class Estacionamento {

    private int id;
    private String nome;
    private Cliente[] clientes;
    private int quantFileiras;
    private int vagasPorFileira;
    private ArrayList<Vaga> vagas;

    public Estacionamento() {
    }

    public Estacionamento(String nome, int quantFileiras, int vagasPorFileira) {
        this.nome = nome;
        this.quantFileiras = quantFileiras;
        this.vagasPorFileira = vagasPorFileira;
    }

    public void addVeiculo(Veiculo veiculo, String idCli) {
        for(int i = 0; i < clientes.length ; i++){
           if(clientes[i].equals(id)) {
               clientes[i].addVeiculo(veiculo);
            }
        }
    }

    public void addCliente(Cliente cliente) {
       var tam = clientes.length;
       clientes[tam + 1] = cliente;
    }

    private void gerarVagas(int linha, int numero) {
        Vaga vaga = new Vaga(linha, numero);
    }

    public void estacionar(String placa) {
        for (Cliente t_cliente:clientes) {
            Veiculo veiculo = t_cliente.possuiVeiculo(placa);
            if(veiculo != null){
                for(int i = 0; i < vagas.size(); i++){
                    Vaga vagaUso = vagas.get(i);
                    if(vagaUso.disponivel()){
                        veiculo.estacionar(vagaUso);
                    }
                }
            }
        }
    }

    public boolean sair(String placa) {
        for (Cliente t_cliente:clientes) {
            Veiculo veiculo = t_cliente.possuiVeiculo(placa);
            if(veiculo != null){
                for(int i = 0; i < vagas.size(); i++){
                    Vaga vagaUso = vagas.get(i);
                    if(vagaUso.disponivel()){
                        try{
                            double valor = veiculo.sair();
                            System.out.println("O valor pago foi : " + valor);
                            return true;
                        }catch (Exception e){
                            System.out.println(e);
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente[] getClientes() {
        return clientes;
    }

    public void setClientes(Cliente[] clientes) {
        this.clientes = clientes;
    }

    public Vaga getVagas() {
        return vagas;
    }

    public void setVagas(Vaga vagas) {
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
