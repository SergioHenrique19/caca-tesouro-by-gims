import java.util.ArrayList;
import java.util.Random;

/**
 *  Essa eh a classe principal da aplicacao "World of Zull".
 *  "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 *  Usuarios podem caminhar em um cenario. E eh tudo! Ele realmente
 *  precisa ser estendido para fazer algo interessante!
 * 
 *  Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 *  "jogar".
 * 
 *  Essa classe principal cria e inicializa todas as outras: ela cria os
 *  ambientes, cria o analisador e comeca o jogo. Ela tambeme avalia e 
 *  executa os comandos que o analisador retorna.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */

public class Jogo 
{
	private Analisador analisador;
	private Ambiente ambienteAtual;
	private Random gerador;
	ArrayList<Ambiente> ambientes;
	int chave;
	int movimentos;
			
	/**
	 * Cria o jogo e incializa seu mapa interno.
	 */
	public Jogo() 
	{
		gerador = new Random();
		criarAmbientes();
		analisador = new Analisador();
		movimentos = gerador.nextInt(31) + 20;
		chave = 0;
	}

	/**
	 * Cria todos os ambientes e liga as saidas deles
	 */
	private void criarAmbientes()
	{
		// Ambiente escritorio, salaTV, salaDeJantar, cozinha, jardim, corredor;
		// Ambiente quarto1, quarto2, quarto3, quarto4, banheiro1, banheiro2;
		
		ambientes = new ArrayList<Ambiente>();
		
		// cria os ambientes
		// escritorio = new Ambiente(" no Escritório");
		// salaTV = new Ambiente(" na Sala de TV");
		// salaDeJantar = new Ambiente(" na Sala de Jantar");
		// cozinha = new Ambiente(" na Cozinha");
		// jardim = new Ambiente(" no Jardim");
		// corredor = new Ambiente(" no Corredor");
		
		// quarto1 = new Ambiente(" no Quarto 1");
		// quarto2 = new Ambiente(" no Quarto 2");
		// quarto3 = new Ambiente(" no Quarto 3");
		// quarto4 = new Ambiente(" no Quarto 4");
		// banheiro1 = new Ambiente(" no Banheiro 1");
		// banheiro2 = new Ambiente(" no Banheiro 2");
		
		ambientes.add(new Ambiente(" no Escritorio", "Escritorio"));
		ambientes.add(new Ambiente(" na Sala de TV", "Sala de TV"));
		ambientes.add(new Ambiente(" na Sala de Jantar", "Sala de Jantar"));
		ambientes.add(new Ambiente(" na Cozinha", "Cozinha"));
		ambientes.add(new Ambiente(" no Jardim", "Jardim"));
		ambientes.add(new Ambiente(" no Corredor", "Corredor"));
		ambientes.add(new Ambiente(" no Quarto 1", "Quarto 1"));
		ambientes.add(new Ambiente(" no Quarto 2", "Quarto 2"));
		ambientes.add(new Ambiente(" no Quarto 3", "Quarto 3"));
		ambientes.add(new Ambiente(" no Quarto 4", "Quarto 4"));
		ambientes.add(new Ambiente(" no Banheiro 1", "Banheiro 1"));
		ambientes.add(new Ambiente(" no Banheiro 2", "Banheiro 2"));
		
		
		
		// inicializa as saidas dos ambientes
		
		// escritorio.ajustarSaidas("Sala TV", salaTV);
		// salaTV.ajustarSaidas("Escritório", escritorio);
		// salaTV.ajustarSaidas("Sala de Jantar", salaDeJantar);
		// salaTV.ajustarSaidas("Jardim", jardim);
		// salaDeJantar.ajustarSaidas("Sala TV", salaTV);
		// salaDeJantar.ajustarSaidas("Cozinha", cozinha);
		// salaDeJantar.ajustarSaidas("Corredor", corredor);
		// cozinha.ajustarSaidas("Sala de Jantar", salaDeJantar);
		// cozinha.ajustarSaidas("Jardim", jardim);
		// jardim.ajustarSaidas("Sala TV", salaTV);
		// jardim.ajustarSaidas("Cozinha", cozinha);
		// corredor.ajustarSaidas("Sala de Jantar", salaDeJantar);
		// corredor.ajustarSaidas("Quarto 1", quarto1);
		// corredor.ajustarSaidas("Quarto 2", quarto2);
		// corredor.ajustarSaidas("Quarto 3", quarto3);
		// corredor.ajustarSaidas("Quarto 4", quarto4);
		// corredor.ajustarSaidas("Banheiro 1", banheiro1);
		// quarto1.ajustarSaidas("Corredor", corredor);
		// quarto2.ajustarSaidas("Corredor", corredor);
		// quarto3.ajustarSaidas("Corredor", corredor);
		// quarto3.ajustarSaidas("Banheiro 2", banheiro2);
		// quarto4.ajustarSaidas("Corredor", corredor);
		// banheiro1.ajustarSaidas("Corredor", corredor);
		// banheiro2.ajustarSaidas("Quarto 3", quarto3);
		
		// escritorio 0
		// salaTV 1
		// salaDeJantar 2
		// cozinha 3
		// jardim 4
		// corredor 5
		// quarto1 6
		// quarto2 7
		// quarto3 8
		// quarto4 9
		// banheiro1 10
		// banheiro2 11
		
		ambientes.get(0).ajustarSaidas("SalaTV", ambientes.get(1));
		ambientes.get(1).ajustarSaidas("Escritorio", ambientes.get(0));
		ambientes.get(1).ajustarSaidas("SalaJantar", ambientes.get(2));
		ambientes.get(1).ajustarSaidas("Jardim", ambientes.get(4));
		ambientes.get(2).ajustarSaidas("SalaTV", ambientes.get(1));
		ambientes.get(2).ajustarSaidas("Cozinha", ambientes.get(3));
		ambientes.get(2).ajustarSaidas("Corredor", ambientes.get(5));
		ambientes.get(3).ajustarSaidas("SalaJantar", ambientes.get(2));
		ambientes.get(3).ajustarSaidas("Jardim", ambientes.get(4));
		ambientes.get(4).ajustarSaidas("SalaTV", ambientes.get(1));
		ambientes.get(4).ajustarSaidas("Cozinha", ambientes.get(3));
		ambientes.get(5).ajustarSaidas("SalaJantar", ambientes.get(2));
		ambientes.get(5).ajustarSaidas("Quarto1", ambientes.get(6));
		ambientes.get(5).ajustarSaidas("Quarto2", ambientes.get(7));
		ambientes.get(5).ajustarSaidas("Quarto3", ambientes.get(8));
		ambientes.get(5).ajustarSaidas("Quarto4", ambientes.get(9));
		ambientes.get(5).ajustarSaidas("Banheiro1", ambientes.get(10));
		ambientes.get(6).ajustarSaidas("Corredor", ambientes.get(5));
		ambientes.get(7).ajustarSaidas("Corredor", ambientes.get(5));
		ambientes.get(8).ajustarSaidas("Corredor", ambientes.get(5));
		ambientes.get(8).ajustarSaidas("Banheiro2", ambientes.get(11));
		ambientes.get(9).ajustarSaidas("Corredor", ambientes.get(5));
		ambientes.get(10).ajustarSaidas("Corredor", ambientes.get(5));
		ambientes.get(11).ajustarSaidas("Quarto3", ambientes.get(8));

		ambienteAtual = ambientes.get(1);  // o jogo comeca do lado de fora
		
		int randomDica1 = gerador.nextInt(12);
		int randomDica2 = gerador.nextInt(12);
		int randomChave = gerador.nextInt(12);
		int randomTesouro = gerador.nextInt(12);
		
		ambientes.get(randomDica1).setDica1(true);
		ambientes.get(randomDica2).setDica2(true);
		ambientes.get(randomChave).setChave(true);
		ambientes.get(randomTesouro).setTesouro(true);
		
	}

	/**
	 *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
	 */
	public void jogar() 
	{            
		imprimirBoasVindas();

		// Entra no loop de comando principal. Aqui nos repetidamente lemos
		// comandos e os executamos ate o jogo terminar.
						
		boolean terminado = false;
		while (! terminado) {
			Comando comando = analisador.pegarComando();
			terminado = processarComando(comando);
		}
		System.out.println("Obrigado por jogar. Ate mais!");
	}
	
	private void imprimirLocalizacaoAtual(){
		System.out.println("Voce esta " + ambienteAtual.getDescricao());
		System.out.println("Voce tem " + movimentos + " movimentos.");
		System.out.print(ambienteAtual.getSaidas());
		System.out.println();
	}
	
	/**
	 * Imprime a mensagem de abertura para o jogador.
	 */
	private void imprimirBoasVindas()
	{
		System.out.println();
		System.out.println("Bem-vindo a Caca ao Tesouro!");
		System.out.println("Caca ao Tesouro e um novo jogo de aventura, incrivelmente bacana.");
		System.out.println("Digite 'ajuda' se voce precisar de ajuda.");
		System.out.println();
		
		imprimirLocalizacaoAtual();
	}

	/**
	 * Dado um comando, processa-o (ou seja, executa-o)
	 * @param comando O Comando a ser processado.
	 * @return true se o comando finaliza o jogo.
	 */
	private boolean processarComando(Comando comando) 
	{
		boolean querSair = false;

		if(comando.ehDesconhecido()) {
			System.out.println("Eu nao entendi o que você disse...");
			return false;
		}

		String palavraDeComando = comando.getPalavraDeComando();
		if (palavraDeComando.equals("ajuda")) {
			imprimirAjuda();
		}
		else if (palavraDeComando.equals("ir")) {
			if (movimentos > 0){
				irParaAmbiente(comando);
			}
			else {
				System.out.println("Parece que acabou seus movimentos, parceiro. Use a bomba voce tem uma chance.");
			}
		}
		else if (palavraDeComando.equals("sair")) {
			querSair = sair(comando);
		}
		else if (palavraDeComando.equals("observar")){
			observar();
		}
		else if (palavraDeComando.equals("detonar")){
			querSair = detonar();
		}

		return querSair;
	}

	// Implementacoes dos comandos do usuario

	/**
	 * Printe informacoes de ajuda.
	 * Aqui nos imprimimos algo bobo e enigmatico e a lista de 
	 * palavras de comando
	 */
	private void imprimirAjuda() 
	{
		System.out.println("Você esta perdido. Você está sozinho. Você caminha");
		System.out.println("pela casa.");
		System.out.println();
		System.out.println("Suas palavras de comando sao:");
		System.out.println(analisador.getComandos());
	}

	/** 
	 * Tenta ir em uma direcao. Se existe uma saida entra no 
	 * novo ambiente, caso contrario imprime mensagem de erro.
	 */
	private void irParaAmbiente(Comando comando) 
	{
		if(!comando.temSegundaPalavra()) {
			// se nao ha segunda palavra, nao sabemos pra onde ir...
			System.out.println("Ir pra onde?");
			return;
		}

		String direcao = comando.getSegundaPalavra();

		// Tenta sair do ambiente atual
		Ambiente proximoAmbiente = null;
		proximoAmbiente = ambienteAtual.getAmbiente(direcao);

		if (proximoAmbiente == null) {
			System.out.println("Nao ha passagem!");
		}
		
		else {
			
			boolean usouChave = false;
			
			if (chave != 0){
				System.out.println("Você deseja usar a chave-mestra? (sim/nao)");
				String confirmacao = analisador.pegarString();
				if (confirmacao.equals("sim")){
					usouChave = true;
					usarChave();
				}
			}
			
			if (!usouChave) {
				boolean chance = gerador.nextBoolean();
				
				if (!chance) {
					proximoAmbiente = ambienteAtual;
					System.out.println("A porta esta emperrada.");
				}
			}
			
			if (proximoAmbiente.getDica1()) {
				proximoAmbiente.setDica1(false);
				boolean tesouro = true;
				do {
					int ambienteEscolhido = gerador.nextInt(ambientes.size());
					if (!ambientes.get(ambienteEscolhido).getTesouro()){
						tesouro = false;
						System.out.println("O tesouro nao esta no(a) " + ambientes.get(ambienteEscolhido).getNome());
					}
				}while (tesouro);
			}
			
			if (proximoAmbiente.getDica2()) {
				proximoAmbiente.setDica2(false);
				for (Ambiente a : ambientes) {
					if (a.getTesouro()){
						int ambienteEscolhido = gerador.nextInt(a.getQuantidadeAmbientes());
						System.out.println("O tesouro esta proximo ao comodo " + a.getSaidaAleatoria(ambienteEscolhido));
					}
				}
			}
			
			if (proximoAmbiente.getChave()) {
				proximoAmbiente.setChave(false);
				int vidaChave = gerador.nextInt(12);
				chave = vidaChave;
				System.out.println("A chave tem " + (chave+1) + " usos");
			}
			
			
			
			ambienteAtual = proximoAmbiente;
			
			if(!usouChave) {
				movimentos--;
			}
			
			imprimirLocalizacaoAtual();
		}
		
	}
	
	private int usarChave() {
		
		if(chave == 1) {
			System.out.println("Voce utilizou a chave com sucesso. Infelizmente, ela quebrou, e voce nao podera usa-la novamente.");
		}
		
		else {
			System.out.println("Voce utilizou a chave com sucesso.");
		}
		
		chave--;
		
		return chave;
	}

	/** 
	 * "Sair" foi digitado. Verifica o resto do comando pra ver
	 * se nos queremos realmente sair do jogo.
	 * @return true, se este comando sai do jogo, false, caso contrario
	 */
	private boolean sair(Comando comando) 
	{
		if(comando.temSegundaPalavra()) {
			System.out.println("Sair o que?");
			return false;
		}
		else {
			return true;  // sinaliza que nos queremos sair
		}
	}
	
	private void observar(){
		imprimirLocalizacaoAtual();
	}
	
	private boolean detonar(){
		if (ambienteAtual.getTesouro()){
			System.out.println("Parabens, voce ganhou!!!");
		}
		else {
			System.out.println("Nao foi dessa vez...");
		}
		return true;
	}
}
