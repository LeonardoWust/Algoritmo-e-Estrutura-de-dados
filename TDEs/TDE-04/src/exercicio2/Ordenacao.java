package exercicio2;

public class Ordenacao {

    public static void insertionSort(Funcionario[] funcionarios) {

        for (int i = 1; i < funcionarios.length; i++) {

            Funcionario chave = funcionarios[i];
            int j = i - 1;

            while (j >= 0 &&
                    funcionarios[j].getSalarioFuncionario() > chave.getSalarioFuncionario()) {

                funcionarios[j + 1] = funcionarios[j];
                j--;
            }

            funcionarios[j + 1] = chave;
        }
    }

    public static void insertionSortD(Funcionario[] funcionarios) {

        for (int i = 1; i < funcionarios.length; i++) {

            Funcionario chave = funcionarios[i];
            int j = i - 1;

            while (j >= 0 &&
                    funcionarios[j].getSalarioFuncionario() < chave.getSalarioFuncionario()) {

                funcionarios[j + 1] = funcionarios[j];
                j--;
            }

            funcionarios[j + 1] = chave;
        }
    }

    public static void insertionSortPorNome(Funcionario[] funcionarios) {

        for (int i = 1; i < funcionarios.length; i++) {

            Funcionario chave = funcionarios[i];
            int j = i - 1;

            while (j >= 0 &&
                    funcionarios[j].getNomeFuncionario().compareTo(chave.getNomeFuncionario()) > 0) {

                funcionarios[j + 1] = funcionarios[j];
                j--;
            }

            funcionarios[j + 1] = chave;
        }
    }
}
