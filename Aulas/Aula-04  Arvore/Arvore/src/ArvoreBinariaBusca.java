public class ArvoreBinariaBusca {
    private No raiz;

    public ArvoreBinariaBusca() {
        this.raiz = null;
    }

    public void inserir(int valor) {
        raiz = inserirRec(raiz, valor);
    }

    private No inserirRec(No atual, int valor) {
        if (atual == null) {
            return new No(valor);
        }
        if (valor < atual.valor) {
            atual.esquerda = inserirRec(atual.esquerda, valor);
        } else if (valor > atual.valor) {
            atual.direita = inserirRec(atual.direita, valor);
        }
        return atual;
    }

    public boolean buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    private boolean buscarRec(No atual, int valor) {
        if (atual == null) {
            return false;
        }
        if (valor == atual.valor) {
            return true;
        }
        if (valor < atual.valor) {
            return buscarRec(atual.esquerda, valor);
        } else {
            return buscarRec(atual.direita, valor);
        }
    }

    public void remover(int valor) {
        raiz = removerRec(raiz, valor);
    }

    private No removerRec(No atual, int valor) {
        if (atual == null) return null;

        if (valor < atual.valor) {
            atual.esquerda = removerRec(atual.esquerda, valor);
        } else if (valor > atual.valor) {
            atual.direita = removerRec(atual.direita, valor);
        } else {

            if (atual.esquerda == null && atual.direita == null) {
                return null;
            }
            else if (atual.esquerda == null) {
                return atual.direita;
            }
            else if (atual.direita == null) {
                return atual.esquerda;
            }
            else {
                No sucessor = buscaMenor(atual.direita);
                atual.valor = sucessor.valor;
                atual.direita = removerRec(atual.direita, sucessor.valor);
            }
        }
        return atual;
    }

    private No buscaMenor(No atual) {
        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }
        return atual;
    }

    public void imprimirEmOrdem() {
        System.out.print("Em ordem: ");
        imprimirEmOrdemRec(raiz);
        System.out.println();
    }

    private void imprimirEmOrdemRec(No no) {
        if (no != null) {
            imprimirEmOrdemRec(no.esquerda);
            System.out.print(no.valor + " ");
            imprimirEmOrdemRec(no.direita);
        }
    }


    public void imprimirPreOrdem() {
        System.out.print("Pré-ordem: ");
        imprimirPreOrdemRec(raiz);
        System.out.println();
    }

    private void imprimirPreOrdemRec(No no) {
        if (no != null) {
            System.out.print(no.valor + " ");
            imprimirPreOrdemRec(no.esquerda);
            imprimirPreOrdemRec(no.direita);
        }
    }

    public void imprimirPosOrdem() {
        System.out.print("Pós-ordem: ");
        imprimirPosOrdemRec(raiz);
        System.out.println();
    }

    private void imprimirPosOrdemRec(No no) {
        if (no != null) {
            imprimirPosOrdemRec(no.esquerda);
            imprimirPosOrdemRec(no.direita);
            System.out.print(no.valor + " ");
        }
    }

    public int altura() {
        return alturaRec(raiz);
    }

    private int alturaRec(No no) {
        if (no == null) return -1;
        int alturaEsq = alturaRec(no.esquerda);
        int alturaDir = alturaRec(no.direita);
        return 1 + Math.max(alturaEsq, alturaDir);
    }

    public int contarNos() {
        return contarNosRec(raiz);
    }

    private int contarNosRec(No no) {
        if (no == null) return 0;
        return 1 + contarNosRec(no.esquerda) + contarNosRec(no.direita);
    }

    public int contarFolhas() {
        return contarFolhasRec(raiz);
    }

    private int contarFolhasRec(No no) {
        if (no == null) return 0;
        if (no.esquerda == null && no.direita == null) return 1;
        return contarFolhasRec(no.esquerda) + contarFolhasRec(no.direita);
    }

    public int profundidadeNo(int valor) {
        return profundidadeRec(raiz, valor, 0);
    }

    private int profundidadeRec(No atual, int valor, int profundidade) {
        if (atual == null) return -1;
        if (valor == atual.valor) return profundidade;
        if (valor < atual.valor) {
            return profundidadeRec(atual.esquerda, valor, profundidade + 1);
        } else {
            return profundidadeRec(atual.direita, valor, profundidade + 1);
        }
    }

    public void exibirPropriedades() {
        System.out.println("=== Propriedades da Árvore ===");
        System.out.println("Total de nós  : " + contarNos());
        System.out.println("Folhas        : " + contarFolhas());
        System.out.println("Altura        : " + altura());
        System.out.println("==============================");
    }
}