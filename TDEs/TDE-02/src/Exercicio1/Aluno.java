package Exercicio1;

public class Aluno extends  Pessoa{

    public Aluno(String nome, String email) {
        super(nome, email);
    }

    @Override
    public void logar() {
        System.out.println("Aluno logado");
    }
}
