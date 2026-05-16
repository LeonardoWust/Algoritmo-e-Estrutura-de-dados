package Ex3;

public class VerificarParentesis {
    private char[] dados;
    private int topo;

    public VerificarParentesis(int cap) {
        dados = new char[cap];
        topo = -1;
    }

    public void empilhar(char c) { dados[++topo] = c; }
    public char desempilhar() { return dados[topo--]; }
    public boolean estaVazia() { return topo == -1; }

    public static boolean estaBalanceado(String exp) {
        VerificarParentesis p = new VerificarParentesis(exp.length());
        for (char c : exp.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') p.empilhar(c);
            else if (c == ')' || c == ']' || c == '}') {
                if (p.estaVazia()) return false;
                char t = p.desempilhar();
                if ((c == ')' && t != '(') || (c == ']' && t != '[') || (c == '}' && t != '{')) return false;
            }
        }
        return p.estaVazia();
    }
}

