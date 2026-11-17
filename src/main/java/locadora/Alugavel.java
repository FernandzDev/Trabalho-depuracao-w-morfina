package locadora;

/** Define o comportamento comum aos veiculos que podem ser alugados. */
public interface Alugavel {
    /**
     * Calcula a diária conforme as regras do veiculo
     * @return o preço final de uma diária.
     */
    double calcularValorDiaria();

    /**
     * Consulta a disponibilidade do veiculo.
     * @return true se o item estiver disponível para locação
     */
    boolean isDisponivel();

    /**
     * Descreve o veiculo para exibição ao usuário.
     * @return uma descrição do veiculo
     */
    String getDescricao();
}
