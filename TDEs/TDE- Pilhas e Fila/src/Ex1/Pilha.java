package Ex1;

public class Pilha {
    private int [] elementos;
    private int tamanho;

    public Pilha(int capacidade){
        this.elementos = new int[capacidade];
        this.tamanho = 0;
    }

    public int tamanho(){
        return this.tamanho;
    }

    public boolean estaVazia(){
        if (tamanho == 0){
            return true;
        }
        return false;
    }

    public boolean estaCheia(){
        return tamanho == this.elementos.length;
    }

    public boolean empilhar(int e){
        if(estaCheia()){
            System.out.println("Ta cheia");
        }else {
            this.elementos[tamanho] = 1;
            tamanho ++;
        }
        return false;
    }

    public int desempilhar(){
        if (!estaVazia()){
            this.tamanho--;
            return this.elementos[tamanho];
        }
        return 0;
    }

    public int espiar(){
        if (!estaVazia()){
            return this.elementos[tamanho -1];
        }
        return 0;
    }


}
