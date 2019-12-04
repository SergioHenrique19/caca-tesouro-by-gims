package principal;

/**
 * Classe que representa uma chave mestra capaz de abrir qualquer porta. 
 * A chave possui uma vida util, que indica quantas vezes ela pode ser usada.
 * Implementa a interface Item.
 */
public class ChaveMestra implements Item{
    private int vidaUtil;
    
    /**
     * Cria a chave mestra recebendo sua vida útil por parâmetro.
     * 
     * @param vidaUtil Representa o número de usos que a chave possui.
     */
    public ChaveMestra(int vidaUtil){
        this.vidaUtil = vidaUtil;
    }
    
    /**
     * Método responsável por retornar a descrição da chave mestra.
     * Sobrescreve o método abstrato da classe Item.
     * 
     * @return String contendo a descrição da chave mestra.
     */
    @Override
    public String getDescricao() {
        return "Uma chave capaz de abrir qualquer porta. Possui " + getVidaUtil() + " uso(s) restante(s)";
    }
    
    /**
     * Método responsável por retornar a vida útil da chave mestra.
     * 
     * @return Vida útil da chave mestra.
     */
    public int getVidaUtil() {
        return vidaUtil;
    }
    
    
}
