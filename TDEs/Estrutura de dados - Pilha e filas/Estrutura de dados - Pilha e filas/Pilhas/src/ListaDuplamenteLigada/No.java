package ListaDuplamenteLigada;

public class No<T> {
    No<T> proximo;
    No<T> anterior;
    T dado;

    public No(No<T> proximo, No<T> anterior, T valorInicial) {
        this.proximo = proximo;
        this.anterior = anterior;
        this.dado = valorInicial;
    }

    public No(T dado) {
        this.dado = dado;
        this.proximo = null;
        this.anterior = null;
    }
}
