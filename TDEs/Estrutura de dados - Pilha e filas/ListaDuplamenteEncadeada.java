public class ListaDuplamenteEncadeada<T> {

    private class No<T> {
        T dado;
        No<T> anterior;
        No<T> proximo;

        No(T dado) {
            this.dado = dado;
            this.anterior = null;
            this.proximo = null;
        }
    }

    private No<T> cabeca;
    private No<T> cauda;
    private int tamanho;

    public ListaDuplamenteEncadeada() {
        this.cabeca = null;
        this.cauda = null;
        this.tamanho = 0;
    }

    public void adicionarNoInicio(T dado) {
        No<T> novo = new No<>(dado);
        if (estaVazia()) {
            cabeca = novo;
            cauda = novo;
        } else {
            novo.proximo = cabeca;
            cabeca.anterior = novo;
            cabeca = novo;
        }
        tamanho++;
    }

    public void adicionarNoFim(T dado) {
        No<T> novo = new No<>(dado);
        if (estaVazia()) {
            cabeca = novo;
            cauda = novo;
        } else {
            novo.anterior = cauda;
            cauda.proximo = novo;
            cauda = novo;
        }
        tamanho++;
    }

    public void adicionarNaPosicao(int posicao, T dado) {
        if (posicao < 0 || posicao > tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
        }
        if (posicao == 0) {
            adicionarNoInicio(dado);
            return;
        }
        if (posicao == tamanho) {
            adicionarNoFim(dado);
            return;
        }
        No<T> novo = new No<>(dado);
        No<T> atual = obterNo(posicao);
        No<T> anteriorAoAtual = atual.anterior;
        anteriorAoAtual.proximo = novo;
        novo.anterior = anteriorAoAtual;
        novo.proximo = atual;
        atual.anterior = novo;
        tamanho++;
    }

    public T removerDoInicio() {
        if (estaVazia()) {
            throw new IllegalStateException("Lista vazia.");
        }
        T dado = cabeca.dado;
        if (tamanho == 1) {
            cabeca = null;
            cauda = null;
        } else {
            cabeca = cabeca.proximo;
            cabeca.anterior = null;
        }
        tamanho--;
        return dado;
    }

    public T removerDoFim() {
        if (estaVazia()) {
            throw new IllegalStateException("Lista vazia.");
        }
        T dado = cauda.dado;
        if (tamanho == 1) {
            cabeca = null;
            cauda = null;
        } else {
            cauda = cauda.anterior;
            cauda.proximo = null;
        }
        tamanho--;
        return dado;
    }

    public T removerDaPosicao(int posicao) {
        validarPosicao(posicao);
        if (posicao == 0) {
            return removerDoInicio();
        }
        if (posicao == tamanho - 1) {
            return removerDoFim();
        }
        No<T> atual = obterNo(posicao);
        atual.anterior.proximo = atual.proximo;
        atual.proximo.anterior = atual.anterior;
        tamanho--;
        return atual.dado;
    }

    public boolean removerPorValor(T valor) {
        No<T> atual = cabeca;
        while (atual != null) {
            if (atual.dado.equals(valor)) {
                if (atual == cabeca) {
                    removerDoInicio();
                } else if (atual == cauda) {
                    removerDoFim();
                } else {
                    atual.anterior.proximo = atual.proximo;
                    atual.proximo.anterior = atual.anterior;
                    tamanho--;
                }
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public boolean buscarValor(T valor) {
        No<T> atual = cabeca;
        while (atual != null) {
            if (atual.dado.equals(valor)) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public int buscarPosicao(T valor) {
        No<T> atual = cabeca;
        int posicao = 0;
        while (atual != null) {
            if (atual.dado.equals(valor)) {
                return posicao;
            }
            atual = atual.proximo;
            posicao++;
        }
        return -1;
    }

    public void atualizar(int posicao, T novoDado) {
        validarPosicao(posicao);
        obterNo(posicao).dado = novoDado;
    }

    public int tamanho() {
        return tamanho;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public void limpar() {
        cabeca = null;
        cauda = null;
        tamanho = 0;
    }

    public void exibir() {
        if (estaVazia()) {
            System.out.println("Lista vazia.");
            return;
        }
        StringBuilder sb = new StringBuilder("null <-> ");
        No<T> atual = cabeca;
        while (atual != null) {
            sb.append("[").append(atual.dado).append("]");
            if (atual.proximo != null) {
                sb.append(" <-> ");
            }
            atual = atual.proximo;
        }
        sb.append(" <-> null");
        System.out.println(sb);
    }

    private No<T> obterNo(int posicao) {
        No<T> atual;
        if (posicao < tamanho / 2) {
            atual = cabeca;
            for (int i = 0; i < posicao; i++) {
                atual = atual.proximo;
            }
        } else {
            atual = cauda;
            for (int i = tamanho - 1; i > posicao; i--) {
                atual = atual.anterior;
            }
        }
        return atual;
    }

    private void validarPosicao(int posicao) {
        if (posicao < 0 || posicao >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
        }
    }

    public static void main(String[] args) {
        ListaDuplamenteEncadeada<Integer> lista = new ListaDuplamenteEncadeada<>();

        System.out.println("=== Inserções ===");
        lista.adicionarNoFim(10);
        lista.adicionarNoFim(20);
        lista.adicionarNoFim(30);
        lista.adicionarNoInicio(5);
        lista.adicionarNaPosicao(2, 15);
        lista.exibir();

        System.out.println("\n=== Buscas ===");
        System.out.println("Contém 15? " + lista.buscarValor(15));
        System.out.println("Posição do 20: " + lista.buscarPosicao(20));
        System.out.println("Contém 99? " + lista.buscarValor(99));

        System.out.println("\n=== Atualização ===");
        lista.atualizar(2, 99);
        lista.exibir();

        System.out.println("\n=== Remoções ===");
        System.out.println("Removido do início: " + lista.removerDoInicio());
        System.out.println("Removido do fim: " + lista.removerDoFim());
        System.out.println("Removido da posição 1: " + lista.removerDaPosicao(1));
        lista.exibir();

        System.out.println("\nRemover por valor 10: " + lista.removerPorValor(10));
        lista.exibir();

        System.out.println("\n=== Auxiliares ===");
        System.out.println("Tamanho: " + lista.tamanho());
        System.out.println("Está vazia? " + lista.estaVazia());
        lista.limpar();
        System.out.println("Após limpar — está vazia? " + lista.estaVazia());
        lista.exibir();
    }
}
