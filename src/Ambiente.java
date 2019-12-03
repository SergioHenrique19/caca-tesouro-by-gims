import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Classe que representa um ambiente, isto é, um cenário do jogo. Cada ambiente
 * possui conexão com um ou mais ambientes, indicados como saídas.
 */
public class Ambiente {
	private String descricao;
	private String nome;
	private HashMap<String, Ambiente> saidas;
	private ArrayList<String> nomeSaidas;
	private boolean dica1;
	private boolean dica2;
	private boolean chave;
	private boolean tesouro;

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
		nomeSaidas = new ArrayList<String>();
		dica1 = false;
		dica2 = false;
		chave = false;
		tesouro = false;
	}

	/**
	 * Método que configura as saídas do ambiente.
	 * 
	 * @param direcao  chave representativa do nome da saída para o próximo ambiente
	 * @param ambiente valor representativo do objeto da saída do ambiente
	 */
	public void ajustarSaidas(String direcao, Ambiente ambiente) {
		saidas.put(direcao, ambiente);
		nomeSaidas.add(ambiente.getNome());
	}

	/**
	 * Método que apresenta a descrição do ambiente.
	 * 
	 * @return Descrição do objeto Ambiente do local atual.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Método que obtem as saídas da localização (ambiente) atual do jogador.
	 * 
	 * @param direcao referência do ambiente atual
	 * @return Vetor das saídas do ambiente em que se encontra o jogador.
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
	 * Método que configurará a mensagem dizendo um ambiente próximo ao ambiente em
	 * que se encontra o tesouro.
	 * 
	 * @param ambienteEscolhido Ambiente onde está localizado o tesouro
	 * @return Aletoriamente, um dos ambientes que é saída do ambiente em que se
	 *         encontra o tesouro.
	 */
	public String getSaidaAleatoria(int ambienteEscolhido) {
		String saida = nomeSaidas.get(ambienteEscolhido);
		return saida;
	}

	/**
	 * Método que "armazena" se a "dica 1" está neste ambiente.
	 * 
	 * @return true se a dica 1 está neste ambiente ou false, caso contrário.
	 */
	public boolean getDica1() {
		return dica1;
	}

	/**
	 * Método que "armazena" se a "dica 2" está neste ambiente.
	 * 
	 * @return true se a dica 2 está neste ambiente ou false, caso contrário.
	 */
	public boolean getDica2() {
		return dica2;
	}

	/**
	 * Método que "armazena" se a chave mestra está neste ambiente.
	 * 
	 * @return true se a chave mestra está neste ambiente ou false, caso contrário.
	 */
	public boolean getChave() {
		return chave;
	}

	/**
	 * Método que "armazena" se o tesouro está neste ambiente.
	 * 
	 * @return true se o tesouro está neste ambiente ou false, caso contrário.
	 */
	public boolean getTesouro() {
		return tesouro;
	}

	/**
	 * Método que configura se a dica 1 estará neste ambiente.
	 * 
	 * @param dica1 true, se a dica 1 estará no ambiente, ou false, caso contrário
	 */
	public void setDica1(boolean dica1) {
		this.dica1 = dica1;
	}

	/**
	 * Método que configura se a dica 2 estará neste ambiente.
	 * 
	 * @param dica2 true, se a dica 2 estará no ambiente, ou false, caso contrário
	 */
	public void setDica2(boolean dica2) {
		this.dica2 = dica2;
	}

	/**
	 * Método que configura se a chave mestra estará neste ambiente.
	 * 
	 * @param chave true, se a chave estará no ambiente, ou false, caso contrário
	 */
	public void setChave(boolean chave) {
		this.chave = chave;
	}

	/**
	 * Método que configura se o tesouro estará neste ambiente.
	 * 
	 * @param tesouro true, se o tesouro estará no ambiente, ou false, caso
	 *                contrário
	 */
	public void setTesouro(boolean tesouro) {
		this.tesouro = tesouro;
	}

	/**
	 * Método que "guarda" a quantidade de ambientes do jogo.
	 * 
	 * @return Quantidade de ambientes do jogo
	 */
	public int getQuantidadeAmbientes() {
		return nomeSaidas.size();
	}

	/**
	 * Método que "guarda" o nome do ambiente.
	 * 
	 * @return Nome do ambiente
	 */
	public String getNome() {
		return this.nome;
	}
}
