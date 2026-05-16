package Pilhas;

import java.util.Stack;

public class Pilha {
    public static void main(String[] args) {
        Stack<String> caixaDeLivros = new Stack<>();

        caixaDeLivros.push("Harry Potter");
        caixaDeLivros.push("Duna");

        String livroParaLer = caixaDeLivros.pop();

        System.out.println(caixaDeLivros.peek());
    }
}
