package locadora;

/**
 * Representa uma van cuja diária pode variar conforme a capacidade de carga
 */
public class Van extends Veiculo {

    private final int capacidadeCarga;

    /**
     * Cria uma van nova, que já começa como disponivel e indica que a "capacidadeCarga" pertence a esse objeto.
     * @param placa (placa da van)
     * @param modelo (modelo da van)
     * @param valorDiaria (valor da diaria da van)
     * @param capacidadeCarga (indica qual a capacidade de carga da van)
     */
    public Van(String placa, String modelo, double valorDiaria, int capacidadeCarga) {
        super(placa, modelo, valorDiaria);
        this.capacidadeCarga = capacidadeCarga;
    }

    /**
     * Retorna a capacidade de carga da van
     * @return capacidade de carga em quilogramas (kg)
     */
    public int getCapacidadeCarga () {
        return capacidadeCarga;
    }

    /**
     * Calcula a diária da van com adicional de R$ 50,00 acima de 1.00 kg.
     * @return diária final da van, em reais.
     */
        @Override
        public double calcularValorDiaria () {
        double valorBase = getValorDiariaBase();
        if (capacidadeCarga > 1000) {
            return valorBase + 50.0;
        }
        return valorBase;
    }

    /**
     * Monta uma descrição com o modelo e a capacidade de carga da van.
     * @return descrição da van
     */
    @Override
    public String getDescricao () {
        return "Van " + getModelo() + " ( Capacidade: " + capacidadeCarga + " kg)";
    }


}
