import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Ambiente {
	private String descricao;
	private String nome;
	private HashMap<String, Ambiente> saidas;
	private ArrayList<String> nomeSaidas;
	private boolean dica1;
	private boolean dica2;
	private boolean chave;
	private boolean tesouro;

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

	public void ajustarSaidas(String direcao, Ambiente ambiente) {
		saidas.put(direcao, ambiente);
		nomeSaidas.add(ambiente.getNome());
	}

	public String getDescricao() {
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
