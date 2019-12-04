package principal;

/**
 * Classe que representa um tesouro. 
 * Um tesouro é um item valioso que possui um valor em pontos.
 * Implementa a interface Item.
 */
public class Tesouro implements Item{
    private int valor;

    public Tesouro(int valor) {
        this.valor = valor;
    }

    @Override
    public String getDescricao() {
        return "Um tesouro cujo valor é: " + getValor();
    }

    public int getValor() {
        return valor;
    }
 
}
