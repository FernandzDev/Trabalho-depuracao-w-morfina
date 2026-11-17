package locadora;

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
    public int getCapacidadeCarga () {
        return capacidadeCarga;
    }

        @Override
        public double calcularValorDiaria () {
        double valorBase = getValorDiariaBase();
        if (capacidadeCarga > 1000) {
            return valorBase + 50.0;
        }
        return valorBase;
    }

    @Override
    public String getDescricao () {
        return "Van " + getModelo() + " ( Capacidade: " + capacidadeCarga + " kg)";
    }


}
