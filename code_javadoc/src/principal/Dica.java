package principal;

/**
 * Classe responsável por armazenar dicas sobre a localização do tesouro.
 * Implementa a interface Item.
 */
public class Dica implements Item{
    private String dica;
    
    /**
     * Cria a dica recebendo seu conteúdo como parâmetro.
     * 
     * @param dica Texto contendo a dica.
     */
    public Dica(String dica){
        this.dica = dica;
    }
    
    /**
     * Método responsável por retornar a descrição da dica.
     * Sobrescreve o método abstrato da classe Item.
     * 
     * @return String contendo a descrição da Dica.
     */
    @Override
    public String getDescricao() {
        return "Um pedaço de papel onde está escrita a mensagem: " + getDica();
    }
    
    /**
     * Método responsável por retornar o conteúdo da dica.
     * 
     * @return Texto contendo a dica.
     */
    public String getDica() {
        return dica;
    }
      
}
