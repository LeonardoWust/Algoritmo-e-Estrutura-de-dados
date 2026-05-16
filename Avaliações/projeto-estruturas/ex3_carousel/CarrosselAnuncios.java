import java.util.Scanner;

public class CarrosselAnuncios {

    static class NoAnuncio {
        int id;
        String empresa;
        String descricao;
        int contadorExibicoes;
        NoAnuncio proximo;

        NoAnuncio(int id, String empresa, String descricao) {
            this.id = id;
            this.empresa = empresa;
            this.descricao = descricao;
            this.contadorExibicoes = 0;
            this.proximo = null;
        }
    }

    static class ListaCircular {
        private NoAnuncio cauda;
        private NoAnuncio anuncioAtual;
        private int tamanho;
        private int proximoId;

        ListaCircular() {
            this.cauda = null;
            this.anuncioAtual = null;
            this.tamanho = 0;
            this.proximoId = 1;
        }

        void exibirEAvancar() {
            if (anuncioAtual == null) {
                System.out.println("[PAINEL] Nenhum anuncio cadastrado no carrossel.");
                return;
            }
            anuncioAtual.contadorExibicoes++;
            System.out.println("\n+------------------------------------------+");
            System.out.println("|          PAINEL DIGITAL                  |");
            System.out.println("+------------------------------------------+");
            System.out.printf("|  ID:       %-31s|%n", "#" + anuncioAtual.id);
            System.out.printf("|  Empresa:  %-31s|%n", anuncioAtual.empresa);
            System.out.printf("|  Anuncio:  %-31s|%n", truncar(anuncioAtual.descricao, 31));
            System.out.printf("|  Exibicoes:%-31s|%n", anuncioAtual.contadorExibicoes + "x");
            System.out.println("+------------------------------------------+");
            anuncioAtual = anuncioAtual.proximo;
        }

        void adicionarAposAtual(String empresa, String descricao) {
            NoAnuncio novo = new NoAnuncio(proximoId++, empresa, descricao);
            if (cauda == null) {
                novo.proximo = novo;
                cauda = novo;
                anuncioAtual = novo;
            } else {
                novo.proximo = anuncioAtual.proximo;
                anuncioAtual.proximo = novo;
                if (anuncioAtual == cauda) {
                    cauda = novo;
                }
            }
            tamanho++;
            System.out.println("[+] Anuncio #" + novo.id + " da empresa \"" + empresa + "\" inserido apos o atual.");
        }

        void adicionarNoFinal(String empresa, String descricao) {
            NoAnuncio novo = new NoAnuncio(proximoId++, empresa, descricao);
            if (cauda == null) {
                novo.proximo = novo;
                cauda = novo;
                anuncioAtual = novo;
            } else {
                novo.proximo = cauda.proximo;
                cauda.proximo = novo;
                cauda = novo;
            }
            tamanho++;
            System.out.println("[+] Anuncio #" + novo.id + " da empresa \"" + empresa + "\" inserido no final.");
        }

        void removerPorId(int id) {
            if (cauda == null) {
                System.out.println("[!] Carrossel vazio.");
                return;
            }
            if (tamanho == 1) {
                if (cauda.id == id) {
                    System.out.println("[-] Anuncio #" + cauda.id + " removido. Carrossel agora vazio.");
                    cauda = null;
                    anuncioAtual = null;
                    tamanho--;
                } else {
                    System.out.println("[!] Anuncio #" + id + " nao encontrado.");
                }
                return;
            }
            NoAnuncio anterior = cauda;
            NoAnuncio alvo = cauda.proximo;
            boolean encontrou = false;
            for (int i = 0; i < tamanho; i++) {
                if (alvo.id == id) { encontrou = true; break; }
                anterior = alvo;
                alvo = alvo.proximo;
            }
            if (!encontrou) {
                System.out.println("[!] Anuncio #" + id + " nao encontrado.");
                return;
            }
            if (alvo == anuncioAtual) {
                anuncioAtual = alvo.proximo;
            }
            anterior.proximo = alvo.proximo;
            if (alvo == cauda) {
                cauda = anterior;
            }
            alvo.proximo = null;
            tamanho--;
            System.out.println("[-] Anuncio #" + id + " (\"" + alvo.empresa + "\") removido do carrossel.");
        }

        void listarCicloCompleto() {
            if (anuncioAtual == null) {
                System.out.println("[PAINEL] Carrossel vazio.");
                return;
            }
            System.out.println("\n=== CICLO COMPLETO DO CARROSSEL ===");
            NoAnuncio inicio = anuncioAtual;
            NoAnuncio cursor = anuncioAtual;
            int posicao = 1;
            do {
                String marcador = (posicao == 1) ? " < ATUAL" : "";
                System.out.printf("  [%d] #%d | %-20s | %-35s | %dx%s%n",
                    posicao, cursor.id, cursor.empresa,
                    truncar(cursor.descricao, 33), cursor.contadorExibicoes, marcador);
                cursor = cursor.proximo;
                posicao++;
            } while (cursor != inicio);
            System.out.println("  Total: " + tamanho + " anuncio(s)");
            System.out.println("  Ciclo fecha: #" + cauda.id + " -> #" + cauda.proximo.id);
            System.out.println("===================================\n");
        }

        void exibirAutomatico(int n) {
            if (anuncioAtual == null) { System.out.println("[PAINEL] Carrossel vazio."); return; }
            System.out.println("\n[AUTO] Exibindo " + n + " anuncios automaticamente...\n");
            for (int i = 0; i < n; i++) {
                System.out.println("  --- Exibicao " + (i+1) + " de " + n + " ---");
                exibirEAvancar();
                try { Thread.sleep(600); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
            System.out.println("[AUTO] Exibicao automatica concluida.\n");
        }

        private String truncar(String str, int max) {
            if (str == null) return "";
            return str.length() > max ? str.substring(0, max-3) + "..." : str;
        }

        boolean estaVazio() { return cauda == null; }
        int getTamanho() { return tamanho; }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaCircular carrossel = new ListaCircular();

        System.out.println("+==========================================+");
        System.out.println("|     PAINEL DIGITAL - CARROSSEL           |");
        System.out.println("+==========================================+");

        System.out.println("\n[SISTEMA] Carregando anuncios iniciais...");
        carrossel.adicionarNoFinal("TechStore", "50% OFF em notebooks! So este fim de semana.");
        carrossel.adicionarNoFinal("FoodPark", "Melhor hamburguer da cidade. Peca ja!");
        carrossel.adicionarNoFinal("Academia FitLife", "Primeiro mes GRATIS. Venha treinar conosco!");
        carrossel.adicionarNoFinal("Banco Digital X", "Abra sua conta em 5 minutos. Sem tarifas.");

        int opcao;
        do {
            System.out.println("\n+================================+");
            System.out.println("|         MENU DO PAINEL        |");
            System.out.println("+================================+");
            System.out.println("|  1. Exibir e Avancar          |");
            System.out.println("|  2. Adicionar anuncio         |");
            System.out.println("|  3. Remover anuncio           |");
            System.out.println("|  4. Listar ciclo completo     |");
            System.out.println("|  5. Exibicao automatica       |");
            System.out.println("|  0. Sair                      |");
            System.out.println("+================================+");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: carrossel.exibirEAvancar(); break;
                case 2:
                    System.out.println("Inserir (1=Apos atual / 2=No final): ");
                    int tipo = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Nome da empresa: ");
                    String emp = scanner.nextLine();
                    System.out.print("Descricao: ");
                    String dsc = scanner.nextLine();
                    if (tipo == 1) carrossel.adicionarAposAtual(emp, dsc);
                    else carrossel.adicionarNoFinal(emp, dsc);
                    break;
                case 3:
                    System.out.print("ID do anuncio a remover: ");
                    int idR = scanner.nextInt(); scanner.nextLine();
                    carrossel.removerPorId(idR);
                    break;
                case 4: carrossel.listarCicloCompleto(); break;
                case 5:
                    System.out.print("Quantos anuncios exibir automaticamente? ");
                    int qtd = scanner.nextInt(); scanner.nextLine();
                    carrossel.exibirAutomatico(qtd);
                    break;
                case 0: System.out.println("\n[SISTEMA] Desligando painel... Ate logo!"); break;
                default: System.out.println("[ERRO] Opcao invalida!");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
