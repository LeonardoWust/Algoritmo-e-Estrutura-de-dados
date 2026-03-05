package Exercicio1;

public class Teste {
    public static void main(String[] args) {
        Vetor jogadores = new Vetor(5);
        jogadores.inserirNoFim("Ronaldo");
        jogadores.inserirNoFim("Messi");
        jogadores.inserirNoFim("Romário");
        jogadores.inserirNoFim("Neymar");

//        System.out.println(jogadores.obter(3));
//        System.out.println(jogadores.tamanho());
//        jogadores.aumentarCapacidade();

//        String removido = jogadores.remover(1);
//        System.out.println(jogadores.remover(3));

//        jogadores.alterarElemento(2, "CR7");
//
//        System.out.println(jogadores);


        jogadores.estaVazio();


    }
}
