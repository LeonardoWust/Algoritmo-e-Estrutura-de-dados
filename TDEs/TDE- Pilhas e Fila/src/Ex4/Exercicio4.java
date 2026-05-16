package Ex4;

import java.util.Scanner;

public class Exercicio4 {
    public static void main(String[] args) {
        FilaClientes clientes = new FilaClientes(5);

        Scanner scanner = new Scanner(System.in);
        int i = 0;
        String nome;
        int tempo;
        while (i < 5){
            System.out.println("Informe o nome do cliente: ");
            nome = scanner.next();
            System.out.println("Informe o número de minutos: ");
            tempo = scanner.nextInt();
            Cliente c = new Cliente(nome, tempo);
            clientes.enfileirar(c);
            i++;
        }
    }
}
