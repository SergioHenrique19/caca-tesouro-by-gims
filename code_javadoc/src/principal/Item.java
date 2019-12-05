package principal;

/**
 * Interface a ser implementada por todas as classes que representarem um item do jogo.
 * Todo item possui uma descrição, e cada item deve ser responsável por implementar 
 * o retorno de sua própria descrição.
 */

public interface Item {
    /**
     * Método abstrato que retorna uma descrição do item.
     * Todo item possui sua própria descrição e deve implementá-la.
     * 
     * @return String contendo a descrição do item
     */
    public abstract String getDescricao();
    
}
