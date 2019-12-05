package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultCaret;

/**
 * Classe responsável disponibilizar os dados do jogo e comunicar com o jogador
 * através de uma Interface Gráfica do Usuário
 */
public class TelaJogo {
    //booleana utilizada para controlar a leitura do input da interface
    private boolean envioConfirmado;
    private JFrame janela;
    
    //painel esquerdo
    private JLabel rotuloTentativas;
    private JLabel rotuloChave;
    private JTextField campoTentativas;
    private JTextField campoChave;
    
    //painel direito
    private JLabel rotuloDicas;
    private JTextArea areaTextoDicas;
    
    //painel central
    private JLabel mapa;
    private ImageIcon imagem;
    
    //painel inferior
    private JTextArea areaTerminal;
    private JLabel setinha;
    private JTextField campoInputTexto;
    private JButton enviar;
    
    
    
    
    
    /**
     * Cria uma tela instanciando seus componentes e em seguida juntando todos em
     * uma única janela
     */
    public TelaJogo(){
        envioConfirmado = false;
        janela = new JFrame("Caça ao Tesouro");
        
        rotuloTentativas = new JLabel("Número de Tentativas Restantes:");
        rotuloChave = new JLabel("Durabilidade da chave mestra:");
        rotuloDicas = new JLabel("Dicas encontradas:");
        mapa = new JLabel();
        imagem = new ImageIcon(new ImageIcon("src/TelaJogo.png").getImage().getScaledInstance(650, 400, Image.SCALE_DEFAULT));

        
        campoTentativas = new JTextField();
        campoTentativas.setEditable(false);
        campoTentativas.setHorizontalAlignment(SwingConstants.CENTER);
        
        campoChave = new JTextField();
        campoChave.setEditable(false);
        campoChave.setHorizontalAlignment(SwingConstants.CENTER);

        setinha = new JLabel("Informe seu comando >");
        campoInputTexto = new JTextField(15);
        
        areaTextoDicas = new JTextArea();
        areaTextoDicas.setEditable(false);
        
        areaTerminal = new JTextArea();
        areaTerminal.setEditable(false);
        
        enviar = new JButton("Enviar");
        enviar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                envioConfirmado = true;
            }
        });

        montarJanela();
    }
    
    /**
     * Método responsável por configurar a janela e juntar seus componentes
     */
    private void montarJanela(){
        //utilizada para configurar o posicionamento automático das barras de rolagem
        DefaultCaret dc; 
        
        janela.setSize(1080, 720);
        janela.setLayout(new BorderLayout());
        
        /* configura painel esquerdo */
        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setPreferredSize(new Dimension(250, 350));
        painelEsquerdo.setLayout(new BoxLayout(painelEsquerdo, BoxLayout.Y_AXIS));
        painelEsquerdo.add(rotuloTentativas);
        painelEsquerdo.add(campoTentativas);
        painelEsquerdo.add(rotuloChave);
        painelEsquerdo.add(campoChave);
        janela.add(painelEsquerdo, BorderLayout.WEST);
        
        /* configura painel central */
        JPanel painelCentral = new JPanel();
        painelCentral.setPreferredSize(new Dimension(700, 400));
        painelCentral.setBorder(BorderFactory.createLineBorder(Color.black));
        painelCentral.setLayout(new BorderLayout());
        painelCentral.setBackground(Color.WHITE);
        mapa.setIcon(imagem);
        mapa.setHorizontalAlignment(SwingConstants.CENTER);
        mapa.setVerticalAlignment(SwingConstants.CENTER);
        painelCentral.add(mapa, BorderLayout.CENTER);
        janela.add(painelCentral, BorderLayout.CENTER);
        
        
        /* configura painel direito */
        JPanel painelDireito = new JPanel();
        painelDireito.setPreferredSize(new Dimension(250, 350));
        painelDireito.setLayout(new BoxLayout(painelDireito, BoxLayout.Y_AXIS));
        painelDireito.add(rotuloDicas);
        painelDireito.add(new JScrollPane(areaTextoDicas));
        
        dc = (DefaultCaret) areaTextoDicas.getCaret();
        dc.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        janela.add(painelDireito, BorderLayout.EAST);
        
        /* configura painel inferior */
        JPanel painelInferior = new JPanel();
        JPanel painelInferiorEntrada = new JPanel();
        painelInferior.setPreferredSize(new Dimension(1000, 250));
        painelInferior.setLayout(new BoxLayout(painelInferior, BoxLayout.Y_AXIS));
        painelInferior.add(new JScrollPane(areaTerminal));
        
        dc = (DefaultCaret) areaTerminal.getCaret();
        dc.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        painelInferiorEntrada.setPreferredSize(new Dimension(1000, 10));
        painelInferiorEntrada.setLayout(new FlowLayout());
        painelInferiorEntrada.add(setinha);
        painelInferiorEntrada.add(campoInputTexto);
        painelInferiorEntrada.add(enviar);
        painelInferior.add(painelInferiorEntrada);
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
    }
    
    /**
     * Método responsável por escrever o texto fornecido na área de texto destinada 
     * ao terminal da interface gráfica e realizar quebrar de linha.
     * 
     * @param text Texto contendo a mensagem a ser escrita
     */
    public void writeLineTextArea(String text){
        areaTerminal.append(text + "\n");
    }
    
    /**
     * Método responsável por escrever o texto fornecido na área de texto destinada 
     * ao terminal da interface gráfica.
     * 
     * @param text Texto contendo a mensagem a ser escrita
     */
    public void writeTextArea(String text){
        areaTerminal.append(text);
    }
    
    /**
     * Método que retona o comando (String) digitado pelo usuário na interface gráfica.
     * 
     * @return Comando (String) digitado pelo usuário
     */
    public String pegarEntrada(){
        while(!envioConfirmado){
            writeTextArea("");
        }
        areaTerminal.append("> " + campoInputTexto.getText() + "\n");
        envioConfirmado = false;
        String saida = campoInputTexto.getText();
        campoInputTexto.setText("");
        return saida;   
    }
    
}
