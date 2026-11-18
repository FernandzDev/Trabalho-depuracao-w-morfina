# Justificativas — Parte 1 (Depuração)

Preencha uma seção para cada um dos 11 métodos da classe `Locadora`. Para cada um,
diga se ele tinha um bug ou não, e então explique o que estava
errado e por que a sua correção resolve o problema (ou explique por que
decidiu que o método já estava correto).
Se for fazer em dupla, cada aluno coloca aqui nesse arquivo quais respondeu.

## 1. contarCarrosDisponiveis

**Tinha bug?** Não.

**Explicação:** O método já estava correto. O laço vai de `i = 0` até `i < carros.length`, percorrendo todos os
elementos, e incrementa o contador somente quando `isDisponivel()` é `true`.

## 2. calcularReceitaTotalFrota

**Tinha bug?** sim, tinha um bug lógico, não travava mas devolvia um valor errado.

**Explicação:** O laço começava em `i = 1`, então o primeiro carro do array nunca entrava na soma.
Com os carros de diária 100, 220, 90 e 250 o método devolvia 560 em vez de 660. poderiamos trocar getValorDiariaBase()
por calcularValorDiaria() dentro do laço, mas somarDiarias() já faz exatamente isso.

## 3. calcularMediaIdadeClientes

**Tinha bug?** sim, tinha um bug de execução

**Explicação:** Com um array vazio, `soma / clientes.length` vira `0 / 0` em divisão de inteiros e lança
`ArithmeticException: / by zero`. Adicionei uma verificação no início: se o array for `null` ou vazio,
devolve 0. Além disso, o método retornava `int`, então a média estava vido inteira.ex (idades 20 e 21 davam 20 em vez
de 20,5). Mudei o retorno para `double` e faço a divisão com
`(double) soma / clientes.length`.

## 4. buscarClienteMaisVelho

**Tinha bug?** Não

**Explicação:** O método já estava correto. A variável `maisVelho` começa em `null`; no primeiro elemento a condição
`maisVelho == null` é verdadeira e ele vira o candidato, e depois só é substituído quando aparece alguém com idade
estritamente maior (`>`), o que também faz o primeiro vencer em caso de empate.

## 5. buscarVeiculoPorPlaca

**Tinha bug?** Sim, bug de execução

**Explicação:** Se nenhuma placa batia, `encontrado` continuava `null` e a linha final
`encontrado.getModelo()` lançava `NullPointerException`. Corrigi devolvendo o resultado dentro do laço assim que a placa é encontrada e, depois do
laço, devolvendo a mensagem "Carro de placa X nao encontrado". Como efeito colateral positivo, o método agora para
no primeiro carro encontrado em vez de continuar varrendo e sobrescrever o resultado com um possível duplicado.

## 6. calcularMultaAtraso

**Tinha bug?** Sim, bug lógico

**Explicação:** O laço usava `i <= diasAtraso`, executando `diasAtraso + 1` vezes: 3 dias de atraso cobravam
RS160,00 em vez de RS120,00, e 0 dias de atraso cobravam R$40,00 em vez de zero. Troquei por `diasAtraso * multaPorDia`
e, para `diasAtraso <= 0`, devolvo 0 (sem multa e sem valor negativo).

## 7. processarLocacaoCompleta

**Tinha bug?** Sim, bug lógico 

**Explicação:** A regra diz que o desconto de 15% só vale quando o cliente é apto E o aluguel é de 7 dias ou mais,
mas o método `aplicarDescontosEEncargos` usava `||`. Assim, qualquer cliente apto ganhava desconto mesmo em
1 dia, e qualquer cliente (até não apto) ganhava desconto com 7 dias ou mais. Troquei `||` por `&&`. Também
acrescentei, em `validarLocacao`, uma verificação de `dias <= 0` (lançando `IllegalStateException`), para
não calcular valores negativos ou zerados; as demais etapas (`calcularValorBruto`) já estavam corretas

## 8. calcularDiariaComCategoria

**Tinha bug?** Sim, bug de execução

**Explicação:** O método usava `categoria` direto como índice de `TABELA_PRECOS`, que só tem as posições 0, 1 e 2. Qualquer outro
valor (3, 5, -1…) lançava `ArrayIndexOutOfBoundsException`, uma exceção interna que não explica o problema ao usuário.
Passei a validar a categoria antes de acessar o array e a lançar `IllegalArgumentException` com uma mensagem clara
("Categoria invalida: X (use um valor de 0 a 2)"). Também rejeto `dias` negativo. Para categorias válidas (como a
categoria 1 por 5 dias = R$650,00) o calculo continua o mesmo.

## 9. gerarResumoFrota

**Tinha bug?** Não

**Explicação:** A lógica do método já estava correta. A assinatura foi adaptada para receber um vetor do tipo
`Alugavel[]`, permitindo resumir uma frota com diferentes tipos de veículos, como `Carro`, `Moto` e `Van`, sem depender
de uma classe concreta.

## 10. calcularDescontoEscalonado

**Tinha bug?** Sim, bug de execução

**Explicação:** O laço usava `i <= LIMIARES_DIAS.length`; como o array tem 3 posições (índices 0 a 2), na última
iteração `i = 3` acessava `LIMIARES_DIAS[3]` e lançava `ArrayIndexOutOfBoundsException` sempre, para qualquer
valor de dias. Troquei por `i < LIMIARES_DIAS.length`. A lógica restante estava totalmente correta.


## 11. clienteElegivelDescontoFidelidade

**Tinha bug?** Sim, bug lógico

**Explicação:** A regra pede "pelo menos 25 anos", ou seja, a partir de 25 é valido, mas o código usava `> 25`, que exclui quem tem
exatamente 25. Troquei por `>= 25`. A outra condição, `totalLocacoesAnteriores >= 3`, já estava correta.


# Justificativas - Parte 2

Escolhemos criar `Alugavel` como interface porque ela define somente o comportamento que todo item alugável deve
oferecer: calcular a diária, informar sua disponibilidade e fornecer uma descrição. Assim, os métodos da locadora podem
trabalhar com tipos diferentes por meio do mesmo contrato, sem precisar conhecer a classe concreta de cada item.

Também criamos a classe abstrata `Veiculo`, que implementa `Alugavel` e concentra os dados e comportamentos comuns da
frota, como placa, modelo, valor da diária-base e disponibilidade. `Carro`, `Moto` e `Van` herdam dessa classe. `Moto`
usa a cilindrada e `Van` usa a capacidade de carga para acrescentar valores específicos ao cálculo da diária.

O método `somarDiarias(Alugavel[] itens)` demonstra o polimorfismo exigido na extensão: ele percorre um vetor que pode
misturar carros, motos e vans e soma o resultado de `calcularValorDiaria()` de cada item sem testar seu tipo concreto.
O método `gerarResumoFrota(Alugavel[] veiculos)` usa a mesma abstração para calcular a quantidade de itens disponíveis
e a média das diárias.

Além das classes relacionadas à frota, foram criadas `Reserva`, `Pagamento` e `Vistoria`. `Reserva` associa um cliente a
um item `Alugavel` por uma quantidade válida de dias e calcula o subtotal; `Pagamento` registra o valor cobrado e se ele
foi quitado; e `Vistoria` registra a existência e a descrição de uma avaria no veículo.
