package locadora;

/**
 * Classe de serviço da locadora: reúne as operações de consulta, cálculo de
 * valores, descontos e multas sobre a frota, os clientes e os seguros.
 * A classe não guarda estado próprio, apenas constantes de tabelas de
 * preço e de desconto. Todos os dados chegam por parâmetro.
 */
public class Locadora {

    private static final double[] TABELA_PRECOS = {90.0, 130.0, 180.0};
    private static final int[] LIMIARES_DIAS = {3, 7, 15};
    private static final double[] DESCONTOS = {0.05, 0.10, 0.20};

    /**
     * Vetor utilizado para fazer a contagem de carros disponiveis.
     *
     * @param veiculos frota a ser analisada
     * @return quantidade de carros disponiveis para alugar
     */
    public int contarVeiculosDisponiveis(Veiculo[] veiculos) {
        int total = 0;
        for (int i = 0; i < veiculos.length; i++) {
            if (veiculos[i].isDisponivel()) {
                total++;
            }
        }
        return total;
    }

    /**
     * Soma o valor da diária de todos os veiculos da frota, estejam eles
     * disponíveis ou não.
     *
     * @param veiculos frota a ser somada (não deve ser null nem conter elementos null)
     * @return soma das diárias, em reais. 0 se a frota estiver vazia
     */
    public double calcularReceitaTotalFrota(Veiculo[] veiculos) {
        return somarDiarias(veiculos);
    }

    /**
     * Calcula a media de idade dos clientes informados.
     *Se a lista for null ou vazia, devolve 0 em vez de
     * tentar dividir por zero.
     *
     * @param clientes cuja a idade ser considerada
     * @return média de idade, 0 se não houver clientes
     */
    public double calcularMediaIdadeClientes(Cliente[] clientes) {
        if (clientes == null || clientes.length == 0) {
            return 0;
        }
        double soma = 0;
        for (int i = 0; i < clientes.length; i++) {
            soma += clientes[i].getIdade();
        }
        return soma / clientes.length;
    }

    /**
     * Busca, entre os clientes informados, o de maior idade. Em caso de
     * empate, devolve o que aparece primeiro no array.
     *
     * @param clientes a serem comparados (não deve ser null)
     * @return o cliente mais velho
     */
    public Cliente buscarClienteMaisVelho(Cliente[] clientes) {
        Cliente maisVelho = null;
        for (int i = 0; i < clientes.length; i++) {
            if (maisVelho == null || clientes[i].getIdade() > maisVelho.getIdade()) {
                maisVelho = clientes[i];
            }
        }
        return maisVelho;
    }

    /**
     * Busca um veiculo pela placa e devolve uma descrição com o modelo e o valor
     * da diária. Se houver mais de um veiculo com a mesma placa, vale o primeiro
     *
     * @param veiculos frota onde a busca será feita (não deve ser null)
     * @param placa placa procurada
     * @return texto no formato "Modelo - R$valor/dia", ou uma mensagem
     * informando que o carro não foi encontrado
     */
    public String buscarVeiculoPorPlaca(Veiculo[] veiculos, String placa) {
        for (int i = 0; i < veiculos.length; i++) {
            if (veiculos[i].getPlaca().equals(placa)) {
                return veiculos[i].getModelo() + " - R$" + veiculos[i].calcularValorDiaria() + "/dia";
            }
        }
        return "Veiculo de placa " + placa + " nao encontrado";
    }

    /**
     * Calcula a multa por atraso na devolução, cobrando R$ 40,00 por dia de
     * atraso.
     *
     * @param diasAtraso quantidade de dias de atraso, caso tenha 0 dias de atraso não tem multa
     * @return valor total da multa
     */
    public double calcularMultaAtraso(int diasAtraso) {
        double multaPorDia = 40;
        if (diasAtraso <= 0) {
            return 0;
        }
        return multaPorDia * diasAtraso;
    }

    /**
     * Processa uma locação completa validando o carro e a quantidade de dias,
     * calcula o valor bruto ((diária + seguro) * dias) e aplica 15% de
     * desconto quando o cliente estiver apto e o aluguel for de 7 dias
     * ou mais.
     *
     * @param veiculo carro que sera alugado
     * @param cliente cliente que esta alugando
     * @param seguro seguro contratado
     * @param dias quantidade de dias de aluguel
     * @return valor final a pagar em reais
     */
    public double processarLocacaoCompleta(Veiculo veiculo, Cliente cliente, Seguro seguro, int dias) {
        validarLocacao(veiculo, dias);
        double valorBruto = calcularValorBruto(veiculo, seguro, dias);
        return aplicarDescontosEEncargos(valorBruto, cliente, dias);
    }

    /**
     * Valida se a locação pode acontecer
     * @param item veiculo a ser alugado
     * @param dias quantidade de dias que foi alugado
     */
    private void validarLocacao(Alugavel item, int dias) {
        if (!item.isDisponivel()) {
            throw new IllegalStateException("Veiculo indisponivel");
        }
        if (dias <= 0){
            throw new IllegalStateException("A locação precisa ter pelo menos 1 dia");
        }
    }

    /**
     * Calcula o valor bruto da locação sem desconto.
     *
     * @param item veiculo alugado
     * @param seguro seguro contratado
     * @param dias quantidade de dias
     * @return (diária do carro + diária do seguro) * dias
     */
    private double calcularValorBruto(Alugavel item, Seguro seguro, int dias) {
        return (item.calcularValorDiaria() + seguro.getValorDiario()) * dias;
    }

    /**
     * Aplica um desconto no valor bruto quando o cliente é apto
     * ou o aluguel tem 7 dias ou mais. Se qualqer uma das duas
     * condições falhar, o valor não muda.
     *
     * @param valorBruto valor antes do desconto
     * @param cliente cliente da locadora
     * @param dias quantidade de dias
     * @return valor final, seja com ou sem o desconto
     */
    private double aplicarDescontosEEncargos(double valorBruto, Cliente cliente, int dias) {
        if (cliente.isApto() && dias >= 7) {
            return valorBruto * 0.85;
        }
        return valorBruto;
    }
    /**
     * Calcula o valor de um aluguel a partir da categoria do veículo,
     * multiplicando a diária da categoria pela quantidade de dias.
     * @param categoria indica qual a categoria do veiculo: 0(R$ 90,00), 1 (R$ 130,00) ou 2 (R$ 180,00)
     * @param dias quantidade de dias
     * @return valor do aluguel em reais
     * @throws IllegalArgumentException se a categoria não existir na tabela ou se dias for negativo
     */
    public double calcularDiariaComCategoria(int categoria, int dias) {
        if (categoria < 0 || categoria >= TABELA_PRECOS.length) {
            throw new IllegalArgumentException(
                    "Categoria invalida: " + categoria + " (use um valor de 0 a " + (TABELA_PRECOS.length - 1) + ")");
        }
        if (dias < 0) {
            throw new IllegalArgumentException("A quantidade de dias nao pode ser negativa");
        }
        double valorDiaria = TABELA_PRECOS[categoria];
        return valorDiaria * dias;
    }


    /**
     * Mostra a quantidade de carros disponiveis e calcula a média do valor diario
     * apenas de carros disponiveis
     *
     * @param veiculos frota a ser resumida
     * @return texto no formato "N carro(s) disponivel(is), media de R$X/dia,
     * a média é 0 quando não há carros disponíveis
     */
    public String gerarResumoFrota(Alugavel[] veiculos) {
        int disponiveis = 0;
        double somaValores = 0;
        for (int i = 0; i < veiculos.length; i++) {
            if (veiculos[i].isDisponivel()) {
                disponiveis++;
                somaValores += veiculos[i].calcularValorDiaria();
            }
        }
        double media = disponiveis == 0 ? 0 : somaValores / disponiveis;
        return disponiveis + " carro(s) disponivel(is), media de R$" + media + "/dia";
    }

    /**
     * Aplica um desconto escalonado sobre o valor base, conforme a quantidade
     * de dias alugados: 5% (3+ dias), 10% (7+ dias) ou 20% (15+ dias).
     *
     * @param valorBase valor antes do desconto
     * @param dias quantidade de dias alugados
     * @return valor final ja com o desconto aplicado
     */

    public double calcularDescontoEscalonado(double valorBase, int dias) {
        double desconto = 0;
        for (int i = 0; i < LIMIARES_DIAS.length; i++) {
            if (dias >= LIMIARES_DIAS[i]) {
                desconto = DESCONTOS[i];
            }
        }
        return valorBase * (1 - desconto);
    }

    /**
     * Verifica se o cliente tem direito ao desconto de fidelidade: precisa ter
     * pelo menos 25 anos E pelo menos 3 locacoes anteriores.
     *
     * @param cliente
     * @param totalLocacoesAnteriores
     * @return true se o cliente for elegivel e false caso contrario
     */

    public boolean clienteElegivelDescontoFidelidade(Cliente cliente, int totalLocacoesAnteriores) {
        return cliente.getIdade() >= 25 && totalLocacoesAnteriores >= 3;
    }
    /**
     * Soma as diárias de todos os itens, inclusive os indisponíveis
     * @param itens vetor não nulo e sem elementos nulos
     * @return total em reais, ou zero se o vetor estiver vazio
     */
    public double somarDiarias(Alugavel[] itens){
        double total = 0;

        for (Alugavel item : itens) {
            total += item.calcularValorDiaria();
        }
        return total;
    }
}
