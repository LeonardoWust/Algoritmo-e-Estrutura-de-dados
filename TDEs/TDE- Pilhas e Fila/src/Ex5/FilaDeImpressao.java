package Ex5;

import java.util.LinkedList;
import java.util.Queue;

class Documento {
    private String nome;
    private int numeroPaginas;

    public Documento(String nome, int numeroPaginas) {
        this.nome = nome;
        this.numeroPaginas = numeroPaginas;
    }

    public String getNome() {
        return nome;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    @Override
    public String toString() {
        return "Documento: " + nome + " | Páginas: " + numeroPaginas;
    }
}

public class FilaDeImpressao {
    public static void main(String[] args) {
        Queue<Documento> filaImpressao = new LinkedList<>();

        // Inserindo documentos na fila
        filaImpressao.add(new Documento("Relatorio_Financeiro.pdf", 15));
        filaImpressao.add(new Documento("Apresentacao_Marketing.pptx", 5));
        filaImpressao.add(new Documento("Planilha_Custos.xlsx", 2));
        filaImpressao.add(new Documento("Manual_do_Usuario.docx", 45));
        filaImpressao.add(new Documento("Contrato_Prestacao_Servicos.pdf", 8));

        int totalPaginasImpressas = 0;
        int impressoesRealizadas = 0;
        Documento documentoMaior = null;

        System.out.println("=== Iniciando Simulação de Impressão ===\n");

        // Simulando a impressão
        while (!filaImpressao.isEmpty()) {
            Documento docAtual = filaImpressao.poll(); // Desenfileira (remove do início)

            System.out.println("Imprimindo -> " + docAtual);

            totalPaginasImpressas += docAtual.getNumeroPaginas();
            impressoesRealizadas++;

            // Verificando o documento com mais páginas
            if (documentoMaior == null || docAtual.getNumeroPaginas() > documentoMaior.getNumeroPaginas()) {
                documentoMaior = docAtual;
            }

            // A cada duas impressões, informa o próximo da fila
            if (impressoesRealizadas % 2 == 0 && !filaImpressao.isEmpty()) {
                System.out.println("  [Aviso] O próximo documento a ser impresso será: " + filaImpressao.peek().getNome());
            }
        }

        // Exibindo o relatório final
        System.out.println("\n=== Resumo da Simulação ===");
        if (documentoMaior != null) {
            System.out.println("Documento com maior número de páginas: " + documentoMaior.getNome() + " (" + documentoMaior.getNumeroPaginas() + " páginas)");
        }
        System.out.println("Total de páginas impressas: " + totalPaginasImpressas);
    }
}
