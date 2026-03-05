package Exercicio1;

import java.util.Arrays;

public class Vetor {
    private String[] elementos;
    private int tamanho;

    public Vetor(int capacidade){
        this.elementos = new String[capacidade];
        this.tamanho = 0;
    }


    public void inserirNoFim(String elemento){
        if(tamanho < this.elementos.length){
            this.elementos[tamanho] = elemento;
            tamanho++;
        }else{
            System.out.println("Vetor Cheio!!!");
        }
    }

    public void inserirEspecifico(int posicao, String elemento){
        if(!(posicao >= 0 && posicao <= tamanho)){
            System.out.println("Posição Inválida");
            return;
        }
        if (tamanho >= this.elementos.length){
            System.out.println("Vetor cheio!!");
            return;
        }
        for(int i = tamanho - 1; i >= posicao; i--){
            this.elementos[i + 1] = this.elementos[i];
        }
        this.elementos[posicao] = elemento;

        tamanho++;
    }

    public String obterPosicao(int posicao){
        if(posicao >=0 && posicao < elementos.length){
            return elementos[posicao];
        }
        return null;
    }

    public int tamanho(){
        return tamanho;
    }

    public void alterarElemento(int posicao, String novoElemento){
        if(!(posicao >= 0 && posicao < tamanho)){
            System.out.println("Posição inválida");
            return;
        }
        this.elementos[posicao] = novoElemento;
    }

    public void aumentarCapacidade(){
        String[] novoVetor = new String[this.elementos.length * 2];
        for (int i = 0; i < tamanho; i++) {
            novoVetor[i] = elementos[i];
        }
        this.elementos = novoVetor;
    }

    public String removerIndice(int indice){
        if(indice >= 0 && indice < tamanho){
            String removido = this.elementos[indice];
            for (int i = indice; i < tamanho; i++){
                this.elementos[i] = this.elementos[i + 1];
            }
            tamanho--;
            return removido;
        }
        return null;
    }

    public void removerValor(String valor){
        for (int i = 0; i < tamanho; i++){
            if (this.elementos[i].equals(valor)){
                for (int j = i; j < tamanho - 1; j++){
                    this.elementos[j] = this.elementos[j +1];
                }
                tamanho --;
                return;
            }
        }
        System.out.println("Elemento não encontrado!");
    }

    public boolean verificarExistencia(String valorProcurado){
        for (int i = 0; i < tamanho; i++) {
            if (this.elementos[i].equals(valorProcurado)){
                return true;
            }
        }
        return false;
    }

    public boolean estaVazio(){
        if (this.tamanho == 0){
            System.out.println("Está vazio");
        }else {
            System.out.println("Não está vazio");
        }
        return false;
    }

    public void limpar(){
        this.tamanho = 0;
    }

    @Override
    public String toString(){

        String resultado = "[";

        for (int i = 0; i < tamanho; i++) {
            resultado = resultado + this.elementos[i];

            if (i < tamanho - 1){
                resultado = resultado + ", ";
            }
        }

        resultado = resultado + "] ";

        return resultado;
    }
}
