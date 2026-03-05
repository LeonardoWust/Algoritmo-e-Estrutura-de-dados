package Exercicio1;

public class Turma {
    private String id;
    private String nome;
    private Professor professor;
    private Aluno[] alunos;
    private int tamanho;

    public Turma(String id, String nome){
        this.id = id;
        this.nome = nome;
        alunos = new Aluno[40];
        this.tamanho = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Aluno[] getAlunos() {
        return alunos;
    }

    public void setAlunos(Aluno[] alunos) {
        this.alunos = alunos;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void inserirAluno(Aluno aluno){
        if (tamanho <alunos.length) {
            alunos[tamanho] = aluno;
            tamanho++;
        }
    }

    public void listarAlunos(){
        for (int i = 0; i < tamanho; i++) {
            System.out.println(alunos[i].nome);

        }
    }

}
