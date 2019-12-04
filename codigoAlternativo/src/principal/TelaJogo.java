package principal;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Classe responsável disponibilizar os dados do jogo e comunicar com o jogador
 * através de uma Interface Gráfica do Usuário
 */
public class TelaJogo {
    private JFrame janela;
    private JLabel rotuloTentativas;
    private JLabel rotuloChave;
    private JLabel rotuloDicas;
    private JLabel mapa;
    private JTextField campoTentativas;
    private JTextField campoChave;
    private JTextField campoInputTexto;//talvez tirar
    private JTextArea areaTextoDicas;
    private JTextArea areaTerminal;
    
    
    
    /**
     * Cria uma tela instanciando seus componentes e em seguida juntando todos em
     * uma única janela
     */
    public TelaJogo(){
        janela = new JFrame("Caça ao Tesouro");
        
        rotuloTentativas = new JLabel("Número de Tentativas Restantes:");
        rotuloChave = new JLabel("Durabilidade da chave mestra:");
        rotuloDicas = new JLabel("Dicas encontradas:", SwingConstants.LEFT);
        mapa = new JLabel("", new ImageIcon("src/TelaJogo.png"), SwingConstants.LEFT);
        
        campoTentativas = new JTextField();
        campoTentativas.setEditable(false);
        campoTentativas.setHorizontalAlignment(SwingConstants.CENTER);
        
        campoChave = new JTextField();
        campoChave.setEditable(false);
        campoChave.setHorizontalAlignment(SwingConstants.CENTER);

        
        campoInputTexto = new JTextField();
        
        areaTextoDicas = new JTextArea();
        areaTextoDicas.setEditable(false);
        areaTerminal = new JTextArea();
        areaTerminal.setEditable(false);
        

        montarJanela();
    }
    
    /**
     * Método responsável por configurar a janela e juntar seus componentes
     */
    private void montarJanela(){
        janela.setSize(1080, 720);
        janela.setLayout(new BorderLayout());
        
        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setLayout(new BoxLayout(painelEsquerdo, BoxLayout.Y_AXIS));
        painelEsquerdo.add(rotuloTentativas);
        painelEsquerdo.add(campoTentativas);
        painelEsquerdo.add(rotuloChave);
        painelEsquerdo.add(campoChave);
        janela.add(painelEsquerdo, BorderLayout.WEST);
        
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new FlowLayout());
        painelCentral.add(mapa);
        janela.add(painelCentral, BorderLayout.CENTER);
        
        
        
        JPanel painelDireito = new JPanel();
        painelDireito.setLayout(new BoxLayout(painelDireito, BoxLayout.Y_AXIS));
        painelDireito.add(rotuloDicas);
        painelDireito.add(new JScrollPane(areaTextoDicas));
        janela.add(painelDireito, BorderLayout.EAST);
        
        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new BoxLayout(painelInferior, BoxLayout.Y_AXIS));
        painelInferior.add(new JScrollPane(areaTerminal));
        janela.add(painelInferior, BorderLayout.SOUTH);
        
        janela.pack();
        //necessário para que a aplicação encerre após fechar a janela
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Método responsável por tornar a janela visível ao jogador
     */
    public void exibir(){
        janela.setVisible(true);
    }
    
    
    /**
     * Método responsável por atualizar o número de usos restantes de uma chave mestra 
     * a ser disponibilizado na interface gráfica.
     * 
     * @param vidaUtil Número de usos da chave mestra.
     */
    public void setUsosChave(String vidaUtil){
        campoChave.setText(vidaUtil);
    }
    
    /**
     * Método responsável por atualizar o número de tentativas de jogada restantes 
     * a ser disponibilizado na interface gráfica.
     * 
     * @param tentativas Número de jogadas restantes.
     */
    public void setTentativas(String tentativas){
        campoTentativas.setText(tentativas);
    }
    
    /**
     * Método responsável por disponibilizar na interface gráfica o conteúdo de uma dica.
     * 
     * @param dica Texto contendo a dica.
     */
    public void addDica(String dica){
        areaTextoDicas.append(dica + "\n");
        //areaTerminal.setText(areaTerminal.getText() + "\n" + dica);
    }
    
    /**
     * Método responsável por escrever o texto informado na área de texto destinada 
     * ao terminal da inerface gráfica.
     * 
     * @param text Texto contendo a mensagem a ser escrita
     */
    public void writeLineTextArea(String text){
        areaTerminal.append(text + "\n");
    }
    
}
