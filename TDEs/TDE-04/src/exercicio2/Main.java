package exercicio2;

public class Main {

    public static void main(String[] args) {

        Funcionario[] funcionarios = {
                new Funcionario("Carlos", 4000),
                new Funcionario("Ana", 2500),
                new Funcionario("João", 3000),
                new Funcionario("Maria", 2000)
        };

        System.out.println("Antes da ordenacao:");

        for (int i = 0; i < funcionarios.length; i++) {
            System.out.println(funcionarios[i].getNomeFuncionario() + " - " +
                    funcionarios[i].getSalarioFuncionario());
        }

        Ordenacao.insertionSortPorNome(funcionarios);

        System.out.println("\nDepois da ordenacao:");

        for (int i = 0; i < funcionarios.length; i++) {
            System.out.println(funcionarios[i].getNomeFuncionario() + " - " +
                    funcionarios[i].getSalarioFuncionario());
        }
    }
}