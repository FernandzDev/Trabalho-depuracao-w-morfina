package locadora;

public class Carro extends Veiculo {

    /**
     * Cria um carro novo, que já começa disponível para locação
     *
     * @param placa (placa do carro)
     * @param modelo (modelo do carro)
     * @param valorDiaria (valor da diaria do carro)
     *
     */
    public Carro (String placa, String modelo, double valorDiaria) {
        super(placa, modelo, valorDiaria);
}
    @Override
    public String getDescricao() {
        return "Carro "+ getModelo() + " (Placa: "+ getPlaca() + ")";
    }

    @Override
    public double calcularValorDiaria() {
        return getValorDiariaBase();
    }

    public double getValorDiaria() {
        return getValorDiariaBase();
    }

}
