package principal;

import java.util.Scanner;

/**
 * Classe responsável por analisar o que foi digitado pelo usuário e retorna um
 * objeto do tipo Comando, caso seja válido, e, caso contrário, retorna um
 * objeto Comando marcado como inválido (primeira palavra é null). Quando chamado, 
 * lê uma linha do terminal e tenta interpretar como um comando do jogo.
 */
public class Analisador 
{
    private PalavrasComando palavrasDeComando;  
    private Scanner entrada;        

    /**
     * Cria um analisador para ler o que foi digitado no terminal.
     */
    public Analisador() 
    {
        palavrasDeComando = new PalavrasComando();
        entrada = new Scanner(System.in);
    }

    /**
     * Metodo que analisa o comando digitado pelo usuário e verifica se é válido.
     * Inicialmente, pega a primeira linha e analisa se tem uma ou duas palavras. Em
     * seguida, verifica se a primeira palavra digitada é um comando válido.
     * 
     * @return Objeto Comando válido se a primeira palavra for comando válido, ou
     *         Comando desconhecido se a primeira não for válida.
     */
    public Comando pegarComando() 
    {
        String linha;   
        String palavra1 = null;
        String palavra2 = null;

        System.out.print("> ");     

        linha = entrada.nextLine();

        // Verifica se tem uma ou duas palavras, e ignora o resto da linha
        Scanner tokenizer = new Scanner(linha);
        if(tokenizer.hasNext()) {
            palavra1 = tokenizer.next();      
            if(tokenizer.hasNext()) {
                palavra2 = tokenizer.next();     
            }
        }

        // Agora verifica se esta palavra eh conhecida. Se for, cria um
        // com ela. Se nao, cria um comando "null" (para comando desconhecido)
        tokenizer.close();

        // Verifica se a primeira palavra é um comando válido. Se for, cria um comando válido.
        // Se não, cria um comando com "null" na primeira palavra (representando comando desconhecido)
        if(palavrasDeComando.ehComando(palavra1)) {
            return new Comando(palavra1, palavra2);
        }

        else {
            return new Comando(null, palavra2); 
        }


    }
    
    /**
     * Método que recebe comando (String) digitada pelo usuário para analisar.
     * 
     * @return Comando (String) digitado pelo usuário
     */
    public String pegarString(){
        return entrada.nextLine();
    }

    /**
     * Método que apresenta os comandos válidos para o usuário.
     * 
     * @return Vetor dos comandos válidos
     */
    public String getComandos(){
        return palavrasDeComando.getComandos();
    }
	
}
