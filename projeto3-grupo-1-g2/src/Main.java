import modules.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {

        Estacionamento estacionamento = new Estacionamento("Estacionamento1", 10,10);

        ArrayList<Veiculo> veiculos = new ArrayList<>();
        Veiculo veiculo1 = new Veiculo("HNU-0000");
        Veiculo veiculo2 = new Veiculo("HNU-1111");
        Veiculo veiculo3 = new Veiculo("HNU-2222");
        Veiculo veiculo4 = new Veiculo("HNU-3333");
        veiculos.add(veiculo1);
        veiculos.add(veiculo2);
        veiculos.add(veiculo3);
        veiculos.add(veiculo4);
        ArrayList<Cliente> clientes = new ArrayList<>();
        Cliente cliente1 = new Cliente("cliente1", "123.456.789-90");
        cliente1.addVeiculo(veiculo1);
        Cliente cliente2 = new Cliente("cliente2", "123.456.789-80");
        cliente2.addVeiculo(veiculo2);
        Cliente cliente3 = new Cliente("cliente3", "123.456.789-70");
        cliente3.addVeiculo(veiculo3);
        cliente3.addVeiculo(veiculo4);
        clientes.add(cliente1);
        clientes.add(cliente2);
        clientes.add(cliente3);
        ArrayList<Vaga> vagas = new ArrayList<>();
        Vaga vaga1 = new Vaga(1);
        Vaga vaga2= new Vaga(2);
        Vaga vaga3 = new Vaga(3);
        Vaga vaga4 = new Vaga(4);
        vagas.add(vaga1);
        vagas.add(vaga2);
        vagas.add(vaga3);
        vagas.add(vaga4);

        estacionamento.addCliente(cliente1);
        estacionamento.addCliente(cliente2);
        estacionamento.addCliente(cliente3);

        estacionamento.setVagas(vagas);

        estacionar(estacionamento, clientes, veiculos);
        sair(estacionamento, clientes, veiculos);
        System.out.println("\n");
        relatorio(estacionamento);
        

        System.out.println("\n");
        System.out.println(veiculo1.gerarRelatorioVagas());
        System.out.println(veiculo2.gerarRelatorioVagas());
        System.out.println(veiculo3.gerarRelatorioVagas());


    }

    public static void estacionar(Estacionamento estacionamento, ArrayList<Cliente> clientes, ArrayList<Veiculo> veiculos){
        int count = 0;
        for (Cliente cliente: clientes) {
            Veiculo v = cliente.possuiVeiculo(veiculos.get(count).getPlaca());
            estacionamento.estacionar(v.getPlaca());
            count ++;
        }
    }

    public static void sair(Estacionamento estacionamento, ArrayList<Cliente> clientes, ArrayList<Veiculo> veiculos){
        int count = 0;
        for (Cliente cliente: clientes) {
            Veiculo v = cliente.possuiVeiculo(veiculos.get(count).getPlaca());
            estacionamento.sair(v.getPlaca());
            count ++;
        }
    }


    public static void relatorio(Estacionamento estacionamento){
        System.out.println("O valor total arrecadado foi :" + estacionamento.totalArrecadado());
        System.out.println("O valor medio por uso foi :" + estacionamento.valoMedioPorUso());
    }

    

}
