package locadora;

/**
 * Cada moto é uma extensão de veiculo, possui um modelo, valor de diaria e indicador de disponibilidade.
 * Além disso possui a variavel "cilindrada" para indicar qual é a cilindrada da moto
 */
public class Moto extends Veiculo {

    private final int cilindrada;

    /**
     * Cria uma moto nova, que já começa como disponível e indica que a "cilindrada" pertence a esse objeto.
     * @param placa (placa da moto)
     * @param modelo (modelo da moto)
     * @param valorDiaria (valor da diaria da moto)
     * @param cilindrada (indica qual é a cilindrada da moto)
     */
  public Moto (String placa, String modelo, double valorDiaria, int cilindrada ){
      super(placa, modelo, valorDiaria);
      this.cilindrada = cilindrada;
  }

  public int getCilindrada(){
      return cilindrada;
  }

    @Override
    public double calcularValorDiaria() {
      double valorBase = getValorDiariaBase();

      if (cilindrada > 300){
          return valorBase + 30.0;
      }

      return valorBase;
    }

    @Override
    public String getDescricao() {
        return "Moto " + getModelo() + " (" + cilindrada + " cc)";
    }

}
