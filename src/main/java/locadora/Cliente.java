package locadora;

/**
 * representa as informações do cliente da locadora.
 *
 * as informações contêm o nome, idade e anos habilitado do cliente, para assim saber se ele vai poder alugar o veiculo
 */
public class Cliente {

    private String nome;
    private int idade;
    private int anosHabilitado;

    /**
     * Cria um cliente
     *
      * @param nome nome do cliente
     * @param idade idade do cliente
     * @param anosHabilitado anos que o cliente esta habilitado
     */
    public Cliente(String nome, int idade, int anosHabilitado) {
        this.nome = nome;
        this.idade = idade;
        this.anosHabilitado = anosHabilitado;
    }

    /**
     * Retorna o nome do cliente
     * @return nome do cliente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a idade do cliente
     * @return idade em anos
     */
    public int getIdade() {
        return idade;
    }

    /**
     * Retorna o tempo de habilitação do cliente.
     * @return quantidade de anos de habilitação
     */
    public int getAnosHabilitado() {
        return anosHabilitado;
    }

    /**
     * Verifica se o cliente está apto a alugar um veículo. O cliente é apto
     * quando tem pelo menos 21 anos e pelo menos 2 anos de
     * habilitação.
     *
     * @return true se o cliente for apto; false caso contrário
     */
    public boolean isApto() {
        return idade >= 21 && anosHabilitado >= 2;
    }
}
