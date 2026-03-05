import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numero1, numero2, operacao;
        System.out.println("Digite o primeiro número: ");
        numero1 = scan.nextInt();
        System.out.println("Digite o segundo número: ");
        numero2 = scan.nextInt();
        System.out.println("Escolha a operação: \n 1-Somar \n 2-Subtrair \n 3- Multiplicar \n 4-Dividir");
        operacao = scan.nextInt();

        switch (operacao){
            case 1:
                System.out.println("A soma é: " + (numero1 + numero2));
                break;
            case 2:
                System.out.println("A subtracao é: " + (numero1 - numero2));
                break;
            case 3:
                System.out.println("A multiplicacao é: " + (numero1 * numero2));
                break;
            case 4:
                if (numero2 == 0) {
                    System.out.println("Impossível dividir");
                    break;
                }
                System.out.println("A soma é: " + (numero1 / numero2));
                break;
            default:
                System.out.println("Opção Inválida");
        }
    }
}
