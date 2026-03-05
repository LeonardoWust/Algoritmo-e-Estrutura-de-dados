import Exercicio1.Aluno;
import Exercicio1.Pessoa;
import Exercicio1.Professor;
import Exercicio1.Turma;

public class Main {
    public static void main(String[] args) {
        Aluno a1 = new Aluno("Leonardo","asdad@");
        Aluno a2 = new Aluno("alan","aasdaa2@!");
        Aluno a3 = new Aluno("rafa","laldsa@");
        Turma t1 = new Turma("1","arquitetura");

        t1.inserirAluno(a1);
        t1.inserirAluno(a2);
        t1.inserirAluno(a3);

        t1.listarAlunos();
    }
}