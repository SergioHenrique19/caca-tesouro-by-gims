/**
 * Classe que armazena informações sobre um comando que foi digitado pelo
 * usuário. O comando (String) pode possuir uma palavra ou duas.
 */
public class Comando {
    private String palavraDeComando;
    private String segundaPalavra;

    /**
     * Cria um objeto Comando. As palavras devem ser fornecidas pelo usuário, mas
     * podem ser nulas.
     * 
     * @param primeiraPalavra primeira palavra do comando
     * @param segundaPalavra  segunda palavra do comando
     */
    public Comando(String primeiraPalavra, String segundaPalavra) {
        palavraDeComando = primeiraPalavra;
        this.segundaPalavra = segundaPalavra;
    }

    /**
     * Retorna a primeira palavra do comando digitado.
     * 
     * @return Primeira palavra do comando
     */
    public String getPalavraDeComando() {
        return palavraDeComando;
    }

    /**
     * Retorna a segunda palavra do comando digitado.
     * 
     * @return Segunda palavra do comando ou null, caso não exista
     */
    public String getSegundaPalavra() {
        return segundaPalavra;
    }

    /**
     * Verifica se a primeira palavra é desconhecida (null).
     * 
     * @return true se a palavra for desconhecida ou false, caso contrário.
     */
    public boolean ehDesconhecido() {
        return (palavraDeComando == null);
    }

    /**
     * Verifica se o comando digitado possui segunda palavra.
     * 
     * @return true se houver segunda palavra ou false, caso contrário.
     */
    public boolean temSegundaPalavra() {
        return (segundaPalavra != null);
    }
}
