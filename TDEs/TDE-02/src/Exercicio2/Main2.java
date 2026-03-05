package Exercicio2;

public class Main2 {
    public static void main(String[] args) {
        Livro[] lista = new Livro[5];
        Livraria livraria = new Livraria(lista, 0);
        Livro liv1 = new Livro("os guri", "catiça", 50, true);
        Livro liv2 = new Livro("ó u aviazinho", "silvio santos", 100, true);
        Livro liv3 = new Livro("titulo random", "anonimo", 77, false);

        livraria.inserirLivros(liv1);
        livraria.inserirLivros(liv2);
        livraria.inserirLivros(liv3);

        livraria.listarLivros();

        livraria.listarDisponiveis();
    }
}
