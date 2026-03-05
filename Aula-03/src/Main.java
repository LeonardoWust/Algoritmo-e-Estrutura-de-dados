//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int[] numeros; // Declaração de vetor
        numeros = new int[10]; //Inicialização do vetor
        numeros[0] = 100;
        System.out.println(numeros[0]);

        String[] jogadores = {"Vini Junior", "Raphinha", "Alisson"};

        System.out.println(jogadores[1]);

        for (int i = 0; i < jogadores.length; i++) {
            System.out.println(jogadores[i]);
        }

//        for(int numero : numeros){
//            System.out.print(numero + "-");
//        }

        for (int i = 0; i < numeros.length; i++) {
            if(i == numeros.length - 1){
                System.out.print(numeros[i]);
            }else {
                System.out.print(numeros[i] + ",");
            }
        }
        System.out.println("]");

        int[][] matriz = new int[3][3];

        matriz[0][0] = 1;
        matriz[0][1] = 10;
        matriz[0][2] = 100;

        System.out.println(matriz[0].length);

        for (int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[i].length; j++){
                System.out.println(matriz[i][j] + "|");
            }
            System.out.println("\n------------");
        }

    }
}