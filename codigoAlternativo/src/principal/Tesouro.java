package principal;

/**
 * Classe que representa um tesouro. 
 * Um tesouro é um item valioso que possui um valor em pontos.
 * Implementa a interface Item.
 */
public class Tesouro implements Item{
    private int valor;
    
    /**
     * Cria um tesouro com o valor informado.
     * 
     * @param valor Valor em pontos a ser atribuído ao tesouro.
     */
    public Tesouro(int valor) {
        this.valor = valor;
    }
    
    /**
     * Método responsável por retornar a descrição do tesouro.
     * Sobrescreve o método abstrato da classe Item.
     * 
     * @return String contendo a descrição do tesouro.
     */
    @Override
    public String getDescricao() {
        return "Um tesouro cujo valor é: " + getValor();
    }
    
    /**
     * Método responsável por retornar o valor do tesouro.
     * 
     * @return Inteiro representando o valor em pontos do tesouro.
     */
    public int getValor() {
        return valor;
    }
 
}
