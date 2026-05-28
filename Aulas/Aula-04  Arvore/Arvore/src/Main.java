
public class Main {
    public static void main(String[] args) {

        public class Main {
            public static void main(String[] args) {

                ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();

                //  Inserção
                System.out.println(">>> Inserindo: 50 30 70 20 40 60 80 10 35");
                int[] valores = {50, 30, 70, 20, 40, 60, 80, 10, 35};
                for (int v : valores) arvore.inserir(v);

                //  Impressões
                arvore.imprimirEmOrdem();
                arvore.imprimirPreOrdem();
                arvore.imprimirPosOrdem();

                // Propriedades
                arvore.exibirPropriedades();


                // Profundidade
                System.out.println("\n>>> Profundidade dos nós:");
                int[] testar = {50, 30, 10, 35, 80, 99};
                for (int v : testar) {
                    int prof = arvore.profundidadeNo(v);
                    if (prof == -1) {
                        System.out.println("  Nó " + v + " → não encontrado");
                    } else {
                        System.out.println("  Nó " + v + " → profundidade " + prof);
                    }
                }

                // Busca
                System.out.println("\n>>> Busca:");
                System.out.println("  buscar(40): " + arvore.buscar(40));
                System.out.println("  buscar(99): " + arvore.buscar(99));

                // Removendo
                System.out.println("\n>>> Removendo nó folha (10):");
                arvore.remover(10);
                arvore.imprimirEmOrdem();
                arvore.exibirPropriedades();

                System.out.println(">>> Removendo nó com um filho (20):");
                arvore.remover(20);
                arvore.imprimirEmOrdem();

                System.out.println(">>> Removendo nó com dois filhos (30):");
                arvore.remover(30);
                arvore.imprimirEmOrdem();

                System.out.println(">>> Removendo a raiz (50):");
                arvore.remover(50);
                arvore.imprimirEmOrdem();

                arvore.exibirPropriedades();
            }
        }

    }
}