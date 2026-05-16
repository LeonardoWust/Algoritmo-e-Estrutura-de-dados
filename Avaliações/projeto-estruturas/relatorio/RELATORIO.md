# Relatório Técnico — Estruturas de Dados
## Pilhas, Filas e Listas Ligadas

---

## 4.1 Identificação

**Aluno:** _(seu nome aqui)_  
**Disciplina:** Análise e Desenvolvimento de Sistemas — Estruturas de Dados  
**Data:** Junho de 2025

---

## 4.2 Introdução

A implementação manual de estruturas de dados é uma habilidade fundamental em computação. Ao construir pilhas, filas e listas ligadas sem o auxílio de bibliotecas prontas, o programador passa a compreender de verdade o que acontece por baixo dos panos: como a memória é alocada para cada nó no momento em que ele é criado, como os **ponteiros** encadeiam esses nós em sequência, e qual o custo real de cada operação (inserção, remoção, busca). Essa compreensão é especialmente crítica no gerenciamento de memória — ao remover um nó, é necessário desconectar manualmente suas referências para evitar nós "perdidos" no heap; em estruturas circulares, o descuido com ponteiros pode gerar loops infinitos que travam o programa. Programar sem atalhos força o desenvolvedor a raciocinar sobre cada ligação entre nós, tornando-o capaz de depurar, otimizar e adaptar qualquer estrutura às necessidades reais de um sistema.

---

## 4.3 Implementação

### Exercício 1 — Pilha e Fila (Lista Simplesmente Ligada) | `Cafeteria.java`

#### Estrutura do Nó

```java
static class No {
    int id;
    String descricao;
    No proximo;   // aponta para o próximo nó; null = fim da estrutura
}
```

Cada nó carrega os dados do pedido e um ponteiro `proximo`. Simples e suficiente para as duas estruturas.

#### Fila (`FilaPedidos`) — FIFO

A fila mantém dois ponteiros: `cabeca` (próximo a ser atendido) e `cauda` (onde o novo pedido entra).

- **`enqueue`**: cria novo nó → `cauda.proximo = novo` → `cauda = novo`
- **`dequeue`**: salva `cabeca` → `cabeca = cabeca.proximo` → retorna o salvo

**Caso crítico — fila vazia após remoção:** quando o `dequeue` esvazia a fila, `cabeca` passa a `null`. O código trata isso explicitamente: `if (cabeca == null) { cauda = null; }`, garantindo que a cauda não fique apontando para um nó já removido.

#### Pilha (`PilhaCancelados`) — LIFO

A pilha mantém um único ponteiro `topo`.

- **`push`**: `novo.proximo = topo` → `topo = novo`
- **`pop`**: salva `topo` → `topo = topo.proximo` → retorna o salvo

**Caso crítico — pilha vazia:** `pop` verifica `if (topo == null)` antes de qualquer acesso, evitando `NullPointerException`.

---

### Exercício 2 — Lista Duplamente Ligada | `Playlist.java`

#### Estrutura do Nó

```java
static class NoMusica {
    String titulo, artista, album;
    int duracao;
    NoMusica anterior;  // aponta para o nó anterior (null na cabeça)
    NoMusica proximo;   // aponta para o próximo nó (null na cauda)
}
```

O segundo ponteiro (`anterior`) é o que diferencia a lista duplamente ligada: ele permite navegar nos dois sentidos sem precisar percorrer toda a lista do início.

#### Operações Principais

- **Inserção no início**: `novo.proximo = cabeca` → `cabeca.anterior = novo` → `cabeca = novo`
- **Inserção no fim**: `cauda.proximo = novo` → `novo.anterior = cauda` → `cauda = novo`
- **Inserção por posição**: percorre até a posição anterior, ajusta quatro ponteiros (antes e depois do novo nó)
- **Remoção (`removerNo`)**: reconecta `anterior.proximo` e `proximo.anterior` para "saltar" o nó removido. Os dois casos extremos (remover cabeça ou cauda) são tratados separadamente.

**Caso crítico — remoção da música atual:** se o ponteiro `atual` (música em reprodução) aponta para o nó que será removido, o código redireciona `atual` para `no.proximo` (ou `no.anterior` se for o último) antes de desconectar o nó, evitando ponteiro inválido.

**Ordenação (Bubble Sort adaptado):** a ordenação troca apenas os *dados* entre nós adjacentes (não os nós em si), preservando a integridade dos ponteiros estruturais.

---

### Exercício 3 — Lista Circular Simplesmente Ligada | `CarrosselAnuncios.java`

#### Estrutura do Nó

```java
static class NoAnuncio {
    int id;
    String empresa, descricao;
    int contadorExibicoes;
    NoAnuncio proximo;  // NUNCA null em lista não-vazia — fecha o ciclo!
}
```

#### Estratégia de Implementação

A lista mantém um ponteiro para a **cauda** (último nó). Isso permite que:
- O **primeiro** nó seja acessado em O(1) via `cauda.proximo`
- Inserção no **fim** seja O(1) (sem percorrer tudo)
- Inserção no **início** também seja O(1)

#### Invariante do Ciclo

Em toda operação, a propriedade fundamental deve ser mantida: `cauda.proximo == primeiroNo`. Qualquer remoção ou inserção precisa restabelecer essa invariante.

#### Casos Críticos Tratados

| Situação | Tratamento |
|---|---|
| **Inserir em lista vazia** | `novo.proximo = novo` — o nó aponta para si mesmo |
| **Remover único elemento** | `cauda = null`, `anuncioAtual = null` — lista volta ao estado vazio |
| **Remover o `anuncioAtual`** | `anuncioAtual = alvo.proximo` antes de desconectar o nó |
| **Listar sem loop infinito** | `do { ... } while (cursor != inicio)` — para ao fechar o ciclo |
| **Remover a cauda** | `cauda = anterior` atualiza o ponteiro após a desconexão |

**Prevenção de loop infinito na listagem:** o algoritmo guarda o nó de início e usa um loop `do-while` que termina exatamente quando `cursor` volta ao ponto de partida — percorre cada nó exatamente uma vez.

---

## 4.4 Evidências de Execução

### Exercício 1 — Cafeteria

**Fluxo testado:** imprimir fila inicial → atender pedido → cancelar pedido → restaurar pedido → adicionar novo pedido → imprimir fila final.

```
[SISTEMA] Carregando pedidos iniciais...
[FILA] Pedido #1 (Café Expresso + Pão de Queijo) adicionado à fila.
[FILA] Pedido #2 (Cappuccino + Croissant) adicionado à fila.
[FILA] Pedido #3 (Suco de Laranja + Tapioca) adicionado à fila.
[FILA] Pedido #4 (Chá Verde + Bolo de Cenoura) adicionado à fila.

=== PEDIDOS PENDENTES ===
  [1] Pedido #1 → Café Expresso + Pão de Queijo
  [2] Pedido #2 → Cappuccino + Croissant
  [3] Pedido #3 → Suco de Laranja + Tapioca
  [4] Pedido #4 → Chá Verde + Bolo de Cenoura
=========================

--- ATENDENDO PEDIDO ---
✓ Pedido #1 (Café Expresso + Pão de Queijo) foi ATENDIDO!

--- CANCELANDO PEDIDO ---
[PILHA] Pedido #2 (Cappuccino + Croissant) adicionado à pilha de cancelados.
✗ Pedido #2 foi CANCELADO e movido para a pilha.

--- RESTAURANDO PEDIDO ---
[FILA] Pedido #2 (Cappuccino + Croissant) adicionado à fila.
↩ Pedido #2 foi RESTAURADO para a fila!

=== PEDIDOS PENDENTES ===
  [1] Pedido #3 → Suco de Laranja + Tapioca
  [2] Pedido #4 → Chá Verde + Bolo de Cenoura
  [3] Pedido #2 → Cappuccino + Croissant   ← restaurado vai ao fim
=========================
```

**Resultado:** as estruturas de fila (FIFO) e pilha (LIFO) operam corretamente e de forma integrada.

---

### Exercício 2 — Playlist

**Fluxo testado:** listar playlist → tocar atual → avançar → ordenar por título → buscar.

```
╔═══════════════════════════════════════════════════════════════════╗
║  PLAYLIST: Minhas Favoritas                                      ║
║  #   TÍTULO                              ARTISTA         DURAÇÃO ║
╠═══════════════════════════════════════════════════════════════════╣
║ ▶ 1  Bohemian Rhapsody                   Queen           5:54   ║
║   2  Hotel California                    Eagles           6:31   ║
║   3  Stairway to Heaven                  Led Zeppelin     8:02   ║
║   4  Smells Like Teen Spirit             Nirvana          5:01   ║
║   5  Imagine                             John Lennon      3:07   ║
╚═══════════════════════════════════════════════════════════════════╝

♪ ═══════════════════════════════════ ♪
  ▶  TOCANDO AGORA
  Título:   Bohemian Rhapsody
  Artista:  Queen
  Álbum:    A Night at the Opera
  Duração:  5:54
♪ ═══════════════════════════════════ ♪

>> Avançando para: "Hotel California" - Eagles
[✓] Playlist ordenada por título.
```

**Resultado:** navegação bidirecional, ordenação e reprodução funcionando corretamente.

---

### Exercício 3 — Carrossel

**Fluxo testado:** listar ciclo → exibir 3× → remover anúncio #2 → exibição automática de 3 anúncios.

```
=== CICLO COMPLETO DO CARROSSEL ===
  [1] #1 | TechStore      | 50% OFF em notebooks!...  | 0x  < ATUAL
  [2] #2 | FoodPark       | Melhor hamburguer...       | 0x
  [3] #3 | Academia FitLife | Primeiro mes GRATIS...   | 0x
  [4] #4 | Banco Digital X | Abra sua conta...         | 0x
  Total: 4 anúncio(s)
  Ciclo fecha: #4 -> #1

[-] Anúncio #2 ("FoodPark") removido do carrossel.

[AUTO] Exibindo 3 anúncios automaticamente...
  --- Exibição 1 de 3 --- → #4 Banco Digital X (1x)
  --- Exibição 2 de 3 --- → #1 TechStore (2x)
  --- Exibição 3 de 3 --- → #3 Academia FitLife (2x)
```

**Resultado:** rotação circular contínua, remoção com reconexão do ciclo e contador de exibições funcionando corretamente.

---

## 5 Conclusão

### Principais Dificuldades e Como Foram Resolvidas

**1. Gerenciar casos extremos de ponteiros**  
A maior dificuldade foi garantir que os ponteiros de `cabeca`, `cauda` e `topo` fossem atualizados corretamente em todos os cenários — especialmente nas remoções que esvaziam a estrutura. A solução foi tratar cada caso extremo com blocos `if` explícitos antes da lógica geral.

**2. Manter o ciclo íntegro na lista circular**  
Qualquer falha em reconectar `cauda.proximo` após uma remoção quebraria o ciclo e causaria um `NullPointerException` ou loop infinito. A solução foi centralizar toda reconexão em um único método de remoção com verificações para cada caso especial (remover a cauda, remover o `anuncioAtual`, remover o único elemento).

**3. Evitar loop infinito na listagem circular**  
O uso ingênuo de `while (cursor != null)` não funcionaria, pois nenhum nó é `null` na lista circular. A solução foi a condição `do { ... } while (cursor != inicio)`, que percorre o ciclo exatamente uma vez.

**4. Ordenação em lista ligada**  
O Bubble Sort em arrays troca posições; em lista ligada, trocar os ponteiros dos nós é complexo. A solução adotada foi trocar apenas os **dados** (título, artista, álbum, duração) entre nós adjacentes, mantendo os ponteiros estruturais intactos — estratégia mais simples e igualmente correta.

### Aprendizado Obtido

Este trabalho tornou concreto o que antes era abstrato. Entender que uma "lista ligada" é, na prática, um conjunto de objetos espalhados na memória conectados por referências — e que o programador é responsável por manter cada uma dessas referências consistente — é uma habilidade que muda a forma de pensar sobre qualquer estrutura de dados, inclusive as que vêm prontas nas bibliotecas padrão. Saber o que acontece por dentro de um `java.util.LinkedList` ou de uma `Stack` torna o código muito mais consciente e menos dependente de "magia" das APIs.
