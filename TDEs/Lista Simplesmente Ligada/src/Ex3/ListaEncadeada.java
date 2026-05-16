package Ex3;


class Node<T> {
    T valor;
    Node<T> proximo;

    public Node(T valor) {
        this.valor = valor;
        this.proximo = null;
    }
}

public class ListaEncadeada<T> {
    private Node<T> inicio;
    private int tamanho;

    public ListaEncadeada() {
        this.inicio = null;
        this.tamanho = 0;
    }


    public void adicionarNoInicio(T valor) {
        Node<T> novoNo = new Node<>(valor);
        novoNo.proximo = inicio;
        inicio = novoNo;
        tamanho++;
    }

    public void adicionarNoFim(T valor) {
        if (estaVazia()) {
            adicionarNoInicio(valor);
            return;
        }
        Node<T> novoNo = new Node<>(valor);
        Node<T> atual = inicio;
        while (atual.proximo != null) {
            atual = atual.proximo;
        }
        atual.proximo = novoNo;
        tamanho++;
    }

    public void adicionarNaPosicao(int posicao, T valor) {
        if (posicao < 0 || posicao > tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida.");
        }
        if (posicao == 0) {
            adicionarNoInicio(valor);
            return;
        }
        Node<T> novoNo = new Node<>(valor);
        Node<T> atual = inicio;
        for (int i = 0; i < posicao - 1; i++) {
            atual = atual.proximo;
        }
        novoNo.proximo = atual.proximo;
        atual.proximo = novoNo;
        tamanho++;
    }


    public T removerDoInicio() {
        if (estaVazia()) return null;
        T valorRemovido = inicio.valor;
        inicio = inicio.proximo;
        tamanho--;
        return valorRemovido;
    }

    public T removerDoFim() {
        if (estaVazia()) return null;
        if (inicio.proximo == null) {
            return removerDoInicio();
        }
        Node<T> atual = inicio;
        while (atual.proximo.proximo != null) {
            atual = atual.proximo;
        }
        T valorRemovido = atual.proximo.valor;
        atual.proximo = null;
        tamanho--;
        return valorRemovido;
    }

    public T removerDaPosicao(int posicao) {
        if (posicao < 0 || posicao >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida.");
        }
        if (posicao == 0) {
            return removerDoInicio();
        }
        Node<T> atual = inicio;
        for (int i = 0; i < posicao - 1; i++) {
            atual = atual.proximo;
        }
        T valorRemovido = atual.proximo.valor;
        atual.proximo = atual.proximo.proximo;
        tamanho--;
        return valorRemovido;
    }

    public T removerPorValor(T elemento) {
        if (estaVazia()) return null;

        if (inicio.valor.equals(elemento)) {
            return removerDoInicio();
        }

        Node<T> atual = inicio;
        while (atual.proximo != null && !atual.proximo.valor.equals(elemento)) {
            atual = atual.proximo;
        }

        if (atual.proximo != null) {
            T valorRemovido = atual.proximo.valor;
            atual.proximo = atual.proximo.proximo;
            tamanho--;
            return valorRemovido;
        }

        return null;
    }


    public T buscar(int posicao) {
        if (posicao < 0 || posicao >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida.");
        }
        Node<T> atual = inicio;
        for (int i = 0; i < posicao; i++) {
            atual = atual.proximo;
        }
        return atual.valor;
    }

    public int buscarPosicao(T valor) {
        Node<T> atual = inicio;
        int posicao = 0;
        while (atual != null) {
            if (atual.valor.equals(valor)) {
                return posicao;
            }
            atual = atual.proximo;
            posicao++;
        }
        return -1;
    }


    public void atualizar(int posicao, T novoValor) {
        if (posicao < 0 || posicao >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida.");
        }
        Node<T> atual = inicio;
        for (int i = 0; i < posicao; i++) {
            atual = atual.proximo;
        }
        atual.valor = novoValor;
    }

    public int tamanho() {
        return tamanho;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public void limpar() {
        inicio = null;
        tamanho = 0;
    }

    public void exibir() {
        if (estaVazia()) {
            System.out.println("Lista vazia.");
            return;
        }
        Node<T> atual = inicio;
        System.out.print("Lista: [ ");
        while (atual != null) {
            System.out.print(atual.valor + (atual.proximo != null ? " -> " : ""));
            atual = atual.proximo;
        }
        System.out.println(" ]");
    }
}