package locadora;

public class Reserva {
    private final Cliente cliente;
    private final Alugavel veiculo;
    private final int dias;

    public Reserva(Cliente cliente, Alugavel veiculo, int dias) {
        if (dias <= 0){
            throw new IllegalArgumentException("A reserva precisar ter pelo menos um dia");
        }

        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dias = dias;
    }

    public double calcularSubtotal(){
        double diaria = veiculo.calcularValorDiaria();
        diaria = diaria * dias;
        return diaria;
    }

}
