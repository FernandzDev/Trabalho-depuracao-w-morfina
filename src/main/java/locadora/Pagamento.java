package locadora;

public class Pagamento {
    private final Reserva reserva;
    private final double valorCobrado;
    private boolean pago;

    public Pagamento(Reserva reserva, double valorCobrado) {
        this.reserva = reserva;
        this.valorCobrado = valorCobrado;
        this.pago = false;
    }

    public void confirmarPagamento(){
        this.pago = true;
    }

    public boolean isPago() {
        return pago;
    }

    public double getValorCobrado(){
        return valorCobrado;
    }
}
