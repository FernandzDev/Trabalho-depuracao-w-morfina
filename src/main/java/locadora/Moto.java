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

    /**
     * Retorna a cilindrada da moto
     * @return cilindrada em centímetros cubicos (cc)
     */
  public int getCilindrada(){
      return cilindrada;
  }

    /**
     * Calcula a diária da moto com adicional de R$ 30,00 acima de 300 cc.
     * @return diária final da moto, em reais
     */
    @Override
    public double calcularValorDiaria() {
      double valorBase = getValorDiariaBase();

      if (cilindrada > 300){
          return valorBase + 30.0;
      }

      return valorBase;
    }

    /**
     * Monta uma descrição com o modelo e a cilindrada da moto
     * @return descrição da moto
     */
    @Override
    public String getDescricao() {
        return "Moto " + getModelo() + " (" + cilindrada + " cc)";
    }

}
