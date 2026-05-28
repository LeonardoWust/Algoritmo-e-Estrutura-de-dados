public class Professor extends Pessoa {
    private String matricula;

    public Professor() {
        super(nome, telefone);
    }

    public void falar (){
        System.out.println("O professor " + super.getNome() + super.getTelefone());
    }
}
