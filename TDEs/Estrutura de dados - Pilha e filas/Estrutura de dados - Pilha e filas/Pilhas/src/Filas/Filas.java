package Filas;

import java.util.LinkedList;
import java.util.Queue;

public class Filas {
    public static void main(String[] args) {
        Queue<String> filaDoBanco = new LinkedList<>();

        filaDoBanco.add("Senha A01");
        filaDoBanco.add("Senha B02");

        System.out.println(filaDoBanco.peek());
    }
}
