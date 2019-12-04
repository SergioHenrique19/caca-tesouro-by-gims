package principal;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe que representa um ambiente, isto é, um cenário do jogo. Cada ambiente
 * possui conexão com um ou mais ambientes, indicados como saídas.
 */
public class Ambiente {
    private String descricao;
    private String nome;
    private HashMap<String, Ambiente> saidas;
    private ArrayList<Item> itensVisiveis;
    private ArrayList<Item> itensOcultos;
    
    
    /**
     * Cria um ambiente a partir da "descricao". Inicialmente, não possui saídas.
     * 
     * @param descricao texto informativo do ambiente
     * @param nome      nome do ambiente a ser utilizado como chave no HashMap
     */
    public Ambiente(String descricao, String nome) {
        this.descricao = descricao + " da casa mal assombrada.";
        this.nome = nome;
        saidas = new HashMap<String, Ambiente>();
        itensVisiveis = new ArrayList<Item>();
        itensOcultos = new ArrayList<Item>();
    }
    
    
    /**
     * Método que configura as saídas do ambiente.
     * 
     * @param direcao  chave representativa do nome da saída para o próximo ambiente
     * @param ambiente valor representativo do objeto da saída do ambiente
     */
    public void ajustarSaidas(String direcao, Ambiente ambiente) {
        saidas.put(direcao, ambiente);
    }
    
    
    /**
     * Método que apresenta a descrição do ambiente.
     * 
     * @return Descrição do objeto Ambiente do local atual.
     */
    public String getDescricao(){
        return descricao;
    }
    
    /**
     * Método que obtem uma saída da localização (ambiente) atual do jogador com base
     * na direção passada por parâmetro.
     * 
     * @param direcao para onde se deseja ir a partir do ambiente em que se encontra o jogador
     * @return Ambiente alcançado ao seguir a direcao informada.
     */
    public Ambiente getAmbiente(String direcao) {
        return saidas.get(direcao);
    }
    
    /**
     * Método que apresenta/imprime as saídas da localização (ambiente) atual do
     * jogador.
     * 
     * @return Texto com as saídas do ambiente em que se encotra o jogador.
     */
    public String getSaidas() {
        String textoSaidas = "";
        for (String direcao : saidas.keySet()) {
            textoSaidas = textoSaidas + direcao + " ";
        }
        return textoSaidas;
    }
    
    /**
     * Método que "guarda" o nome do ambiente.
     * 
     * @return Nome do ambiente
     */
    public String getNome() {
        return this.nome;
    }
    
    
    /**
     * Método responsável por inserir um item visível no ambiente
     * 
     * @param item Um item a ser colocado à vista no ambiente
     */
    public void inserirItemVisivel(Item item){
        itensVisiveis.add(item);
    }
    
    /**
     * Método responsável por inserir um item oculto no ambiente
     * 
     * @param item Um item a ser escondido no ambiente
     */
    public void inserirItemOculto(Item item){
        itensOcultos.add(item);
    }
    
    /**
     * Método responsável por coletar um item visível do ambiente
     * 
     * @return Item que se encontra à vista no ambiente
     */
    public Item removeItemVisivel(){
        return itensVisiveis.remove(0);
    }
    
    /**
     * Método responsável por coletar um item oculto do ambiente
     * 
     * @return Item que se encontra escondido no ambiente
     */
    public Item removeItemOculto(){
        return itensOcultos.remove(0);
    }
    
    /**
     * Método que informa se existe algum item visível no ambiente
     * 
     * @return true se a lista de itens visiveis possuir algum elemento, ou false, caso contrário 
     */
    public boolean possuiItemVisivel(){
        return itensVisiveis.size() > 0;
    }
    
    /**
     * Método que informa se existe algum item oculto no ambiente
     * 
     * @return true se a lista de itens ocultos possuir algum elemento, ou false, caso contrário 
     */
    public boolean possuiItemOculto(){
        return itensOcultos.size() > 0;
    }
}
