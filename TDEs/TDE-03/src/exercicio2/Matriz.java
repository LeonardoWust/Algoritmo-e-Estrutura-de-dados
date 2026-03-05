package exercicio2;

public class Matriz {
    private int [][] matriz;
    private int linhas;
    private int colunas;

    public Matriz(int matriz[][]){
        this.matriz = matriz;
    }

    public int obterMaior(){
        int maior = matriz[0][0];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] > maior){
                    maior = matriz[i][j];
                }
            }
        }
        return maior;
    }
    
    public int obterMenor(){
        int menor = matriz[0][0];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j <matriz[i].length ; j++) {
                if(matriz[i][i] < menor){
                    menor = matriz[i][i];
                }
            }
        }
        return menor;
    }
    
    public int calcularSomaTotal() {
        
        int soma = 0;
        
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                soma = soma + matriz[i][i];
            }
        }
        return soma;
    }

    public double calcularMedia() {
        int soma = 0;
        int quantidade = 0;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {

                soma = soma + matriz[i][j];
                quantidade++;

            }
        }

        if (quantidade == 0) {
            return 0;
        }
        return (double) soma / quantidade;
    }

    public int contarOcorrencias(int valorProcurado) {
        int contador = 0;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == valorProcurado) {
                    contador++;

                }
            }
        }
        return contador;
    }
}
