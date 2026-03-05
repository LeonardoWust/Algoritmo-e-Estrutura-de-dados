package exercicio2;

public class Teste {
    public static void main(String[] args) {
        int[][] numeros = {
                {1, 4, 10},
                {30, 22, 100},
                {99, 2, 3}
        };
        Matriz matriz = new Matriz(numeros);
        System.out.println(matriz.obterMaior());
    }
}
