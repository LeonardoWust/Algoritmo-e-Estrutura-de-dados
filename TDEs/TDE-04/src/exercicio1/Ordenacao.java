package exercicio1;

public class Ordenacao {

    public void bubbleSort(int[] vetor) {
        int n = vetor.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    int temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                }
            }
        }
    }

    public void bubbleSortDecrescente(int[] vetor){
        int n = vetor.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (vetor[j] < vetor[j + 1]) {
                    int temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                }
            }
        }
    }


    public void selectionSort(int[] vetor) {
        int n = vetor.length;

        for (int i = 0; i < n - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < n; j++) {
                if (vetor[j] < vetor[indiceMenor]) {
                    indiceMenor = j;
                }
            }
            int temp = vetor[indiceMenor];
            vetor[indiceMenor] = vetor[i];
            vetor[i] = temp;
        }
    }

    public void selectionSortDecrescente(int[] vetor) {
        int n = vetor.length;
        for (int i = 0; i < n - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < n; j++) {
                if (vetor[j] < vetor[indiceMenor]) {
                    indiceMenor = j;
                }
            }
            int temp = vetor[indiceMenor];
            vetor[indiceMenor] = vetor[i];
            vetor[i] = temp;
        }
    }
    public void insertionSort(int[] vetor){
        int n = vetor.length;

        for (int i = 1; i < n ; i++) {
            int key = vetor[i];
            int j = i - 1;
            while(j >= 0 && vetor[j] > key){
                vetor[j+ 1] = vetor[j];
                j--;
            }
            vetor[j+1] = key;
        }
    }
    public void insertionSortDecrescente(int[] vetor){
        int n = vetor.length;
        for (int i = 1; i < n ; i++) {
            int key = vetor[i];
            int j = i - 1;
            while(j >= 0 && vetor[j] < key){
                vetor[j+ 1] = vetor[j];
                j--;
            }
            vetor[j+1] = key;
        }
    }
}
