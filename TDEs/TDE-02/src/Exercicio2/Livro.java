package Exercicio2;

public class Livro {
    private String titulo;
    private String autor;
    private int numeroPaginas;
    private boolean disponibilidade;

    public Livro(String titulo, String autor, int numeroPaginas, boolean disponibilidade) {
        this.titulo = titulo;
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
        this.disponibilidade = disponibilidade;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public void exibirDetalhes(){
        System.out.println("=== INFO ===");
        System.out.println("Título: " + getTitulo());
        System.out.println("Autor: " + getAutor());
        System.out.println("Número de Páginas: " + getNumeroPaginas());
        System.out.println("Disponibilidade: " + disponibilidade);
    }

    public void reservar(){
        if (disponibilidade == true) {
            System.out.println("Não disponivel, Confirmando a reserva");
            setDisponibilidade(false);
        }else {
            System.out.println("Livro já reservado");
        }
    }

    public void devolver(){
        if (disponibilidade == false){
            System.out.println("Confirmando devolução");
            setDisponibilidade(true);
        }
    }
}
