package locadora;

/**
 * Reune os dados e comportamentos comuns aos veículos da locadora.

 */
public abstract class Veiculo implements Alugavel {
    private final String placa;
    private final String modelo;
    private final double valorDiariaBase;
    private boolean disponivel;

    /**
     * Inicializa os dados do veiculo e o marca como disponivel.
     * @param placa placa do veículo
     * @param modelo modelo do veículo
     * @param valorDiariaBase valor da diária sem adicionais, em reais
     */
    protected Veiculo(String placa, String modelo, double valorDiariaBase) {
        this.placa = placa;
        this.modelo = modelo;
        this.valorDiariaBase = valorDiariaBase;
        this.disponivel = true;
    }

    /**
     * Retorna a placa do veiculo
     * @return placa do veículo.
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Retorna o modelo do veículo
     *
     * @return modelo do veículo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Retorna a diária antes dos adicionais específicos do veículo
     *
     * @return diária base em reais.
     */
    public double getValorDiariaBase() {
        return valorDiariaBase;
    }

    @Override
    public boolean isDisponivel(){
        return disponivel;
    }

    /**
     * Altera a disponibilidade do veículo para locação
     * @param disponivel {@code true} para marcar o veículo como disponível
     * ou {@code false} para marcá-lo como indisponível
     */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
