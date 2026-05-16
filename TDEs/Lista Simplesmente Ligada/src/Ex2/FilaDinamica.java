package Ex2;

public class FilaDinamica<T> {


    private class NoFila {
        T valor;
        NoFila proximo;

        NoFila(T valor) {
            this.valor = valor;
        }
    }

    private NoFila inicio;
    private NoFila fim;
    private int tamanho;

    public FilaDinamica() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }


    public void enqueue(T valor) {
        NoFila novoNo = new NoFila(valor);
        if (estaVazia()) {
            inicio = novoNo;
        } else {
            fim.proximo = novoNo;
        }
        fim = novoNo;
        tamanho++;
    }


    public T dequeue() {
        if (estaVazia()) {
            throw new RuntimeException("A fila está vazia.");
        }
        T valorRemovido = inicio.valor;
        inicio = inicio.proximo;

        if (inicio == null) {
            fim = null;
        }

        tamanho--;
        return valorRemovido;
    }


    public T peek() {
        if (estaVazia()) {
            throw new RuntimeException("A fila está vazia.");
        }
        return inicio.valor;
    }

    public boolean estaVazia() {
        return inicio == null;
    }

    public int tamanho() {
        return tamanho;
    }
}