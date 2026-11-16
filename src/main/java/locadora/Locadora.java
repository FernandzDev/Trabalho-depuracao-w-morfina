package locadora;

/**
 * Classe de serviço da locadora: reúne as operações de consulta, cálculo de
 * valores, descontos e multas sobre a frota, os clientes e os seguros.
 *
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
     * @param carros frota a ser analisada
     * @return quantidade de carros disponiveis para alugar
     */
    public int contarCarrosDisponiveis(Carro[] carros) {
        int total = 0;
        for (int i = 0; i < carros.length; i++) {
            if (carros[i].isDisponivel()) {
                total++;
            }
        }
        return total;
    }


    /**
     * Soma o valor da diária de todos os carros da frota, estejam eles
     * disponíveis ou não.
     *
     * @param carros frota a ser somada (não deve ser null nem conter elementos null)
     * @return soma das diárias, em reais. 0 se a frota estiver vazia
     */
    public double calcularReceitaTotalFrota(Carro[] carros) {
        double total = 0;
        for (int i = 0; i < carros.length; i++) {
            total += carros[i].getValorDiaria();
        }
        return total;
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
     * Busca um carro pela placa e devolve uma descrição com o modelo e o valor
     * da diária. Se houver mais de um carro com a mesma placa, vale o primeiro
     *
     * @param carros frota onde a busca será feita (não deve ser null)
     * @param placa placa procurada
     * @return texto no formato "Modelo - R$valor/dia", ou uma mensagem
     * informando que o carro não foi encontrado
     */
    public String buscarCarroPorPlaca(Carro[] carros, String placa) {
        for (int i = 0; i < carros.length; i++) {
            if (carros[i].getPlaca().equals(placa)) {
                return carros[i].getModelo() + " - R$" + carros[i].getValorDiaria() + "/dia";
            }
        }
        return "Carro de placa " + placa + " nao encontrado";
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
     * @param carro carro que sera alugado
     * @param cliente cliente que esta alugando
     * @param seguro seguro contratado
     * @param dias quantidade de dias de aluguel
     * @return valor final a pagar em reais
     */
    public double processarLocacaoCompleta(Carro carro, Cliente cliente, Seguro seguro, int dias) {
        validarLocacao(carro, dias);
        double valorBruto = calcularValorBruto(carro, seguro, dias);
        return aplicarDescontosEEncargos(valorBruto, cliente, dias);
    }

    /**
     * Valida se a locação pode acontecer
     * @param carro a ser alugado
     */
    private void validarLocacao(Carro carro) {
        if (!carro.isDisponivel()) {
            throw new IllegalStateException("Carro indisponivel");
        }
    }

    /**
     * Calcula o valor bruto da locação sem desconto.
     *
     * @param carro carro alugado
     * @param seguro seguro contratado
     * @param dias quantidade de dias
     * @return (diária do carro + diária do seguro) * dias
     */
    private double calcularValorBruto(Carro carro, Seguro seguro, int dias) {
        return (carro.getValorDiaria() + seguro.getValorDiario()) * dias;
    }

    /**
     *
     * @param valorBruto
     * @param cliente
     * @param dias
     * @return
     */
    private double aplicarDescontosEEncargos(double valorBruto, Cliente cliente, int dias) {
        if (cliente.isApto() || dias >= 7) {
            return valorBruto * 0.85;
        }
        return valorBruto;
    }

    // Calcula a diaria de um aluguel a partir da categoria do veiculo (0, 1 ou 2),
    // multiplicada pela quantidade de dias.
    // TODO: adicionar Javadoc.
    public double calcularDiariaComCategoria(int categoria, int dias) {
        double valorDiaria = TABELA_PRECOS[categoria];
        return valorDiaria * dias;
    }

    // Gera um resumo com a quantidade de carros disponiveis e o valor medio
    // da diaria apenas dos carros disponiveis.
    // TODO: adicionar Javadoc.
    public String gerarResumoFrota(Carro[] carros) {
        int disponiveis = 0;
        double somaValores = 0;
        for (int i = 0; i < carros.length; i++) {
            if (carros[i].isDisponivel()) {
                disponiveis++;
                somaValores += carros[i].getValorDiaria();
            }
        }
        double media = disponiveis == 0 ? 0 : somaValores / disponiveis;
        return disponiveis + " carro(s) disponivel(is), media de R$" + media + "/dia";
    }

    // Aplica um desconto escalonado sobre o valor base, conforme a quantidade
    // de dias alugados: 5% (3+ dias), 10% (7+ dias) ou 20% (15+ dias).
    // TODO: adicionar Javadoc.
    public double calcularDescontoEscalonado(double valorBase, int dias) {
        double desconto = 0;
        for (int i = 0; i <= LIMIARES_DIAS.length; i++) {
            if (dias >= LIMIARES_DIAS[i]) {
                desconto = DESCONTOS[i];
            }
        }
        return valorBase * (1 - desconto);
    }

    // Verifica se o cliente tem direito ao desconto de fidelidade: precisa ter
    // pelo menos 25 anos E pelo menos 3 locacoes anteriores.
    // TODO: adicionar Javadoc.
    public boolean clienteElegivelDescontoFidelidade(Cliente cliente, int totalLocacoesAnteriores) {
        return cliente.getIdade() > 25 && totalLocacoesAnteriores >= 3;
    }
}
