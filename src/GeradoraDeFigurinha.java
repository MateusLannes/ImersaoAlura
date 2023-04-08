
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author Mateus Lannes Cunha
 * data    06/04/2023
 */


public class GeradoraDeFigurinha {

    
    public void cria() throws Exception {


        //leitura da imagem
        BufferedImage imagemOriginal = ImageIO.read(new File("entrada/Filme.jpg"));

        // cria uma nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // escrever uma frase na nova imagem

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File("saida/figurinha.png"));


    }
    public static void main(String[] args) throws Exception {
        GeradoraDeFigurinha geradora = new GeradoraDeFigurinha();
        geradora.cria();
    }
}
