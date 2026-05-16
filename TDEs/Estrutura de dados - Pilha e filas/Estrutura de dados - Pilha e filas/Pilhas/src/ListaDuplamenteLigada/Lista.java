package ListaDuplamenteLigada;

public class Lista<T> {
    No<T> primeiro;
    No<T> ultimo;
    int tamanho;

    public Lista() {
        this.primeiro = null;
        this.ultimo = null;
        this.tamanho = 0;
    }


    public boolean estaVazia(){
        if (tamanho == 0){
            return true;
        }
        return false;
    }

    public int tamanho(){
        return tamanho;
    }

    public void limpar(){
        this.primeiro = null;
        this.ultimo = null;
        this.tamanho = 0;
    }

    public void exibir(){
        No<T> atual = this.primeiro;
            while (atual != null){
                System.out.println("Obejto atual: " + atual.dado);
                    atual = atual.proximo;
            }
    }
}
