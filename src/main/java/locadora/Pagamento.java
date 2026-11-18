package locadora;

/**
 * Registra o valor cobrado e a situação do pagamento de uma reserva
 */
public class Pagamento {
    private final Reserva reserva;
    private final double valorCobrado;
    private boolean pago;

    /**
     * Cria um pagamento inicialmente pendente.
     * @param reserva reserva à qual o pagamento está associado
     * @param valorCobrado valor a pagar, em reais
     */
    public Pagamento(Reserva reserva, double valorCobrado) {
        this.reserva = reserva;
        this.valorCobrado = valorCobrado;
        this.pago = false;
    }

    /**
     * Marca o pagamento como quitado sem alterar o valor cobrado.
     */
    public void confirmarPagamento(){
        this.pago = true;
    }

    /**
     * Consulta se o pagamento foi confirmado
     * @return {@Code true} se o pagamento foi confirmado
     * ou {@code false} se ainda está pendente
     */
    public boolean isPago() {
        return pago;
    }

    /**
     * Retorna o valor registrado no pagamento.
     * @return valor cobrado, em reais.
     */
    public double getValorCobrado(){
        return valorCobrado;
    }
}
