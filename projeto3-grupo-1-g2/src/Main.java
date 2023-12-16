import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.junit.platform.commons.function.Try;

import modules.*;
import utils.FileTool;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int escolha;

    static List<Estacionamento> listaEstacionamentos = new LinkedList<>();
    static HashMap<Cliente, List<Veiculo>> clientesVeiculos = new HashMap<>();
    static Estacionamento avenida;
    static Estacionamento savassi;
    static Estacionamento pampulha;

    public static void main(String[] args) {
        // Use if needs to fill users data.
        preencherDados();

        do {
            System.out.println("\n GESTÃO DE ESTACIONAMENTO");
            System.out.println("\t 1. Cadastrar cliente");
            System.out.println("\t 2. Cadastrar veículo");
            System.out.println("\t 3. Estacionar veículo");
            System.out.println("\t 4. Informar saída do veículo");
            System.out.println("\t 5. Listar clientes e veículos");
            System.out.println("\t 6. Gerar relatório de uso do veículo");
            System.out.println("\t 7. Gerar relatório de uso de vagas");
            System.out.println("\t 8. Gerar relatório de arrecadação total por mês de um estacionamento");
            System.out.println("\t 9. Gerar relatório de valor médio arrecadado de um estacionamento");
            System.out.println("\t 10. Gerar relatório de arrecadação total do estacionamento");
            System.out.println("\t 11. Gerar relatório de veículo por data crescente");
            System.out.println("\t 12. Gerar relatório de veículo por valor decrescente");

            System.out.println("\t 20. Sair\n");

            System.out.print("Escolha uma opção: ");
            try {
                escolha = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                escolha = 20;
            }

            switch (escolha) {
                case 1:
                    criarCliente();
                    break;
                case 2:
                    criarVeiculo();
                    break;
                case 3:
                    estacionarVeiculo();
                    break;
                case 4:
                    registrarSaida();
                    break;
                case 5:
                    listarClientes();
                    break;
                case 6:
                    gerarRelatorioUsoVeiculo();
                    break;
                case 7:
                    gerarRelatorioUsoDeVaga();
                    break;
                case 8:
                    gerarRelatorioArrecadacaoTotalEstacionamento();
                    break;
                case 9:
                    gerarRelatorioTotalArrecadadoPorMesEstacionamento();
                    break;
                case 10:
                    gerarRelatorioValorMeioPorUsoEstacionamento();
                    break;
                case 11:
                    gerarRelatorioDataCrescenteVeiculo();
                    break;
                case 12:
                    gerarRelatorioValorDecrescenteVeiculo();
                    break;
                default:
                    escolha = 20;
                    break;
            }
        } while (escolha != 20);
        scanner.close();
    }

    private static void preencherDados() {
        try {
            FileTool v_readerFile = new FileTool(true);
            String v_line = "INÍCIO";
            v_readerFile.changePath("./projeto3-grupo-1-g2/src/data/pub.in");
            Integer v_insertClients = 0;
            Integer v_estacionarVeiculos = 0;
            for (int i = 1; v_line != ""; i++) {
                v_line = v_readerFile.readLine(1);
                if (i <= 3) {
                    String[] v_lineSplit = v_line.split(" ; ");
                    if (v_line.contains("Avenida")) {
                        avenida = new Estacionamento("Avenida", Integer.parseInt(v_lineSplit[1]),
                                Integer.parseInt(v_lineSplit[2]));
                        listaEstacionamentos.add(avenida);
                    }
                    if (v_line.contains("Savassi")) {
                        savassi = new Estacionamento("Savassi", Integer.parseInt(v_lineSplit[1]),
                                Integer.parseInt(v_lineSplit[2]));
                        listaEstacionamentos.add(savassi);
                    }
                    if (v_line.contains("Pampulha")) {
                        pampulha = new Estacionamento("Pampulha", Integer.parseInt(v_lineSplit[1]),
                                Integer.parseInt(v_lineSplit[2]));
                        listaEstacionamentos.add(pampulha);
                    }
                }
                if (i == 4) {
                    v_insertClients = Integer.parseInt(v_line);
                }
                if (i >= 5) {
                    for (int j = i; i <= v_insertClients; j++) {
                        if (!(i == j)) {
                            v_line = v_readerFile.readLine(1);
                        }
                        String[] v_lineSplit = v_line.split(" ; ");
                        String nomeCliente = v_lineSplit[1];
                        String cpfCliente = v_lineSplit[2];
                        Cliente novoCliente = new Horista(nomeCliente, cpfCliente);
                        Estacionamento estacionamentoEscolhido = null;
                        for (Estacionamento estacionamento : listaEstacionamentos) {
                            if (estacionamento.getLocal().equals(v_lineSplit[0]))
                                estacionamentoEscolhido = estacionamento;
                        }
                        estacionamentoEscolhido.addClienteToEstacionamento(novoCliente);
                        Veiculo novoVeiculo = new Veiculo(v_lineSplit[3]);
                        novoCliente.addVeiculo(novoVeiculo);
                        i = j;
                    }
                }
                if (i == 55) {
                    v_estacionarVeiculos = Integer.parseInt(v_line);
                }
                if (i >= 56) {
                    Integer t_valueToFinish = i + v_estacionarVeiculos - 1;
                    for (int j = i; j <= t_valueToFinish; j++) {
                        if (!(i == j)) {
                            v_line = v_readerFile.readLine(1);
                        }
                        if (v_line != "") {
                            String[] v_lineSplit = v_line.split(" ; ");
                            String placaVeiculo = v_lineSplit[0];
                            String escolhasParaEstacionar = v_lineSplit[1];
                            Integer minutosParaSair = Integer.parseInt(v_lineSplit[2]);
                            boolean escolhas[] = new boolean[3];
                            for (String t_escolhas : escolhasParaEstacionar.split(",")) {
                                if (t_escolhas.equals("0")) {
                                    break;
                                } else if (t_escolhas.equals("1")) {
                                    escolhas[0] = true;
                                } else if (t_escolhas.equals("2")) {
                                    escolhas[1] = true;
                                } else if (t_escolhas.equals("3")) {
                                    escolhas[2] = true;
                                }
                            }

                            Cliente clienteEstacionamento = null;
                            Veiculo veiculoEstacionamento = null;

                            for (Estacionamento estacionamento : listaEstacionamentos) {
                                for (Cliente cliente : estacionamento.clientesVeiculos.keySet()) {
                                    if (cliente.possuiVeiculo(placaVeiculo)) {
                                        clienteEstacionamento = cliente;
                                        for (Veiculo veiculo : cliente.getVeiculos()) {
                                            if (veiculo.getPlaca().equals(placaVeiculo)) {
                                                veiculoEstacionamento = veiculo;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                if (clienteEstacionamento != null) {
                                    break;
                                }
                            }
                            Cliente clienteSaida = null;
                            Veiculo veiculoSaida = null;
                            Estacionamento estacionamentoParaUsar = null;

                            for (Estacionamento estacionamento : listaEstacionamentos) {
                                for (Cliente cliente : estacionamento.clientesVeiculos.keySet()) {
                                    if (cliente.possuiVeiculo(placaVeiculo)) {
                                        clienteSaida = cliente;
                                        for (Veiculo veiculo : cliente.getVeiculos()) {
                                            if (veiculo.getPlaca().equals(placaVeiculo)) {
                                                veiculoSaida = veiculo;
                                                estacionamentoParaUsar = estacionamento;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                if (clienteSaida != null) {
                                    break;
                                }
                            }
                            estacionamentoParaUsar.estacionar(veiculoEstacionamento, escolhas[0], escolhas[1],
                                    escolhas[2]);

                            if (clienteSaida != null && veiculoSaida != null && estacionamentoParaUsar != null) {
                                estacionamentoParaUsar.sair(veiculoSaida, minutosParaSair);
                            }
                        }

                        i = j;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void criarCliente() {
        System.out.println("Escolha o tipo de cliente:");
        System.out.println("\t1. Horista");
        System.out.println("\t2. Mensalista");
        System.out.println("\t3. Turnista");
        System.out.print("Resposta: ");
        int tipoCliente = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Informe o nome do cliente:");
        String nomeCliente = scanner.nextLine();
        System.out.println("Informe o CPF do cliente:");
        String cpfCliente = scanner.nextLine();

        Cliente novoCliente = null;

        switch (tipoCliente) {
            case 1:
                novoCliente = new Horista(nomeCliente, cpfCliente);
                break;
            case 2:
                novoCliente = new Mensalista(nomeCliente, cpfCliente);
                break;
            case 3:
                novoCliente = new Turnista(nomeCliente, cpfCliente);
                break;
            default:
                System.out.println("Tipo de cliente inválido.");
                break;
        }

        if (novoCliente != null) {
            List<Veiculo> veiculosCliente = new ArrayList<>();
            clientesVeiculos.put(novoCliente, veiculosCliente);
            System.out.println("\nEm qual estacionamento deseja armazenar o cliente?");
            for (int i = 0; i < listaEstacionamentos.size(); i++) {
                System.out.println((i + 1) + ". " + listaEstacionamentos.get(i).getLocal());
            }
            System.out.print("Resposta: ");
            int escolhaEstacionamento = scanner.nextInt();
            scanner.nextLine();

            if (escolhaEstacionamento >= 1 && escolhaEstacionamento <= listaEstacionamentos.size()) {
                Estacionamento estacionamentoEscolhido = listaEstacionamentos
                        .get(escolhaEstacionamento - 1);

                boolean clienteExistente = estacionamentoEscolhido.clienteExiste(novoCliente);

                if (!clienteExistente) {
                    boolean clienteAdicionado = estacionamentoEscolhido
                            .addClienteToEstacionamento(novoCliente);

                    if (clienteAdicionado) {
                        System.out.println("\nCliente adicionado com sucesso ao estacionamento: "
                                + estacionamentoEscolhido.getLocal());
                    } else {
                        System.out.println(
                                "O cliente já existe no estacionamento: "
                                        + estacionamentoEscolhido.getLocal());
                    }
                } else {
                    System.out.println(
                            "O cliente já existe no estacionamento: " + estacionamentoEscolhido.getLocal());
                }
            } else {
                System.out.println("Escolha de estacionamento inválida.");
            }
            System.out.println("Cliente cadastrado com sucesso!");
            atualizarDados();
        }
    }

    public static void criarVeiculo() {
        System.out.println("\nIdentificar um cliente e atribuir um veículo:");
        System.out.println("Informe o CPF do cliente:");
        String cpf = scanner.nextLine();

        Cliente clienteExistente = null;
        for (Estacionamento estacionamento : listaEstacionamentos) {
            for (Cliente cliente : estacionamento.clientesVeiculos.keySet()) {
                if (cliente.getCpf().equals(cpf)) {
                    clienteExistente = cliente;
                    break;
                }
            }
        }

        if (clienteExistente != null) {
            System.out.println("Cliente encontrado. Informe os detalhes do veículo:");
            System.out.println("Informe a placa do veículo:");
            String placaVeiculo = scanner.nextLine();

            FabricaVeiculoGenerico factory = new FabricaVeiculoGenerico();
            Veiculo novoVeiculo = factory.CriarVeiculo(placaVeiculo);
            // clienteExistente.addVeiculo(novoVeiculo);

            for (Estacionamento estacionamento : listaEstacionamentos) {
                if (estacionamento.clientesVeiculos.containsKey(clienteExistente)) {
                    estacionamento.clientesVeiculos.get(clienteExistente).add(novoVeiculo);
                    System.out.println(
                            "Veículo atribuído ao cliente no estacionamento: " + estacionamento.getLocal());
                    atualizarDados();
                    break;
                }
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public static void estacionarVeiculo() {
        System.out.print("\nInforme a placa do veículo que deseja estacionar: ");
        String placaVeiculo = scanner.nextLine();

        Cliente clienteEstacionamento = null;
        Veiculo veiculoEstacionamento = null;

        for (Estacionamento estacionamento : listaEstacionamentos) {
            for (Cliente cliente : estacionamento.clientesVeiculos.keySet()) {
                if (cliente.possuiVeiculo(placaVeiculo)) {
                    clienteEstacionamento = cliente;
                    for (Veiculo veiculo : cliente.getVeiculos()) {
                        if (veiculo.getPlaca().equals(placaVeiculo)) {
                            veiculoEstacionamento = veiculo;
                            veiculoEstacionamento.addObservador(estacionamento);
                            break;
                        }
                    }
                    break;
                }
            }
            if (clienteEstacionamento != null) {
                break;
            }
        }

        if (clienteEstacionamento != null && veiculoEstacionamento != null) {
            Integer tipoServico = 1;
            boolean usarManobrita = false;
            boolean usarLavagem = false;
            boolean usarPolimento = false;
            while (tipoServico != 0) {
                System.out.println("Escolha o tipo de serviço que vai ser utilizado:");
                System.out.println("\t0. Todos serviços escolhidos");
                System.out.println("\t1. Manobrista");
                System.out.println("\t2. Lavagem");
                System.out.println("\t3. Polimento");
                System.out.print("Resposta: ");
                tipoServico = scanner.nextInt();
                switch (tipoServico) {
                    case 1:
                        usarManobrita = true;
                        break;
                    case 2:
                        usarLavagem = true;
                        break;
                    case 3:
                        usarPolimento = true;
                        break;
                }
            }
            System.out.println("Veículo encontrado. Estacionando...");

            boolean estacionadoComSucesso = false;
            for (Estacionamento estacionamento : listaEstacionamentos) {
                estacionadoComSucesso = estacionamento.estacionar(veiculoEstacionamento, usarManobrita,
                        usarLavagem, usarPolimento);
                if (estacionadoComSucesso) {
                    System.out.println("Veículo estacionado com sucesso no estacionamento: "
                            + estacionamento.getLocal());
                    break;
                }
            }

            if (!estacionadoComSucesso) {
                System.out.println("Não foi possível estacionar o veículo.");
            }
        } else {
            System.out.println("Veículo não encontrado ou cliente não cadastrado.");
        }
    }

    public static void registrarSaida() {
        System.out.print("Informe a placa do veículo que finalizou o uso da vaga: ");
        String placaVeiculoSair = scanner.nextLine();

        Cliente clienteSaida = null;
        Veiculo veiculoSaida = null;
        Estacionamento estacionamentoSaida = null;

        for (Estacionamento estacionamento : listaEstacionamentos) {
            for (Cliente cliente : estacionamento.clientesVeiculos.keySet()) {
                if (cliente.possuiVeiculo(placaVeiculoSair)) {
                    clienteSaida = cliente;
                    for (Veiculo veiculo : cliente.getVeiculos()) {
                        if (veiculo.getPlaca().equals(placaVeiculoSair)) {
                            veiculoSaida = veiculo;
                            estacionamentoSaida = estacionamento;
                            break;
                        }
                    }
                    break;
                }
            }
            if (clienteSaida != null) {
                break;
            }
        }

        if (clienteSaida != null && veiculoSaida != null && estacionamentoSaida != null) {
            System.out.println("Quanto tempo o carro ficou no estacionamento?(Em minutos)");
            Integer tempoParaSair = scanner.nextInt();
            boolean veiculoRemovido = estacionamentoSaida.sair(veiculoSaida, tempoParaSair);

            if (veiculoRemovido) {
                System.out.println("Veículo deixou o estacionamento: " + estacionamentoSaida.getLocal());
            } else {
                System.out.println("Não foi possível remover o veículo do estacionamento.");
            }
        } else {
            System.out.println("Veículo não encontrado ou não está estacionado em nenhum estacionamento.");
        }
    }

    public static void listarClientes() {
        for (Estacionamento estacionamento : listaEstacionamentos) {
            HashMap<Cliente, List<Veiculo>> clientesVeiculosEstacionamento = estacionamento
                    .getClientesVeiculos();
            for (Cliente cliente : clientesVeiculosEstacionamento.keySet()) {
                System.out.println("\tCliente: " + cliente.getNome() + " - CPF: " + cliente.getCpf());
                List<Veiculo> veiculos = clientesVeiculosEstacionamento.get(cliente);
                for (Veiculo veiculo : veiculos) {
                    System.out.println("\t\tVeículo: " + veiculo.getPlaca());
                }
            }
        }
    }

    public static void gerarRelatorioDataCrescenteVeiculo() {
        System.out.println("Informe a placa do veículo para calcular o relatório por data de forma crescente:");
        String placaVeiculoParaRelatorio = scanner.nextLine();

        for (Estacionamento estacionamento : listaEstacionamentos) {
            for (Cliente cliente : estacionamento.getClientesVeiculos().keySet()) {
                List<Veiculo> veiculos = estacionamento.getClientesVeiculos().get(cliente);
                for (Veiculo veiculo : veiculos) {
                    if (veiculo.getPlaca().equals(placaVeiculoParaRelatorio)) {
                        System.out.println(veiculo.gerarRelatorioVagasPorDataCrescente());
                        break;
                    }
                }
            }
        }
    }

    public static void gerarRelatorioValorDecrescenteVeiculo() {
        System.out.println("Informe a placa do veículo para calcular o relatório por valor de forma decrescente:");
        String placaVeiculoParaRelatorio = scanner.nextLine();

        for (Estacionamento estacionamento : listaEstacionamentos) {
            for (Cliente cliente : estacionamento.getClientesVeiculos().keySet()) {
                List<Veiculo> veiculos = estacionamento.getClientesVeiculos().get(cliente);
                for (Veiculo veiculo : veiculos) {
                    if (veiculo.getPlaca().equals(placaVeiculoParaRelatorio)) {
                        System.out.println(veiculo.gerarRelatorioVagasPorValorDecrescente());
                        break;
                    }
                }
            }
        }
    }

    public static void gerarRelatorioUsoVeiculo() {
        System.out.println("Informe a placa do veículo para calcular o valor médio por uso:");
        String placaVeiculoParaRelatorio = scanner.nextLine();

        double valorMedioPorUso = 0;
        int countEstacionamentos = 0;
        for (Estacionamento estacionamento : listaEstacionamentos) {
            for (Cliente cliente : estacionamento.getClientesVeiculos().keySet()) {
                List<Veiculo> veiculos = estacionamento.getClientesVeiculos().get(cliente);
                for (Veiculo veiculo : veiculos) {
                    if (veiculo.getPlaca().equals(placaVeiculoParaRelatorio)) {
                        valorMedioPorUso += cliente.arrecadadoTotal();
                        countEstacionamentos++;
                        break;
                    }
                }
            }
        }
        if (countEstacionamentos > 0) {
            double valorMedioTotal = valorMedioPorUso / countEstacionamentos;
            System.out.println(
                    "Valor médio por uso do veículo " + placaVeiculoParaRelatorio + ": " + valorMedioTotal);
        } else {
            System.out.println(
                    "Veículo não encontrado ou não está associado a nenhum cliente nos estacionamentos.");
        }
    }

    public static void gerarRelatorioUsoDeVaga() {
        System.out.println("Informe a placa do veículo para gerar o relatório de uso de vagas:");
        String placaVeiculoRelatorio = scanner.nextLine();

        for (Estacionamento estacionamento : listaEstacionamentos) {
            for (Cliente cliente : estacionamento.getClientesVeiculos().keySet()) {
                List<Veiculo> veiculos = estacionamento.getClientesVeiculos().get(cliente);
                for (Veiculo veiculo : veiculos) {
                    if (veiculo.getPlaca().equals(placaVeiculoRelatorio)) {
                        if (veiculo instanceof Veiculo) {
                            String relatorioVagas = ((Veiculo) veiculo).gerarRelatorioVagas();
                            System.out.println(relatorioVagas);
                        } else {
                            System.out.println("Este veículo não suporta relatório de vagas.");
                        }
                        break;
                    }
                }
            }
        }
    }

    public static void gerarRelatorioTotalArrecadadoPorMesEstacionamento() {
        System.out.println("Informe o estacionamento que será gerado o relatório:");
        String estacionamentoString = scanner.nextLine();
        System.out.println("Informe o mês que será gerado o relatório:");
        String mes = scanner.nextLine();
        for (Estacionamento t_estacionamento : listaEstacionamentos) {
            if (t_estacionamento.getLocal().equals(estacionamentoString)) {
                System.out.println(t_estacionamento.relatorioArrecadacaoNoMes(Integer.parseInt(mes)));
            }
        }
    }

    /**
     * Gera um relatório contendo informações sobre a arrecadação total do
     * estacionamento.
     *
     * @return O relatório formatado da arrecadação total do estacionamento.
     */
    public static void gerarRelatorioArrecadacaoTotalEstacionamento() {
        System.out.println("Informe o estacionamento desejado:");
        String relatorioEstacionamento = scanner.nextLine();

        for (Estacionamento estacionamento : listaEstacionamentos) {
            if (estacionamento.getLocal().equals(relatorioEstacionamento)) {
                System.out.println(estacionamento.relatorioArrecadacaoTotal());
            }
        }
    }

    public static void gerarRelatorioValorMeioPorUsoEstacionamento() {
        System.out.println("Informe o estacionamento que será gerado o relatório:");
        String estacionamentoString = scanner.nextLine();
        for (Estacionamento t_estacionamento : listaEstacionamentos) {
            if (t_estacionamento.getLocal().equals(estacionamentoString)) {
                System.out.println(t_estacionamento.relatorioValorMedioPorUso());
            }
        }
    }
}