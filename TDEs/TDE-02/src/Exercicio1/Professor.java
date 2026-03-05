package Exercicio1;

public class Professor extends Pessoa{
    public Professor(String nome, String email) {
        super(nome, email);
    }

    @Override
    public void logar() {
        System.out.println("Professor logado");
    }
}
