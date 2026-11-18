package locadora;

/**
 * Representa um carro cuja diária final é igual à diária-base.
 */
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

    /**
     * Monta uma descrição com o modelo e a placa do carro.
     * @return descrição do carro
     */
    @Override
    public String getDescricao() {
        return "Carro "+ getModelo() + " (Placa: "+ getPlaca() + ")";
    }

    /**
     * Calcula a diaria do carro sem aplicar adicionais.
     * @return diária-base do carro, em reais.
     */
    @Override
    public double calcularValorDiaria() {
        return getValorDiariaBase();
    }

    /**
     * Retorna o valor da diária do carro.
     * @return diária do carro, em reais.
     */
    public double getValorDiaria() {
        return getValorDiariaBase();
    }

}
