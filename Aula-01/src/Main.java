import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite seu nome: ");
        String nome = scan.next();
        System.out.println("Digite sua idade: ");
        int idade = scan.nextInt();

        System.out.println("Olá " + nome + " sua idade é " + idade );




    }
}