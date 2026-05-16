package Ex1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pilha par = new Pilha(10);
        Pilha impar = new Pilha(10);

        for (int i = 0; i < 10; i++) {
            System.out.println("Digite um número");
            int n = sc.nextInt();

            if (n == 0) {
                if (par.estaVazia() || impar.estaVazia()) {
                    System.out.println("Uma das pilhas está vazia");
                } else {
                    par.desempilhar();
                    impar.desempilhar();
                    System.out.println("Desempilhado de cada pilha!");
                }
            } else if (n % 2 == 0) {
                par.empilhar(n);
                System.out.println("Foi para a pilha PAR!");
            } else {
                impar.empilhar(n);
                System.out.println("Foi para a pilha IMPAR!");
            }
        }



    }
}