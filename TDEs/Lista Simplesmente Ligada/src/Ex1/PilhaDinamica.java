package Ex1;

public class PilhaDinamica<T> {


    private class NoPilha {
        T valor;
        NoPilha proximo;

        NoPilha(T valor) {
            this.valor = valor;
        }
    }

    private NoPilha topo;
    private int tamanho;

    public PilhaDinamica() {
        this.topo = null;
        this.tamanho = 0;
    }


    public void push(T valor) {
        NoPilha novoNo = new NoPilha(valor);
        novoNo.proximo = topo;
        topo = novoNo;
        tamanho++;
    }


    public T pop() {
        if (estaVazia()) {
            throw new RuntimeException("A pilha está vazia.");
        }
        T valorRemovido = topo.valor;
        topo = topo.proximo;
        tamanho--;
        return valorRemovido;
    }


    public T peek() {
        if (estaVazia()) {
            throw new RuntimeException("A pilha está vazia.");
        }
        return topo.valor;
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public int tamanho() {
        return tamanho;
    }
}