
package modules;

import java.util.ArrayList;

public class Estacionamento {

    private int id;
    private String nome;
    private ArrayList<Cliente> clientes;
    private int quantFileiras;
    private int vagasPorFileira;
    private ArrayList<Vaga> vagas;

    public Estacionamento() {
    }

    public Estacionamento(String nome, int quantFileiras, int vagasPorFileira) {
        this.nome = nome;
        this.quantFileiras = quantFileiras;
        this.vagasPorFileira = vagasPorFileira;
        this.clientes = new ArrayList<>();
        this.vagas = new ArrayList<>();
    }

    public void addVeiculo(Veiculo veiculo, String idCli) {
        for(int i = 0; i < clientes.size() ; i++){
           if(clientes.get(i).equals(id)) {
               clientes.get(i).addVeiculo(veiculo);
            }
        }
    }

    public void addCliente(Cliente cliente) {
       clientes.add(cliente);
    }

    private void gerarVagas(int numero) {
        Vaga vaga = new Vaga(numero);
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

    public double totalArrecadado(){
        double valor = 0;
        for (Cliente cliente: clientes) {
            valor += cliente.arrecadadoTotal();
        }
        return valor;
    }

    public double totalArrecadadoNoMes(int mes){
        double valor = 0;
        for (Cliente cliente: clientes) {
            valor += cliente.arrecadadoNoMes(mes);
        }
        return valor;
    }

    public double valoMedioPorUso(){
        double valor = 0;
        int qtd = 0;
        for (Cliente cliente: clientes) {
            valor += cliente.arrecadadoTotal();
            qtd++;
        }
        return valor/qtd;
    }

//    public String top5Clientes(int mes){
//        double valor = 0;
//        ArrayList<Cliente> top5Mes = new ArrayList<>();
//        for (Cliente cliente: clientes) {
//            valor = cliente.arrecadadoNoMes(mes);
//            for (Cliente cli: top5Mes) {
//                if(valor > cli.arrecadadoNoMes(mes)){
//
//                }
//            }
//        }
//    }

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

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setVagas(ArrayList<Vaga> vagas) {
        this.vagas = vagas;
    }

    public ArrayList<Vaga> getVagas() {
        return vagas;
    }

    public void setVagas(Vaga vaga) {
        this.vagas.add(vaga);
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
