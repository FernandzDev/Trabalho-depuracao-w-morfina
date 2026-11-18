package locadora;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Carro[] carros = {
            new Carro("ABC1234", "Onix", 100.0),
            new Carro("XYZ9988", "Corolla", 220.0),
            new Carro("JJK4521", "HB20", 90.0),
            new Carro("QRS7788", "Civic", 250.0)
        };
        carros[1].setDisponivel(false);

        Van[] vans = {
            new Van("VAN1234", "Fiorino", 130.0,  650),
            new Van("VAN5678", "Master", 200.0,  1500),
            new Van("VAN9012", "Ducato", 180.0,  1200),
            new Van("VAN3456", "Partner Rapid", 140.0,  650)
        };
        vans[1].setDisponivel(false);

        Moto[] motos = {
            new Moto("MOT1234", "CG 160", 70.0, 162),
            new Moto("MOT5678", "CB 500F", 110.0, 471),
            new Moto("MOT9012", "MT-03", 95.0, 321),
            new Moto("MOT3456", "Factor 150", 65.0, 149)
        };
        motos[1].setDisponivel(false);

        Cliente[] clientes = {
            new Cliente("Joana", 25, 5),
            new Cliente("Pedro", 19, 0),
            new Cliente("Marcos", 30, 3),
            new Cliente("Ana", 25, 1)
        };

        Cliente[] clientesNovos = {};

        Seguro seguroCompleto = new Seguro("Completo", 25.0);

        Locadora locadora = new Locadora();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        // Aréa de teste //
        //
        //

        //
        //
        // Fechamento area para testes //

        Veiculo[] frota = new Veiculo[carros.length + motos.length + vans.length];

        System.arraycopy(carros, 0, frota, 0, carros.length);
        System.arraycopy(motos, 0, frota, carros.length, motos.length);
        System.arraycopy(vans, 0, frota, carros.length + motos.length, vans.length);

        while (opcao != 0) {
            exibirMenu();
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Veiculos disponiveis: " + locadora.contarVeiculosDisponiveis(frota));
                    break;
                case 2:
                    System.out.println("Receita total da frota: R$" + locadora.calcularReceitaTotalFrota(frota));
                    break;
                case 3:
                    System.out.println("Calculando media de idade (nenhum cliente cadastrado ainda)...");
                    System.out.println("Media de idade: " + locadora.calcularMediaIdadeClientes(clientesNovos));
                    break;
                case 4:
                    Cliente maisVelho = locadora.buscarClienteMaisVelho(clientes);
                    System.out.println("Cliente mais velho: " + maisVelho.getNome() + " (" + maisVelho.getIdade() + " anos)");
                    break;
                case 5:
                    System.out.println("Buscando veiculo de placa ZZZ0000...");
                    System.out.println(locadora.buscarVeiculoPorPlaca(frota, "ZZZ0000"));
                    break;
                case 6:
                    System.out.println("Multa para 3 dias de atraso: R$" + locadora.calcularMultaAtraso(3));
                    break;
                case 7:
                    System.out.println("Processando locacao do Onix para Pedro, 10 dias, com seguro completo...");
                    System.out.println("Valor total: R$" + locadora.processarLocacaoCompleta(carros[0], clientes[1], seguroCompleto, 10));
                    break;
                case 8:
                    int categoria = 1;
                    int dias = 5;

                    double total = locadora.calcularDiariaComCategoria(categoria, dias);

                    System.out.printf("Categoria %d por %d dias: R$ %.2f%n", categoria, dias, total);
                    break;
                case 9:
                    System.out.println(locadora.gerarResumoFrota(frota));
                    break;
                case 10:
                    System.out.println("Aplicando desconto escalonado em R$1000,00 por 10 dias...");
                    System.out.println("Valor com desconto: R$" + locadora.calcularDescontoEscalonado(1000, 10));
                    break;
                case 11:
                    System.out.println("Verificando fidelidade da cliente Ana (25 anos, 5 locacoes anteriores)...");
                    System.out.println("Elegivel? " + locadora.clienteElegivelDescontoFidelidade(clientes[3], 5));
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n===== SISTEMA LOCADORA - TRABALHO ANALISE DE ALGORITMOS =====");
        System.out.println("1 - Contar carros disponiveis");
        System.out.println("2 - Calcular receita total da frota");
        System.out.println("3 - Calcular media de idade dos clientes");
        System.out.println("4 - Buscar cliente mais velho");
        System.out.println("5 - Buscar carro por placa");
        System.out.println("6 - Calcular multa por atraso");
        System.out.println("7 - Processar locacao completa");
        System.out.println("8 - Calcular diaria por categoria");
        System.out.println("9 - Gerar resumo da frota");
        System.out.println("10 - Calcular desconto escalonado");
        System.out.println("11 - Verificar elegibilidade de fidelidade");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");
    }
}

