package Ex3;

import java.util.Scanner;
public class Playlist {
    static class NoMusica {
        String titulo;
        String artista;
        String album;
        int duracao;
        NoMusica anterior;
        NoMusica proximo;
        NoMusica(String titulo, String artista, String album, int duracao) {
            this.titulo = titulo;
            this.artista = artista;
            this.album = album;
            this.duracao = duracao;
            this.anterior = null;
            this.proximo = null;
        }
        String duracaoFormatada() {
            int min = duracao / 60;
            int seg = duracao % 60;
            return String.format("%d:%02d", min, seg);
        }
    }
    static class ListaPlaylist {
        private NoMusica cabeca;
        private NoMusica cauda;
        private NoMusica atual;
        private int tamanho;
        private String nomePlaylist;
        ListaPlaylist(String nome) {
            this.cabeca = null;
            this.cauda = null;
            this.atual = null;
            this.tamanho = 0;
            this.nomePlaylist = nome;
        }
        void adicionarInicio(String titulo, String artista, String album, int duracao) {
            NoMusica novo = new NoMusica(titulo, artista, album, duracao);
            if (cabeca == null) {
                cabeca = novo;
                cauda = novo;
                atual = novo;
            } else {
                novo.proximo = cabeca;
                cabeca.anterior = novo;
                cabeca = novo;
            }
            tamanho++;
            System.out.println("[+] \"" + titulo + "\" adicionada ao início da playlist.");
        }
        void adicionarFim(String titulo, String artista, String album, int duracao) {
            NoMusica novo = new NoMusica(titulo, artista, album, duracao);
            if (cabeca == null) {
                cabeca = novo;
                cauda = novo;
                atual = novo;
            } else {
                cauda.proximo = novo;
                novo.anterior = cauda;
                cauda = novo;
            }
            tamanho++;
            System.out.println("[+] \"" + titulo + "\" adicionada ao fim da playlist.");
        }
        void adicionarPosicao(String titulo, String artista, String album, int duracao, int posicao) {
            if (posicao <= 1) {
                adicionarInicio(titulo, artista, album, duracao);
                return;
            }
            if (posicao > tamanho) {
                adicionarFim(titulo, artista, album, duracao);
                return;
            }
            NoMusica novo = new NoMusica(titulo, artista, album, duracao);
            NoMusica cursor = cabeca;
            for (int i = 1; i < posicao - 1; i++) {
                cursor = cursor.proximo;
            }
            novo.proximo = cursor.proximo;
            novo.anterior = cursor;
            if (cursor.proximo != null) {
                cursor.proximo.anterior = novo;
            }
            cursor.proximo = novo;
            tamanho++;
            System.out.println("[+] \"" + titulo + "\" adicionada na posição " + posicao + ".");
        }
        void removerPorTitulo(String titulo) {
            if (cabeca == null) {
                System.out.println("[!] Playlist vazia.");
                return;
            }
            NoMusica cursor = cabeca;
            while (cursor != null) {
                if (cursor.titulo.equalsIgnoreCase(titulo)) {
                    removerNo(cursor);
                    System.out.println("[-] \"" + titulo + "\" removida da playlist.");
                    return;
                }
                cursor = cursor.proximo;
            }
            System.out.println("[!] Música \"" + titulo + "\" não encontrada.");
        }
        void removerPorPosicao(int posicao) {
            if (cabeca == null) {
                System.out.println("[!] Playlist vazia.");
                return;
            }
            if (posicao < 1 || posicao > tamanho) {
                System.out.println("[!] Posição inválida. Playlist tem " + tamanho + " músicas.");
                return;
            }
            NoMusica cursor = cabeca;
            for (int i = 1; i < posicao; i++) {
                cursor = cursor.proximo;
            }
            System.out.println("[-] \"" + cursor.titulo + "\" removida da posição " + posicao + ".");
            removerNo(cursor);
        }
        private void removerNo(NoMusica no) {
            if (no == atual) {
                if (no.proximo != null) {
                    atual = no.proximo;
                } else if (no.anterior != null) {
                    atual = no.anterior;
                } else {
                    atual = null;
                }
            }
            if (no.anterior != null) {
                no.anterior.proximo = no.proximo;
            } else {
                cabeca = no.proximo;
            }
            if (no.proximo != null) {
                no.proximo.anterior = no.anterior;
            } else {
                cauda = no.anterior;
            }
            no.anterior = null;
            no.proximo = null;
            tamanho--;
        }
        void proximaMusica() {
            if (atual == null) {
                System.out.println("[!] Playlist vazia.");
                return;
            }
            if (atual.proximo != null) {
                atual = atual.proximo;
                System.out.println(">> Avançando para: \"" + atual.titulo + "\" - " + atual.artista);
            } else {
                System.out.println("[!] Já está na última música da playlist.");
            }
        }
        void musicaAnterior() {
            if (atual == null) {
                System.out.println("[!] Playlist vazia.");
                return;
            }
            if (atual.anterior != null) {
                atual = atual.anterior;
                System.out.println("<< Voltando para: \"" + atual.titulo + "\" - " + atual.artista);
            } else {
                System.out.println("[!] Já está na primeira música da playlist.");
            }
        }
        void tocarAtual() {
            if (atual == null) {
                System.out.println("[!] Nenhuma música para tocar. Playlist vazia.");
                return;
            }
            System.out.println("\n♪ ═══════════════════════════════════ ♪");
            System.out.println("  ▶  TOCANDO AGORA");
            System.out.println("  Título:   " + atual.titulo);
            System.out.println("  Artista:  " + atual.artista);
            System.out.println("  Álbum:    " + atual.album);
            System.out.println("  Duração:  " + atual.duracaoFormatada());
            System.out.println("♪ ═══════════════════════════════════ ♪\n");
        }
        void ordenarPorTitulo() {
            if (tamanho <= 1) return;
            boolean trocou;
            do {
                trocou = false;
                NoMusica cursor = cabeca;
                while (cursor != null && cursor.proximo != null) {
                    if (cursor.titulo.compareToIgnoreCase(cursor.proximo.titulo) > 0) {
                        trocarDados(cursor, cursor.proximo);
                        trocou = true;
                    }
                    cursor = cursor.proximo;
                }
            } while (trocou);
            System.out.println("[✓] Playlist ordenada por título.");
        }
        void ordenarPorArtista() {
            if (tamanho <= 1) return;
            boolean trocou;
            do {
                trocou = false;
                NoMusica cursor = cabeca;
                while (cursor != null && cursor.proximo != null) {
                    if (cursor.artista.compareToIgnoreCase(cursor.proximo.artista) > 0) {
                        trocarDados(cursor, cursor.proximo);
                        trocou = true;
                    }
                    cursor = cursor.proximo;
                }
            } while (trocou);
            System.out.println("[✓] Playlist ordenada por artista.");
        }
        private void trocarDados(NoMusica a, NoMusica b) {
            String tmpTitulo = a.titulo;   a.titulo = b.titulo;   b.titulo = tmpTitulo;
            String tmpArtista = a.artista; a.artista = b.artista; b.artista = tmpArtista;
            String tmpAlbum = a.album;     a.album = b.album;     b.album = tmpAlbum;
            int tmpDuracao = a.duracao;    a.duracao = b.duracao; b.duracao = tmpDuracao;
        }
        void buscarMusica(String termo) {
            if (cabeca == null) {
                System.out.println("[!] Playlist vazia.");
                return;
            }
            System.out.println("\n=== RESULTADOS PARA: \"" + termo + "\" ===");
            NoMusica cursor = cabeca;
            int posicao = 1;
            boolean encontrou = false;
            while (cursor != null) {
                if (cursor.titulo.toLowerCase().contains(termo.toLowerCase()) ||
                    cursor.artista.toLowerCase().contains(termo.toLowerCase()) ||
                    cursor.album.toLowerCase().contains(termo.toLowerCase())) {
                    System.out.printf("  [%d] %-35s | %-20s | %-20s | %s%n",
                        posicao, cursor.titulo, cursor.artista, cursor.album, cursor.duracaoFormatada());
                    encontrou = true;
                }
                cursor = cursor.proximo;
                posicao++;
            }
            if (!encontrou) System.out.println("  Nenhuma música encontrada.");
            System.out.println();
        }
        void listarMusicas() {
            if (cabeca == null) {
                System.out.println("[!] Playlist \"" + nomePlaylist + "\" está vazia.");
                return;
            }
            System.out.println("\n╔═══════════════════════════════════════════════════════════════════╗");
            System.out.println("║  PLAYLIST: " + String.format("%-54s", nomePlaylist) + "║");
            System.out.printf("║  %-3s %-35s %-20s %-8s%n", "#", "TÍTULO", "ARTISTA", "DURAÇÃO");
            System.out.println("╠═══════════════════════════════════════════════════════════════════╣");
            NoMusica cursor = cabeca;
            int pos = 1;
            while (cursor != null) {
                String marcador = (cursor == atual) ? "▶" : " ";
                System.out.printf("║ %s%-2d %-35s %-20s %-8s║%n",
                    marcador, pos,
                    truncar(cursor.titulo, 33),
                    truncar(cursor.artista, 18),
                    cursor.duracaoFormatada());
                cursor = cursor.proximo;
                pos++;
            }
            System.out.println("╚═══════════════════════════════════════════════════════════════════╝");
            System.out.println("  Total: " + tamanho + " música(s)\n");
        }
        private String truncar(String str, int max) {
            return str.length() > max ? str.substring(0, max - 1) + "…" : str;
        }
        boolean estaVazia() { return cabeca == null; }
        int getTamanho() { return tamanho; }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaPlaylist playlist = new ListaPlaylist("Minhas Favoritas");
        playlist.adicionarFim("Bohemian Rhapsody", "Queen", "A Night at the Opera", 354);
        playlist.adicionarFim("Hotel California", "Eagles", "Hotel California", 391);
        playlist.adicionarFim("Stairway to Heaven", "Led Zeppelin", "Led Zeppelin IV", 482);
        playlist.adicionarFim("Smells Like Teen Spirit", "Nirvana", "Nevermind", 301);
        playlist.adicionarFim("Imagine", "John Lennon", "Imagine", 187);
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║       🎵 MUSIC PLAYER 🎵         ║");
        System.out.println("╚══════════════════════════════════╝");
        int opcao;
        do {
            System.out.println("\n╔═══════════════════════════════╗");
            System.out.println("║           MENU               ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║  1. ▶  Tocar música atual    ║");
            System.out.println("║  2. >> Próxima música        ║");
            System.out.println("║  3. << Música anterior       ║");
            System.out.println("║  4. 📋 Listar playlist       ║");
            System.out.println("║  5. ➕ Adicionar música       ║");
            System.out.println("║  6. ➖ Remover música         ║");
            System.out.println("║  7. 🔤 Ordenar playlist      ║");
            System.out.println("║  8. 🔍 Buscar música         ║");
            System.out.println("║  0. 🚪 Sair                  ║");
            System.out.println("╚═══════════════════════════════╝");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    playlist.tocarAtual();
                    break;
                case 2:
                    playlist.proximaMusica();
                    break;
                case 3:
                    playlist.musicaAnterior();
                    break;
                case 4:
                    playlist.listarMusicas();
                    break;
                case 5:
                    System.out.println("Onde adicionar? (1=Início / 2=Fim / 3=Posição específica): ");
                    int onde = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Título: "); String titulo = scanner.nextLine();
                    System.out.print("Artista: "); String artista = scanner.nextLine();
                    System.out.print("Álbum: "); String album = scanner.nextLine();
                    System.out.print("Duração (segundos): "); int dur = scanner.nextInt(); scanner.nextLine();
                    if (onde == 1) playlist.adicionarInicio(titulo, artista, album, dur);
                    else if (onde == 3) {
                        System.out.print("Posição: "); int pos = scanner.nextInt(); scanner.nextLine();
                        playlist.adicionarPosicao(titulo, artista, album, dur, pos);
                    } else playlist.adicionarFim(titulo, artista, album, dur);
                    break;
                case 6:
                    System.out.println("Remover por (1=Título / 2=Posição): ");
                    int tipoRemover = scanner.nextInt(); scanner.nextLine();
                    if (tipoRemover == 1) {
                        System.out.print("Título: "); String t = scanner.nextLine();
                        playlist.removerPorTitulo(t);
                    } else {
                        System.out.print("Posição: "); int p = scanner.nextInt(); scanner.nextLine();
                        playlist.removerPorPosicao(p);
                    }
                    break;
                case 7:
                    System.out.println("Ordenar por (1=Título / 2=Artista): ");
                    int crit = scanner.nextInt(); scanner.nextLine();
                    if (crit == 1) playlist.ordenarPorTitulo();
                    else playlist.ordenarPorArtista();
                    break;
                case 8:
                    System.out.print("Buscar por (título, artista ou álbum): ");
                    String termo = scanner.nextLine();
                    playlist.buscarMusica(termo);
                    break;
                case 0:
                    System.out.println("\n[SISTEMA] Encerrando player... Até logo!");
                    break;
                default:
                    System.out.println("[ERRO] Opção inválida!");
            }
        } while (opcao != 0);
        scanner.close();
    }
}
