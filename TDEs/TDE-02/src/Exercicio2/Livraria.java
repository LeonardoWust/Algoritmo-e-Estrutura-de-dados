package Exercicio2;

public class Livraria {
    private Livro[] livros;
    private int tamanho;

    public Livraria(Livro[] livros, int tamanho) {
        this.livros = livros;
        this.tamanho = tamanho;
    }

    public Livraria(int i) {
    }

    public Livro[] getLivros() {
        return livros;
    }

    public void setLivros(Livro[] livros) {
        this.livros = livros;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void inserirLivros (Livro livro) {
        if (tamanho <livros.length) {
            livros[tamanho] = livro;
            tamanho++;
        }
    }

    public void listarLivros(){
        for (int i = 0; i < tamanho ; i++) {
            System.out.println(livros[i].getTitulo());
        }
    }

    public void listarDisponiveis() {
        for (int i = 0; i < tamanho ; i++) {
            if (livros[i].isDisponibilidade()){
                livros[i].exibirDetalhes();
            }
        }
    }
}
