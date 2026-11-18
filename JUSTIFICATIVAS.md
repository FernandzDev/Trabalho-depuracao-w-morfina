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
Com os carros de diária 100, 220, 90 e 250 o método devolvia 560 em vez de 660. Troquei para `i = 0`, de modo que todos os
elementos sejam somados.

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

## 5. buscarCarroPorPlaca

**Tinha bug?**

**Explicação:**

## 6. calcularMultaAtraso

**Tinha bug?**

**Explicação:**

## 7. processarLocacaoCompleta

**Tinha bug?**

**Explicação:**

## 8. calcularDiariaComCategoria

**Tinha bug?**

**Explicação:**

## 9. gerarResumoFrota

**Tinha bug?**

**Explicação:**

## 10. calcularDescontoEscalonado

**Tinha bug?**

**Explicação:**

## 11. clienteElegivelDescontoFidelidade

**Tinha bug?**

**Explicação:**


# Justificativas - Parte 2

Veiculo: Criei a classe mãe veiculo, assim consigo fazer as outras classes herdarem os parametros dela
e otimizar parte do sistema.