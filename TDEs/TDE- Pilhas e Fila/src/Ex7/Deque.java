package Ex7;

public class Deque<T> {
    private T[] elementos;
    private int inicio;
    private int fim;
    private int tamanho;
    private int capacidade;

    @SuppressWarnings("unchecked")
    public Deque(int capacidade) {
        this.capacidade = capacidade;
        this.elementos = (T[]) new Object[capacidade];
        this.inicio = 0;
        this.fim = 0;
        this.tamanho = 0;
    }

    public void inserirNoInicio(T elemento) {
        if (estaCheio()) {
            System.out.println("Erro: Deque cheio. Não é possível inserir no início.");
            return;
        }
        // Calcula o índice anterior de forma circular
        inicio = (inicio - 1 + capacidade) % capacidade;
        elementos[inicio] = elemento;
        tamanho++;
    }

    public void inserirNoFim(T elemento) {
        if (estaCheio()) {
            System.out.println("Erro: Deque cheio. Não é possível inserir no fim.");
            return;
        }
        elementos[fim] = elemento;
        // Calcula o próximo índice de forma circular
        fim = (fim + 1) % capacidade;
        tamanho++;
    }

    public T removerDoInicio() {
        if (estaVazio()) {
            System.out.println("Erro: Deque vazio. Não é possível remover do início.");
            return null;
        }
        T removido = elementos[inicio];
        inicio = (inicio + 1) % capacidade;
        tamanho--;
        return removido;
    }

    public T removerDoFim() {
        if (estaVazio()) {
            System.out.println("Erro: Deque vazio. Não é possível remover do fim.");
            return null;
        }
        // Retrocede o 'fim' de forma circular e remove
        fim = (fim - 1 + capacidade) % capacidade;
        T removido = elementos[fim];
        tamanho--;
        return removido;
    }

    public T consultarInicio() {
        if (estaVazio()) return null;
        return elementos[inicio];
    }

    public T consultarFim() {
        if (estaVazio()) return null;
        int ultimoIndice = (fim - 1 + capacidade) % capacidade;
        return elementos[ultimoIndice];
    }

    public boolean estaVazio() {
        return tamanho == 0;
    }

    public boolean estaCheio() {
        return tamanho == capacidade;
    }

    public int tamanho() {
        return tamanho;
    }

    public void limpar() {
        inicio = 0;
        fim = 0;
        tamanho = 0;
        // Não é estritamente necessário anular os objetos em Java se os ponteiros
        // reescreverem ou ignorarem o "lixo" depois, mas o tamanho=0 garante o estado.
    }

    // Exemplo de uso
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>(4);

        deque.inserirNoFim("B");
        deque.inserirNoFim("C");
        deque.inserirNoInicio("A");
        deque.inserirNoFim("D");

        System.out.println("Início atual: " + deque.consultarInicio()); // A
        System.out.println("Fim atual: " + deque.consultarFim());       // D
        System.out.println("Tamanho: " + deque.tamanho());              // 4

        System.out.println("Removido do início: " + deque.removerDoInicio()); // A
        System.out.println("Removido do fim: " + deque.removerDoFim());       // D

        System.out.println("Fim após remoção: " + deque.consultarFim());      // C

        deque.limpar();
        System.out.println("Está vazio após limpar? " + deque.estaVazio());   // true
    }
}