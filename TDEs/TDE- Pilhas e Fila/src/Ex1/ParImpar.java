package Ex1;

public class ParImpar {
    public Pilha impar;
    public Pilha par;

    public ParImpar(int capacidade){
        this.par = new Pilha(capacidade);
        this.impar = new Pilha(capacidade);
    }

    public void processar(int numero){
        if (numero == 0){
            desempilharAmbas();
        } else if (numero % 2 == 0){
            par.empilhar(numero);
        } else {
            impar.empilhar(numero);
        }
    }

    public void desempilharAmbas(){
        if( par.estaVazia() || impar.estaVazia()){
            System.out.println("As duas Pilhas estão vazias!");
        }else {
            par.desempilhar();
            impar.desempilhar();
        }
    }

    public void imprimirTudo(){
        System.out.println("\n -- Ex1.Pilha PAR -- ");
        while (!par.estaVazia()) System.out.println(par.desempilhar());

        System.out.println("\n -- Ex1.Pilha IMPAR -- ");
        while(!par.estaVazia()) System.out.println(impar.desempilhar());
    }
}
