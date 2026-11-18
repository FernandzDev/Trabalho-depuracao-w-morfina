package locadora;

/**
 * Representa a reserva de um item alugável por um cliente.
 */
public class Reserva {
    private final Cliente cliente;
    private final Alugavel veiculo;
    private final int dias;

    /**
     * Cria uma reserva sem alterar a disponibilidade do item.
     * @param cliente cliente responsável pela reserva
     * @param veiculo veiculo alugável associado à reserva
     * @param dias quantidad de dias da reserva
     * @throws IllegalArgumentException se a quantidade de dias for menor que um
     */
    public Reserva(Cliente cliente, Alugavel veiculo, int dias) {
        if (dias <= 0){
            throw new IllegalArgumentException("A reserva precisar ter pelo menos um dia");
        }

        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dias = dias;
    }

    /**
     * Multiplica a diária final do item pela quantidade de dias de reserva.
     *
     * @return subtotal em reais, sem seguro nem descontos
     */
    public double calcularSubtotal(){
        double diaria = veiculo.calcularValorDiaria();
        diaria = diaria * dias;
        return diaria;
    }

}
