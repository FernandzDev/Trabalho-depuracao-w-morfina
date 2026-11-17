package locadora;

public class Van {
    private String placa;
    private String modelo;
    private double valorDiaria;
    private boolean disponivel;
    private int capacidadeCarga;

    public Van(String placa, String modelo, double valorDiaria, boolean disponivel, int capacidadeCarga) {
        this.placa = placa;
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
        this.disponivel = disponivel;
        this.capacidadeCarga = capacidadeCarga;
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

    public int getCapacidadeCarga() {
        return capacidadeCarga;
    }

    /**
     * Altera a disponibilidade da Van.
     *
     * @param disponivel
     */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
