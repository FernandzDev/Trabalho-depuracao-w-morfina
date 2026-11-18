package locadora;

/**
 * Registra a observação e a presença de avaria em um veículo vistoriado.
 */
public class Vistoria {
    private final Veiculo veiculo;
    private boolean temAvaria;
    private String observacao;

    /**
     * Cria uma vistoria sem avaria registrada e com a observação vazia.
     * @param veiculo veiculo associado à vistoria
     */
    public Vistoria(Veiculo veiculo){
        this.veiculo = veiculo;
        temAvaria = false;
        this.observacao = "";
    }

    /**
     * Consulta a observação registrada na vistoria
     * @return observação atual ou uma string vazia se nada foi registrado.
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * Consulta se uma avaria foi registrada nesta vistoria.
     * @return {@code true} se há avaria registrada ou
     * {@code false} se nenhuma avaria foi registrada
     */
    public boolean temAvaria() {
        return temAvaria;
    }

    /**
     * Marca a vistoria como tendo avaria e substitui a observação anterior.
     * @param observacao descrição da avaria encontrada.
     */
    public void registrarAvaria(String observacao){
        temAvaria = true;
        this.observacao = observacao;
    }
}
