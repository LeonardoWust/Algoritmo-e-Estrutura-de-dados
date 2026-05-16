import java.util.Scanner;

public class Cafeteria {

    // =========================================================
    // NÓ GENÉRICO DA LISTA SIMPLESMENTE LIGADA
    // =========================================================
    static class No {
        int id;
        String descricao;
        No proximo;

        No(int id, String descricao) {
            this.id = id;
            this.descricao = descricao;
            this.proximo = null;
        }
    }

    // =========================================================
    // FILA DE PEDIDOS PENDENTES (FIFO - First In, First Out)
    // Inserção no FINAL (cauda), remoção no INÍCIO (cabeça)
    // =========================================================
    static class FilaPedidos {
        private No cabeca; // frente da fila (próximo a ser atendido)
        private No cauda;  // fim da fila (último inserido)
        private int tamanho;

        FilaPedidos() {
            this.cabeca = null;
            this.cauda = null;
            this.tamanho = 0;
        }

        // Adiciona pedido ao FINAL da fila
        void enqueue(int id, String descricao) {
            No novo = new No(id, descricao);
            if (cauda == null) {
                // Fila vazia: cabeça e cauda apontam para o mesmo nó
                cabeca = novo;
                cauda = novo;
            } else {
                cauda.proximo = novo;
                cauda = novo;
            }
            tamanho++;
            System.out.println("[FILA] Pedido #" + id + " (" + descricao + ") adicionado à fila.");
        }

        // Remove pedido do INÍCIO da fila e retorna ele
        No dequeue() {
            if (cabeca == null) {
                System.out.println("[FILA] Fila de pedidos está vazia!");
                return null;
            }
            No removido = cabeca;
            cabeca = cabeca.proximo;
            if (cabeca == null) {
                // Fila ficou vazia após remoção
                cauda = null;
            }
            removido.proximo = null; // desconecta o nó removido
            tamanho--;
            return removido;
        }

        // Imprime todos os pedidos da fila (frente → fim)
        void printQueue() {
            if (cabeca == null) {
                System.out.println("[FILA] Nenhum pedido pendente.");
                return;
            }
            System.out.println("\n=== PEDIDOS PENDENTES ===");
            No atual = cabeca;
            int posicao = 1;
            while (atual != null) {
                System.out.println("  [" + posicao + "] Pedido #" + atual.id + " → " + atual.descricao);
                atual = atual.proximo;
                posicao++;
            }
            System.out.println("=========================\n");
        }

        boolean estaVazia() {
            return cabeca == null;
        }

        int getTamanho() {
            return tamanho;
        }
    }

    // =========================================================
    // PILHA DE PEDIDOS CANCELADOS (LIFO - Last In, First Out)
    // Inserção e remoção sempre pelo TOPO
    // =========================================================
    static class PilhaCancelados {
        private No topo;
        private int tamanho;

        PilhaCancelados() {
            this.topo = null;
            this.tamanho = 0;
        }

        // Empurra pedido cancelado para o TOPO da pilha
        void push(No no) {
            no.proximo = topo; // novo nó aponta para o antigo topo
            topo = no;         // topo agora é o novo nó
            tamanho++;
            System.out.println("[PILHA] Pedido #" + no.id + " (" + no.descricao + ") adicionado à pilha de cancelados.");
        }

        // Remove e retorna o pedido do TOPO da pilha
        No pop() {
            if (topo == null) {
                System.out.println("[PILHA] Pilha de cancelados está vazia! Nenhum pedido para restaurar.");
                return null;
            }
            No removido = topo;
            topo = topo.proximo; // topo passa a ser o nó seguinte
            removido.proximo = null;
            tamanho--;
            return removido;
        }

        // Imprime todos os pedidos cancelados (topo → base)
        void printStack() {
            if (topo == null) {
                System.out.println("[PILHA] Nenhum pedido cancelado.");
                return;
            }
            System.out.println("\n=== PEDIDOS CANCELADOS (topo → base) ===");
            No atual = topo;
            int posicao = 1;
            while (atual != null) {
                System.out.println("  [" + posicao + "] Pedido #" + atual.id + " → " + atual.descricao);
                atual = atual.proximo;
                posicao++;
            }
            System.out.println("=========================================\n");
        }

        boolean estaVazia() {
            return topo == null;
        }

        int getTamanho() {
            return tamanho;
        }
    }

    // =========================================================
    // PROGRAMA PRINCIPAL
    // =========================================================
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FilaPedidos fila = new FilaPedidos();
        PilhaCancelados pilha = new PilhaCancelados();
        int proximoId = 1;

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║     SISTEMA DE PEDIDOS - CAFETERIA   ║");
        System.out.println("╚══════════════════════════════════════╝");

        // Pré-carrega alguns pedidos para demonstração
        System.out.println("\n[SISTEMA] Carregando pedidos iniciais...");
        fila.enqueue(proximoId++, "Café Expresso + Pão de Queijo");
        fila.enqueue(proximoId++, "Cappuccino + Croissant");
        fila.enqueue(proximoId++, "Suco de Laranja + Tapioca");
        fila.enqueue(proximoId++, "Chá Verde + Bolo de Cenoura");

        int opcao;
        do {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║              MENU PRINCIPAL          ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  1. Adicionar novo pedido            ║");
            System.out.println("║  2. Atender pedido (dequeue)         ║");
            System.out.println("║  3. Cancelar pedido (dequeue→push)   ║");
            System.out.println("║  4. Restaurar pedido (pop→enqueue)   ║");
            System.out.println("║  5. Imprimir pedidos pendentes       ║");
            System.out.println("║  6. Imprimir pedidos cancelados      ║");
            System.out.println("║  0. Sair                             ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa buffer

            switch (opcao) {
                case 1:
                    System.out.print("Descrição do pedido: ");
                    String desc = scanner.nextLine();
                    fila.enqueue(proximoId++, desc);
                    break;

                case 2:
                    System.out.println("\n--- ATENDENDO PEDIDO ---");
                    No atendido = fila.dequeue();
                    if (atendido != null) {
                        System.out.println("✓ Pedido #" + atendido.id + " (" + atendido.descricao + ") foi ATENDIDO!");
                    }
                    break;

                case 3:
                    System.out.println("\n--- CANCELANDO PEDIDO ---");
                    No cancelado = fila.dequeue();
                    if (cancelado != null) {
                        pilha.push(cancelado);
                        System.out.println("✗ Pedido #" + cancelado.id + " (" + cancelado.descricao + ") foi CANCELADO e movido para a pilha.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- RESTAURANDO PEDIDO ---");
                    No restaurado = pilha.pop();
                    if (restaurado != null) {
                        fila.enqueue(restaurado.id, restaurado.descricao);
                        System.out.println("↩ Pedido #" + restaurado.id + " (" + restaurado.descricao + ") foi RESTAURADO para a fila!");
                    }
                    break;

                case 5:
                    fila.printQueue();
                    break;

                case 6:
                    pilha.printStack();
                    break;

                case 0:
                    System.out.println("\n[SISTEMA] Encerrando... Até logo!");
                    break;

                default:
                    System.out.println("[ERRO] Opção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }
}
