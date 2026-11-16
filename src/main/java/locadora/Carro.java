package locadora;

/**
 * Cada carro é identificado pela placa, possui um modelo, um valor de
 * diária e um indicador de disponibilidade.
 */
public class Carro {

    private String placa;
    private String modelo;
    private double valorDiaria;
    private boolean disponivel;

    /**
     * Cria um carro novo, que já começa disponível para locação.
     *
     * @param placa       (placa do carro)
     * @param modelo      (modelo do carro)
     * @param valorDiaria (valor da diaria do carro em reais)
     */
    public Carro(String placa, String modelo, double valorDiaria) {
        this.placa = placa;
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
        this.disponivel = true;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    /**
     * Altera a disponibilidade do carro.
     *
     * @param disponivel true para marcar como disponível, false para indisponível
     */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
