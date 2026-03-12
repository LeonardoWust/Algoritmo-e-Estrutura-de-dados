package exercicio1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Ordenacao ordena = new Ordenacao();
        int[] numeros = {8, 5, 10, 40, 99, 70, 102, 0, -4, 6};
        System.out.println(Arrays.toString(numeros));
        ordena.insertionSortDecrescente(numeros);
        System.out.println(Arrays.toString(numeros));
    }
}