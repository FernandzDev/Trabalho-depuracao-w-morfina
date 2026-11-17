package locadora;

public class Van implements Alugavel {
    private String placa;
    private String modelo;
    private double valorDiaria;
    private boolean disponivel;
    private int capacidadeCarga;

    /**
     * Cria uma nova van, que já começa disponível para locação.
     *
     * @param placa
     * @param modelo
     * @param valorDiaria
     * @param disponivel
     * @param capacidadeCarga
     */
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


    @Override
    public double calcularValorDiaria() {
        if (capacidadeCarga > 1000){
            return valorDiaria + 50.0;
        }
        return valorDiaria;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    @Override
    public String getDescricao() {
        return "Van "+ modelo + " ( Capacidade: "+ capacidadeCarga +" kg)";
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
