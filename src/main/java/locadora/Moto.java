package locadora;

public class Moto implements Alugavel {

    private String placa;
    private String modelo;
    private double valorDiaria;
    private boolean disponivel;
    private int cilindrada;

    /**
     * Cria uma nova moto, que já começa disponível para locação.
     *
     * @param placa
     * @param modelo
     * @param valorDiaria
     * @param disponivel
     * @param cilindrada
     */
    public Moto(String placa, String modelo, double valorDiaria, boolean disponivel, int cilindrada) {
        this.placa = placa;
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
        this.disponivel = disponivel;
        this.cilindrada = cilindrada;
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

    @Override
    public double calcularValorDiaria() {
        if (cilindrada > 300) {
            return valorDiaria + 30.0;
        }
        return valorDiaria;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    @Override
    public String getDescricao() {
        return "Moto "+ modelo + " ("+ cilindrada +" cc)";
    }

    public int getCilindrada() {
        return cilindrada;
    }

    /**
     * Altera a disponibilidade da moto
     *
     * @param disponivel
     */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
