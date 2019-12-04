package principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Classe principal que representa o jogo de caça ao tesouro, onde o jogador faz
 * uso de todos os recursos do jogo. Esta classe cria e inicializa todos as
 * outras: cria os ambientes, cria o analisador, configura as dicas, tesouro e
 * chave mestra. Também, avalia e executa os comandos que o analisador retorna.
 */
public class Jogo {
    private TelaJogo tela;
    private Analisador analisador;
    private Ambiente ambienteAtual;
    private Random gerador;
    private ArrayList<Ambiente> ambientes;
    private int vidaUtilChave;
    private int movimentos;
    /**
     * Cria o jogo e inicializa todo o mapa, o analisador de comandos e a interface gráfica. Também,
     * configura, aleatoriamente, as tentativas do jogador.
     */
    public Jogo(){
        tela = new TelaJogo();
        gerador = new Random();
        analisador = new Analisador();
        ambientes = new ArrayList<Ambiente>();
        criarAmbientes();
        movimentos = gerador.nextInt(31) + 20; 
        tela.setTentativas(Integer.toString(movimentos));
        vidaUtilChave = 0; 
        tela.setUsosChave(Integer.toString(vidaUtilChave));
    }

    
    /**
     * Método que configura todos os ambientes e ajusta suas saídas. Além disso,
     * configura as localizações dos itens: dicas, chave mestra e tesouro.
     */
    private void criarAmbientes(){
        //Cria os ambientes e adiciona-os ao HashMap
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
        ajustarSaidasAmbientes();
        
        //Configura o ambiente em que inicia-se o jogo
        ambienteAtual = ambientes.get(1);
        
        espalharItens();
    }

    /**
     * Método auxiliar que ajusta as saídas de cada ambiente, as associando aos ambientes
     * adjacentes ao ambiente em questão
     */
    private void ajustarSaidasAmbientes(){
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
    }

    /**
     * Método que cria os itens e configura suas localizações. Também salva em arquivo
     * a localização dos itens criados.
     */
    private void espalharItens(){
        
        Ambiente ambienteTesouro;
        String saidasAmbiente[];
        //usado para referenciar os itens criados
        Item item;
        //armazena o número de ambientes do jogo
        int numAmbientes = ambientes.size();
        //recebe um número aleatório de 0 a numAmbientes
        int posicao;
        //nome do arquivo a ser escrito
        String nomeArquivo = "DadosJogo";
        //recebe o texto a ser escrito no arquivo
        String textoArquivo = "";
        
        //Criando o tesouro
        item = new Tesouro(1000);
        posicao = gerador.nextInt(numAmbientes);
        ambienteTesouro = ambientes.get(posicao);
        ambienteTesouro.inserirItemOculto(item);
        textoArquivo += "O Tesouro está no(a): " + ambienteTesouro.getNome() + "\r\n";
        
        
        //Criando dica de onde o tesouro está perto.
        //Pega o nome de cada ambiente adjacente, os divide em diferentes Strings e coloca as Strings no vetor
        saidasAmbiente = ambienteTesouro.getSaidas().split(" ");
        //Pega um nome aleatório e coloca na dica
        item = new Dica("O tesouro está próximo ao(à) " + saidasAmbiente[gerador.nextInt(saidasAmbiente.length)] + ".");
        posicao = gerador.nextInt(numAmbientes);
        ambientes.get(posicao).inserirItemVisivel(item);
        textoArquivo += "A Dica 'tesouro está perto de' está no(a): " + ambientes.get(posicao).getNome() + "\r\n";

        
        //Criando dica de onde o tesouro não está.
        do {
            posicao = gerador.nextInt(numAmbientes);
        } while(ambienteTesouro == ambientes.get(posicao));
        item = new Dica("O tesouro não está no(a) " + ambientes.get(posicao).getNome() + ".");
        posicao = gerador.nextInt(numAmbientes);
        ambientes.get(posicao).inserirItemVisivel(item);
        textoArquivo += "A Dica 'tesouro não está' está no(a): " + ambientes.get(posicao).getNome() + "\r\n";
        
        
        
        //Criando a chave mestra
        item = new ChaveMestra(gerador.nextInt(12) + 1); //soma 1 pois o número aleatório vai de 0 a 11
        posicao = gerador.nextInt(numAmbientes);
        ambientes.get(posicao).inserirItemVisivel(item);
        textoArquivo += "A Chave Mestra está no(a): " + ambientes.get(posicao).getNome() + "\r\n";
        
        escreverArquivo(nomeArquivo, textoArquivo);
    }
    
    /**
     * Rotina principal do jogo. Permanece em loop até que o jogo termine.
     */
    public void jogar() 
    {            
        tela.exibir();
        imprimirBoasVindas();
        
        //Loop que analisa os comandos digitados pelo jogador
        boolean terminado = false;
        while (!terminado) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }
        System.out.println("Obrigado por jogar. Ate mais!");
        
        //tela.fechar();
    }
    
    /**
     * Método que apresenta a localização/ambiente em que o jogador se encontra.
     */
    private void imprimirLocalizacaoAtual(){
        System.out.println("Voce esta " + ambienteAtual.getDescricao());
        System.out.println("Voce tem " + movimentos + " movimentos.");
        System.out.print("Saidas: ");
        System.out.println(ambienteAtual.getSaidas());
    }
    
    /**
     * Método que apresenta a mensagem de boas-vindas do jogo ao jogador.
     */
    private void imprimirBoasVindas()
    {
        System.out.println();
        System.out.println("Bem-vindo ao Caca ao Tesouro!");
        System.out.println("Caca ao Tesouro e um novo jogo de aventura, incrivelmente bacana.");
        System.out.println("Digite 'ajuda' se voce precisar de ajuda.");
        System.out.println();

        imprimirLocalizacaoAtual();
    }
    
    /**
     * Método que processa o comando, analisando se é um dos comandos válidos e
     * realizando a ação configurar para tal comando válido.
     * 
     * @param comando Comando gerado pelo analisador com base no que o jogador digitou
     * @return true, se o processamento do comando determinar o fim do jogo, ou false, caso contrário
     */
    private boolean processarComando(Comando comando) 
    {
        boolean querSair = false;

        if(comando.ehDesconhecido()) {
            System.out.println("Eu nao entendi o que voce disse...");
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();
        switch (palavraDeComando) {
            case "ajuda":
                imprimirAjuda();
                break;
            case "ir":
                if (movimentos > 0){
                    irParaAmbiente(comando);
                }
                else {
                    System.out.println("Parece que seus movimentos acabaram, parceiro. Use a bomba, voce tem uma chance.");
                }   break;
            case "sair":
                querSair = sair(comando);
                break;
            case "observar":
                observar();
                break;
            case "detonar":
                querSair = detonar();
                break;
            default:
                break;
        }

        return querSair;
    }
    
    
    // Implementacoes dos comandos do usuario

    /**
     * Método que imprime ajuda quando o usuário digita "ajuda", apresentando os
     * comandos válidos/disponíveis.
     */
    private void imprimirAjuda() 
    {
            System.out.println("Voce esta perdido. Voce esta sozinho. Voce caminha");
            System.out.println("pela casa.");
            System.out.println();
            System.out.println("Suas palavras de comando sao:");
            System.out.println(analisador.getComandos());
    }
    
    /**
     * Método que analisa a segunda palavra do comando, representando para qual
     * ambiente o jogador deseja ir, se for possível. Também, verifica se o jogador
     * fará uso da chave mestra e se a porta está emperrada ou não. Além disso,
     * atualiza a interface gráfica com o numero de tentativas restantes do jogador
     * e a durabilidade da chave mestra. Se for para um ambiente, coleta todos os
     * itens visíveis do mesmo.
     * 
     * @param comando Ambiente para qual o usuário deseja ir
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
            
            // Controla o uso da chave mestra
            if(vidaUtilChave != 0) {
                System.out.println("Voce deseja usar a chave-mestra? (sim/nao)");
                String confirmacao = analisador.pegarString();
                if(confirmacao.equals("sim")){
                    usouChave = true;
                    usarChave();
                    tela.setUsosChave(Integer.toString(vidaUtilChave));
                }
            }
            
            
            // Caso o jogador não use a chave mestra, verificar, aleatoriamente, se a porta
            // está emperrada ou não e descontar 1 ponto dos movimentos permitidos
            if(!usouChave){
                boolean emperrada = gerador.nextBoolean();
                if(emperrada) {
                    proximoAmbiente = ambienteAtual;
                    System.out.println("A porta esta emperrada.");
                }
                movimentos--;
                tela.setTentativas(Integer.toString(movimentos));
            }
            
            // Enquanto o proximo ambiente possuir item visivel
            // Coleta o Item
            while(proximoAmbiente.possuiItemVisivel()){
                coletarItem(proximoAmbiente);
            }
            
            ambienteAtual = proximoAmbiente;
            
            imprimirLocalizacaoAtual();
        }
    }
    
    /**
     * Método que coleta um Item visível de um ambiente e retira a informação
     * necessária do mesmo.
     * 
     * @param ambiente Ambiente do qual o item será removido
     */
    private void coletarItem(Ambiente ambiente){
        Item item;
        item = ambiente.removeItemVisivel();
        
        // Caso o ambiete possua uma dica, apresentar ao jogador e adicionar seu conteúdo à interface gráfica 
        if(item instanceof Dica) {
            Dica d = (Dica) item;
            System.out.println("Dica encontrada!");
            System.out.println("Dica: " + d.getDica());
            tela.addDica(d.getDica());
        // Caso o ambiente possua uma chave mestra, apresentá-la ao jogador
        } else if(item instanceof ChaveMestra) {
            ChaveMestra cm = (ChaveMestra) item;
            vidaUtilChave = cm.getVidaUtil();
            System.out.println("Chave Mestra encontrada!");
            System.out.println("Usos: " + cm.getVidaUtil());
            tela.setUsosChave(Integer.toString(cm.getVidaUtil()));
        }
        
    }
    
    /**
     * Método que controla o uso da chave mestra, verificando se ela ainda pode ser
     * utilizada, se puder, desconta do número de tentativas permitidas de uso.
     * 
     * @return Desconta 1 do número de tentativas do uso da chave mestra
     */
    private int usarChave() {

        if(vidaUtilChave == 1) {
            System.out.println("Voce utilizou a chave com sucesso. Infelizmente, ela quebrou, e voce nao podera usa-la novamente.");
        }

        else {
            System.out.println("Voce utilizou a chave com sucesso.");
        }

        vidaUtilChave--;

        return vidaUtilChave;
    }

    /**
     * Método que valida a saída do jogador do jogo.
     * 
     * @param comando Comando a ser analisado
     * @return true, se o comando digitado for "sair", ou false, caso contrário
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
    
    /**
     * Método que apresenta descrição do ambiente atual para o usuário ao digitar
     * "obervar".
     */
    private void observar(){
        imprimirLocalizacaoAtual();
    }
    
    /**
     * Método responsável pelo uso da dinamite, utilizada para encontrar o tesouro,
     * caso o jogador esteja no ambiente indicado.
     * 
     * @return true, se o jogador encontrou o tesouro, ou false, caso contrário
     */
    private boolean detonar(){
        if (ambienteAtual.possuiItemOculto()){
            System.out.println("Tesouro encontrado!");
            Tesouro t = (Tesouro)ambienteAtual.removeItemOculto();
            System.out.println("Valor do tesouro: " + t.getValor());
            System.out.println("Parabens, voce ganhou!!!");
        }
        else {
            System.out.println("Nao foi dessa vez...");
        }
        return true;
    }
    
    
    /**
     * Método responsável por realizar escrita de um texto em um arquivo, chamando
     * o método da classe ManipuladorArquivo
     * 
     * 
     * @param nomeArquivo Nome do arquivo a ser escrito.
     * @param texto String contendo o texto a ser escrito no arquivo.
     */
    private void escreverArquivo(String nomeArquivo, String texto){
        try{
            ManipuladorArquivo.escreverArquivo(nomeArquivo, texto);
        }
        catch(IOException e){
            System.out.println("Falha ao escrever no arquivo: " + e.getMessage());
        }
    }
}
