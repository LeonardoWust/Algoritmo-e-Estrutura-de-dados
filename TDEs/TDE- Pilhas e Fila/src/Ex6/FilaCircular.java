package Ex6;

public class FilaCircular<T> {
    private T[] elementos;
    private int inicio;
    private int fim;
    private int tamanho;
    private int capacidade;

    @SuppressWarnings("unchecked")
    public FilaCircular(int capacidade) {
        this.capacidade = capacidade;
        this.elementos = (T[]) new Object[capacidade];
        this.inicio = 0;
        this.fim = 0;
        this.tamanho = 0;
    }

    public void enfileirar(T elemento) {
        if (estaCheia()) {
            System.out.println("Erro: Não é possível inserir. A fila está cheia.");
            return;
        }

        elementos[fim] = elemento;
        fim = (fim + 1) % capacidade;
        tamanho++;
    }

    public T desenfileirar() {
        if (estaVazia()) {
            System.out.println("Erro: Não é possível remover. A fila está vazia.");
            return null;
        }

        T elementoRemovido = elementos[inicio];

        inicio = (inicio + 1) % capacidade;
        tamanho--;
        return elementoRemovido;
    }

    public T espiar() {
        if (estaVazia()) {
            System.out.println("A fila está vazia.");
            return null;
        }
        return elementos[inicio];
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public boolean estaCheia() {
        return tamanho == capacidade;
    }


    public static void main(String[] args) {
        FilaCircular<Integer> fila = new FilaCircular<>(3);
        fila.enfileirar(10);
        fila.enfileirar(20);
        fila.enfileirar(30);
        fila.enfileirar(40);

        System.out.println("Removendo: " + fila.desenfileirar());

        fila.enfileirar(40);

        System.out.println("Espiando o próximo: " + fila.espiar());
        System.out.println("Removendo: " + fila.desenfileirar());
        System.out.println("Removendo: " + fila.desenfileirar());
        System.out.println("Removendo: " + fila.desenfileirar());
    }
}
