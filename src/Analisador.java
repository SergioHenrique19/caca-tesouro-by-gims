import java.util.Scanner;

public class Analisador {
	private PalavrasComando palavrasDeComando;
	private Scanner entrada;

	public Analisador() {
		palavrasDeComando = new PalavrasComando();
		entrada = new Scanner(System.in);
	}

	public Comando pegarComando() {
		String linha;
		String palavra1 = null;
		String palavra2 = null;

		System.out.print("> ");

		linha = entrada.nextLine();

		Scanner tokenizer = new Scanner(linha);

		if (tokenizer.hasNext()) {
			palavra1 = tokenizer.next();
			if (tokenizer.hasNext()) {
				palavra2 = tokenizer.next();
			}
		}

		tokenizer.close();

		if (palavrasDeComando.ehComando(palavra1)) {
			return new Comando(palavra1, palavra2);
		}

		else {
			return new Comando(null, palavra2);
		}
	}

	public String pegarString() {
		return entrada.nextLine();
	}

	public String getComandos() {
		return palavrasDeComando.getComandos();
	}
}
