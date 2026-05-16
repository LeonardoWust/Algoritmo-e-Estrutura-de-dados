package Ex2;

public class main {
    public static void main(String[] args) {
        String palavra = "AABCCBAA"; // Exemplo do exercício
        Fila fila = new Fila(palavra.length());

        // 1. Coloca todas as letras na fila
        for (int i = 0; i < palavra.length(); i++) {
            fila.enfileirar(palavra.charAt(i));
        }

        boolean ePalindromo = true;

        // 2. Compara a letra que sai da fila (início)
        // com a letra da string na posição inversa (fim)
        for (int i = palavra.length() - 1; i >= 0; i--) {
            char letraDaFila = fila.desenfileirar();
            char letraInversa = palavra.charAt(i);

            if (letraDaFila != letraInversa) {
                ePalindromo = false;
                break;
            }
        }

        if (ePalindromo) {
            System.out.println(palavra + " - sim");
        } else {
            System.out.println(palavra + " - não");
        }
    }

}
