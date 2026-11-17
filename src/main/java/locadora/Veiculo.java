package locadora;

public abstract class Veiculo implements Alugavel {
    private final String placa;
    private final String modelo;
    private final double valorDiariaBase;
    private boolean disponivel;

    protected Veiculo(String placa, String modelo, double valorDiariaBase) {
        this.placa = placa;
        this.modelo = modelo;
        this.valorDiariaBase = valorDiariaBase;
        this.disponivel = true;
    }

    public String getPlaca() {
        return placa;
    }
    public String getModelo() {
        return modelo;
    }
    public double getValorDiariaBase() {
        return valorDiariaBase;
    }

    @Override
    public boolean isDisponivel(){
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
