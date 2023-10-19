
package modules;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

    private int id;
    private String nome;
    private Cliente[] clientes;
    private Vaga vagas;
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
        vagas.estacionar();
    }

    public boolean sair(String placa) {
        vagas.sair();
        return vagas.disponivel();
    }

//    public double totalArrecadado() {
//
//    }
//
//    public double arrecadacaoNoMes(int mes) {
//
//    }
//
//    public double valorMedioPorUso() {
//
//    }
//
//    public String top5Clientes(int mes) {
//
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
