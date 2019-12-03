import java.util.ArrayList;
import java.util.Random;

/**
 * Classe principal que representa o jogo de caça ao tesouro, onde o jogador faz
 * uso de todos os recursos do jogo. Esta classe cria e inicializa todos as
 * outras: cria os ambientes, cria o analisador, configura as dicas, tesouro e
 * chave mestra. Também, avalia e executa os comandos que o analisador retorna.
 */
public class Jogo {
	private Analisador analisador;
	private Ambiente ambienteAtual;
	private Random gerador;
	ArrayList<Ambiente> ambientes;
	int chave;
	int movimentos;

	/**
	 * Cria o jogo e inicializa todo o mapa e o analisador de comandos. Também,
	 * configura, aleatoriamente, as tentavivas do jogador.
	 */
	public Jogo() {
		gerador = new Random();
		criarAmbientes();
		analisador = new Analisador();
		movimentos = gerador.nextInt(31) + 20;
		chave = 0;
	}

	/**
	 * Método que configura todos os ambientes e ajusta suas saídas. Além disso,
	 * configura as localizações dos itens: dicas, chave mestra e tesouro.
	 */
	public void criarAmbientes() {
		ambientes = new ArrayList<Ambiente>();

		// Cria os ambientes e adiciona-os ao HashMap
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

		// Configura as saídas dos ambientes
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

		// Configura o ambiente em que inicia-se o jogador
		ambienteAtual = ambientes.get(1);

		// Configura a localização/ambientes dos itens
		int randomDica1 = gerador.nextInt(12);
		int randomDica2 = gerador.nextInt(12);
		int randomChave = gerador.nextInt(12);
		int randomTesouro = gerador.nextInt(12);

		// Adiciona os itens aos seus ambientes
		ambientes.get(randomDica1).setDica1(true);
		ambientes.get(randomDica2).setDica2(true);
		ambientes.get(randomChave).setChave(true);
		ambientes.get(randomTesouro).setTesouro(true);
	}

	/**
	 * Rotina principal do jogo. Permanece em loop até que o jogo termine.
	 */
	public void jogar() {
		imprimirBoasVindas();

		// Loop que analisa os comandos digitados pelo jogador
		boolean terminado = false;
		while (!terminado) {
			Comando comando = analisador.pegarComando();
			terminado = processarComando(comando);
		}

		System.out.println("Obrigado por jogar. Ate mais!");
	}

	/**
	 * Método que apresenta a localização/ambiente em que o jogador se encontra.
	 */
	public void imprimirLocalizacaoAtual() {
		System.out.println("Voce esta " + ambienteAtual.getDescricao());
		System.out.println("Voce tem " + movimentos + " movimentos.");
		System.out.print(ambienteAtual.getSaidas());
		System.out.println();
	}

	/**
	 * Método que apresenta a mensagem de boas-vindas do jogo ao jogador.
	 */
	public void imprimirBoasVindas() {
		System.out.println();
		System.out.println("Bem-vindo a Caca ao Tesouro!");
		System.out.println("Caca ao Tesouro e um novo jogo de aventura, incrivelmente bacana.");
		System.out.println("Digite 'ajuda' se voce precisar de ajuda.");
		System.out.println();

		imprimirLocalizacaoAtual();
	}

	/**
	 * Método que processa o comando, analisando se é um dos comandos válidos e
	 * realizando a ação configurar para tal comando válido.
	 * 
	 * @param comando Comando digitado pelo jogador
	 * @return true, se o comando for válido, ou false, caso contrário
	 */
	public boolean processarComando(Comando comando) {
		boolean querSair = false;

		if (comando.ehDesconhecido()) {
			System.out.println("Eu nao entendi o que você disse...");
			return false;
		}

		String palavraDeComando = comando.getPalavraDeComando();
		if (palavraDeComando.equals("ajuda")) {
			imprimirAjuda();
		} else if (palavraDeComando.equals("ir")) {
			if (movimentos > 0) {
				irParaAmbiente(comando);
			} else {
				System.out.println("Parece que acabou seus movimentos, parceiro. Use a bomba voce tem uma chance.");
			}
		} else if (palavraDeComando.equals("sair")) {
			querSair = sair(comando);
		} else if (palavraDeComando.equals("observar")) {
			observar();
		} else if (palavraDeComando.equals("detonar")) {
			querSair = detonar();
		}

		return querSair;
	}

	/**
	 * Método que imprime ajuda quando o usuário digita "ajuda", apresentando os
	 * comandos válidos/disponíveis.
	 */
	public void imprimirAjuda() {
		System.out.println("Você esta perdido. Você está sozinho. Você caminha");
		System.out.println("pela casa.");
		System.out.println();
		System.out.println("Suas palavras de comando sao:");
		System.out.println(analisador.getComandos());
	}

	/**
	 * Método que analisa a segunda palavra do comando, representando para qual
	 * ambiente o jogador deseja ir, se for possível. Também, verifica se o jogador
	 * fará uso da chave mestra e se a porta está emperrada ou não.
	 * 
	 * @param comando Ambiente para qual o usuário deseja ir
	 */
	public void irParaAmbiente(Comando comando) {
		if (!comando.temSegundaPalavra()) {
			System.out.println("Ir pra onde?");
			return;
		}

		String direcao = comando.getSegundaPalavra();

		Ambiente proximoAmbiente = null;
		proximoAmbiente = ambienteAtual.getAmbiente(direcao);

		if (proximoAmbiente == null) {
			System.out.println("Nao ha passagem!");
		} else {
			boolean usouChave = false;

			// Controla o uso da chave mestra
			if (chave != 0) {
				System.out.println("Você deseja usar a chave-mestra? (sim/nao)");
				String confirmacao = analisador.pegarString();
				if (confirmacao.equals("sim")) {
					usouChave = true;
					usarChave();
				}
			}

			// Caso o jogador não use a chave mestra, verificar, aleatoriamente, se a porta
			// está emperrada ou não
			if (!usouChave) {
				boolean chance = gerador.nextBoolean();

				if (!chance) {
					proximoAmbiente = ambienteAtual;
					System.out.println("A porta esta emperrada.");
				}
			}

			// Caso o ambiente possua a dica 1, apresentar ao jogador e configurar sua
			// mensagem
			if (proximoAmbiente.getDica1()) {
				proximoAmbiente.setDica1(false);
				boolean tesouro = true;
				do {
					int ambienteEscolhido = gerador.nextInt(ambientes.size());
					if (!ambientes.get(ambienteEscolhido).getTesouro()) {
						tesouro = false;
						System.out.println("O tesouro nao esta no(a) " + ambientes.get(ambienteEscolhido).getNome());
					}
				} while (tesouro);
			}

			// Caso o ambiente possua a dica 2, apresentar ao jogador e configurar sua
			// mensagem
			if (proximoAmbiente.getDica2()) {
				proximoAmbiente.setDica2(false);
				for (Ambiente a : ambientes) {
					if (a.getTesouro()) {
						int ambienteEscolhido = gerador.nextInt(a.getQuantidadeAmbientes());
						System.out.println("O tesouro esta proximo ao comodo " + a.getSaidaAleatoria(ambienteEscolhido));
					}
				}
			}

			// Caso o ambiente possua a chave mestra, apresentá-la ao jogador
			if (proximoAmbiente.getChave()) {
				proximoAmbiente.setChave(false);
				int vidaChave = gerador.nextInt(12);
				chave = vidaChave;
				System.out.println("A chave tem " + (chave + 1) + " usos");
			}

			ambienteAtual = proximoAmbiente;

			// Caso o jogador não usar a chave, descontar 1 ponto dos movimentos permitidos
			if (!usouChave) {
				movimentos--;
			}

			imprimirLocalizacaoAtual();
		}

	}

	/**
	 * Método que controla o uso da chave mestra, verificando se ela ainda pode ser
	 * utilizada, se puder, descontar do número de tentativas permitidas de uso.
	 * 
	 * @return Desconta 1 do número de tentativas do uso da chave mestra
	 */
	public int usarChave() {
		if (chave == 1) {
			System.out
					.println("Voce utilizou a chave com sucesso. Infelizmente, ela quebrou, e voce nao podera usa-la novamente.");
		} else {
			System.out.println("Voce utilizou a chave com sucesso.");
		}

		chave--;

		return chave;
	}

	/**
	 * Método que valida a saída do jogador do jogo.
	 * 
	 * @param comando Comando a ser analisado
	 * @return true, se o comando digitado for "sair", ou false, caso contrário
	 */
	public boolean sair(Comando comando) {
		if (comando.temSegundaPalavra()) {
			System.out.println("Sair o que?");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Método que apresenta descrição do ambiente atual para o usuário ao digitar
	 * "obervar".
	 */
	public void observar() {
		imprimirLocalizacaoAtual();
	}

	/**
	 * Método responsável pelo uso da dinamite, utilizada para encontrar o tesouro,
	 * caso o jogador esteja no ambiente indicado.
	 * 
	 * @return true, se o jogador encontrou o tesouro, ou false, caso contrário
	 */
	public boolean detonar() {
		if (ambienteAtual.getTesouro()) {
			System.out.println("Parabens, voce ganhou!!!");
		} else {
			System.out.println("Nao foi dessa vez...");
		}
		return true;
	}
}
