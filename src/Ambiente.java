
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
/**
 * Classe Ambiente - um ambiente em um jogo adventure.
 *
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.  
 *
 * Um "Ambiente" representa uma localizacao no cenario do jogo. Ele eh
 * conectado aos outros ambientes atraves de saidas. As saidas sao
 * nomeadas como norte, sul, leste e oeste. Para cada direcao, o ambiente
 * guarda uma referencia para o ambiente vizinho, ou null se nao ha
 * saida naquela direcao.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */
public class Ambiente 
{
	private String descricao;
	private String nome;
	private HashMap<String, Ambiente> saidas;
	private ArrayList<String> nomeSaidas;
	private boolean dica1;
	private boolean dica2;
	private boolean chave;
	private boolean tesouro;
	

	/**
	 * Cria um ambiente com a "descricao" passada. Inicialmente, ele
	 * nao tem saidas. "descricao" eh algo como "uma cozinha" ou
	 * "
	 * Create a room described "description". Initially, it has
	 * no exits. "description" is something like "a kitchen" or
	 * "um jardim aberto".
	 * @param descricao A descricao do ambiente.
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
	 * Define as saidas do ambiente. Cada direcao ou leva a um
	 * outro ambiente ou eh null (nenhuma saida para la).
	 * @param norte A saida norte.
	 * @param leste A saida leste.
	 * @param sul A saida sul.
	 * @param oeste A saida oeste.
	 */
	public void ajustarSaidas(String direcao, Ambiente ambiente) {
		saidas.put(direcao, ambiente);
		nomeSaidas.add(ambiente.getNome());
	}

	/**
	 * @return A descricao do ambiente.
	 */
	public String getDescricao(){
		return descricao;
	}
		
	public Ambiente getAmbiente(String direcao) {
		return saidas.get(direcao);
	}
	
	public String getSaidas() {
		String textoSaidas = "";
		for (String direcao : saidas.keySet()) {
			textoSaidas = textoSaidas + direcao + " ";
		}
		return textoSaidas;
	}
	
	public String getSaidaAleatoria(int ambienteEscolhido) {
		String saida = nomeSaidas.get(ambienteEscolhido);
		return saida;
	}
	
	public boolean getDica1() {
		return dica1;
	}
	
	public boolean getDica2() {
		return dica2;
	}
	
	public boolean getChave() {
		return chave;
	}
	
	public boolean getTesouro() {
		return tesouro;
	}
	
	public void setDica1(boolean dica1) {
		this.dica1 = dica1;
	}
	
	public void setDica2(boolean dica2) {
		this.dica2 = dica2;
	}
	
	public void setChave(boolean chave) {
		this.chave = chave;
	}
	
	public void setTesouro(boolean tesouro) {
		this.tesouro = tesouro;
	}
	
	public int getQuantidadeAmbientes() {
		return nomeSaidas.size();
	}
	
	public String getNome() {
		return this.nome;
	}

}
