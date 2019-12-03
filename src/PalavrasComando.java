/**
 * Classe que "contem" todos os comandos válidos do jogo. Armazena todos as
 * palavras comandos válidos e valida os comandos a serem analisados.
 */

public class PalavrasComando {
	private static final String[] comandosValidos = { "ir", "sair", "ajuda", "observar", "detonar" };

	public PalavrasComando() {
	}

	/**
	 * Metodo para verificar se o comando digitado é válido. Ele recebe o comando e
	 * verifica se é igual a algum dos comandos armazenados na classe.
	 * 
	 * @param umaString String correspondente ao comando digitado pelo usuário
	 * @return boolean - true se o comando for válido ou false caso contrário.
	 */
	public boolean ehComando(String umaString) {
		for (int i = 0; i < comandosValidos.length; i++) {
			if (comandosValidos[i].equals(umaString))
				return true;
		}

		return false;
	}

	/**
	 * Metodo para apresentar todos os comandos válidos.
	 * 
	 * @return comandos - vetor de comandos válidos.
	 */
	public String getComandos() {
		String comandos = "";
		for (String comando : comandosValidos) {
			comandos = comandos + comando + " ";
		}
		return comandos;
	}
}
