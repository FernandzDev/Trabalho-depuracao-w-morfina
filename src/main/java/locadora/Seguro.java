package locadora;

/**
 * Representa um plano de seguro que pode ser contratado junto com a locação.
 *
 * O seguro é cobrado por dia e somado à diária do veículo
 */
public class Seguro {

    private String tipo;
    private double valorDiario;

    /**
     * cria um plano de seguro.
     *
     * @param tipo tipo d seguro
     * @param valorDiario Valor cobrado por dia em reais
     */
    public Seguro(String tipo, double valorDiario) {
        this.tipo = tipo;
        this.valorDiario = valorDiario;
    }

    public String getTipo() {
        return tipo;
    }

    public double getValorDiario() {
        return valorDiario;
    }
}
