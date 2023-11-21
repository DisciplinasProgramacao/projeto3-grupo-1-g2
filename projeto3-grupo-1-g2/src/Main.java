import modules.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {

        Estacionamento estacionamento = new Estacionamento("Estacionamento1", 10,10);

        ArrayList<Veiculo> veiculos = new ArrayList<>();
        Veiculo veiculo1 = new Veiculo("HNU-0000");
        Veiculo veiculo2 = new Veiculo("HNU-1111");
        Veiculo veiculo3 = new Veiculo("HNU-2222");
        Veiculo veiculo4 = new Veiculo("HNU-3333");
        Veiculo veiculo5 = new Veiculo("HNU-4444");
        Veiculo veiculo6 = new Veiculo("HNU-5555");
        veiculos.add(veiculo1);
        veiculos.add(veiculo2);
        veiculos.add(veiculo3);
        veiculos.add(veiculo4);
        veiculos.add(veiculo5);
        veiculos.add(veiculo6);
        ArrayList<Cliente> clientes = new ArrayList<>();
        Cliente cliente1 = new Mensalista("cliente1", "123.456.789-90");
        cliente1.addVeiculo(veiculo1);
        Cliente cliente2 = new Turnista("cliente2", "123.456.789-80");
        cliente2.addVeiculo(veiculo2);
        Cliente cliente3 = new Horista("cliente3", "123.456.789-70");
        cliente3.addVeiculo(veiculo3);

        Cliente cliente4 = new Horista("cliente4", "123.456.789-70");
        cliente4.addVeiculo(veiculo4);

        Cliente cliente5 = new Mensalista("cliente5", "123.456.789-70");
        cliente5.addVeiculo(veiculo5);

        Cliente cliente6 = new Turnista("cliente6", "123.456.789-70");
        cliente6.addVeiculo(veiculo6);

        clientes.add(cliente1);
        clientes.add(cliente2);
        clientes.add(cliente3);
        clientes.add(cliente4);
        clientes.add(cliente5);
        clientes.add(cliente6);

        ArrayList<Vaga> vagas = new ArrayList<>();
        Vaga vaga1 = new Vaga(1);
        Vaga vaga2= new Vaga(2);
        Vaga vaga3 = new Vaga(3);
        vagas.add(vaga1);
        vagas.add(vaga2);
        vagas.add(vaga3);

        estacionamento.addCliente(cliente1);
        estacionamento.addCliente(cliente2);
        estacionamento.addCliente(cliente3);
        estacionamento.addCliente(cliente4);
        estacionamento.addCliente(cliente5);
        estacionamento.addCliente(cliente6);


        estacionar(estacionamento, clientes, veiculos);
        top5(estacionamento);
        relatorio(estacionamento);

    }

    public static void estacionar(Estacionamento estacionamento, ArrayList<Cliente> clientes, ArrayList<Veiculo> veiculos){
        int count = 0;
        for (Cliente cliente: clientes) {
            Veiculo v = cliente.possuiVeiculo(veiculos.get(count).getPlaca());
            estacionamento.estacionar(v.getPlaca());
            count ++;
        }
    }

    public static void top5(Estacionamento estacionamento){
        estacionamento.top5Clientes();
    }


    public static void relatorio(Estacionamento estacionamento){
        System.out.println("O valor total arrecadado foi :" + estacionamento.totalArrecadado());
        System.out.println("O valor medio por uso foi :" + estacionamento.valoMedioPorUso());
    }



}
