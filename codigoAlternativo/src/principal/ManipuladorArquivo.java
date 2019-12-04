package principal;


import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe responsável por realizar a escrita da localização do Tesouro, Dicas e
 * Chave Mestra em arquivo de texto
 */
public class ManipuladorArquivo {
    /**
     * Método estático responsável por escrever o texto fornecido no arquivo informado
     * 
     * @param nomeArquivo Nome do arquivo a ser escrito
     * @param texto Texto a ser escrito no arquivo
     * @throws IOException Caso ocorra algum erro na abertura do arquivo
     */
    public static void escreverArquivo(String nomeArquivo, String texto) throws IOException{
        FileWriter arq = new FileWriter(nomeArquivo);
        arq.write(texto);
        arq.close();
    }
    
}
