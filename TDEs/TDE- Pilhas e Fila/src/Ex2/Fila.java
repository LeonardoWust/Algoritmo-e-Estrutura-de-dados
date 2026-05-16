package Ex2;

public class Fila {
    private char [] letras;
    private int tamanho;

    public Fila( int capacidade) {
        this.letras = new char[capacidade];
        this.tamanho = 0;
    }

    public int tamanho(){
        return tamanho;
    }
    public boolean estaVazia(){
        return tamanho == 0;
    }
    public boolean estaCheia(){
        return tamanho == this.letras.length;
    }

    public void enfileirar(char c){
        if (!estaCheia()){
            this.letras[tamanho] = c;
            tamanho++;
        }
        return;
    }

    public char desenfileirar(){
        if (!estaVazia()){
            char letraRemovida = letras[0];

            for (int i = 0; i < tamanho; i++) {
                letras[i -1] = letras[i];
            }
            tamanho--;

            return letraRemovida;
        }
        return '\0';
    }
}
