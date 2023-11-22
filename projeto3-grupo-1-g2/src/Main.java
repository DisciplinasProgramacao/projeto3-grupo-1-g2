import java.util.Scanner;

import modules.Cliente;
import modules.Estacionamento;
import modules.Veiculo;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int escolha;

        do {
            System.out.println("\n###################################\n");
            System.out.println("GESTÃO DE ESTACIONAMENTO");
            System.out.println("\t 1. Criar estacionamento");
            System.out.println("\t 2. Cadastrar cliente & veículo");
            System.out.println("\t 3. Registrar estacionamento de veículo");
            System.out.println("\t 10. Sair\n");

            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    System.out.println("\n1. Criar estacionamento");
                    Estacionamento avenida = new Estacionamento("Avenida", 2, 3);
                    System.out.println("Estacionamento criado:\nLocal: Avenida / 2 fileiras / 3 vagas por fileira");
                    break;
                case 2:
                    System.out.println("\n2. Cadastrar cliente");
                    System.out.println("Informe nome do cliente: ");
                    String nome = scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("Informe CPF do cliente: ");
                    String cpf = scanner.nextLine();
                    Cliente cliente = new Cliente(nome, cpf);
                    System.out.println("Cliente cadastrado com sucesso!");
                    Estacionamento.addCliente(cliente);
                    System.out.println("Informe placa do veículo: ");
                    String placa = scanner.nextLine();
                    Veiculo veiculo = new Veiculo(placa);
                    cliente.addVeiculo(veiculo);
                    System.out.println("Veículo cadastrado com sucesso!");
                    break;
                case 3:
                    System.out.println("\n3. Registrar estacionamento de veículo");
                    
                    break;
                                
                case 10:
                    System.out.println("Saindo do programa. Adeus!");
                    break;
                default:
                    System.out.println("Opção inválida. Escolha novamente.");
                    break;
            }
        } while (escolha != 3);

        scanner.close();
    }
}
