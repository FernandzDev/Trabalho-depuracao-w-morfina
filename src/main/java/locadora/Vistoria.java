package locadora;

public class Vistoria {
    private final Veiculo veiculo;
    private boolean temAvaria;
    private String observacao;

    public Vistoria(Veiculo veiculo){
        this.veiculo = veiculo;
        temAvaria = false;
        this.observacao = "";
    }

    public String getObservacao() {
        return observacao;
    }

    public boolean temAvaria() {
        return temAvaria;
    }

    public void registrarAvaria(String observacao){
        temAvaria = true;
        this.observacao = observacao;
    }
}
