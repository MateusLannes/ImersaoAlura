import static java.net.http.HttpRequest.newBuilder;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

/**
 * @author Mateus Lannes Cunha
 * data    01/04/2023
 */

public class App {

    public static void main(String[] args) throws Exception {


        // conexão http, buscando top 250 filmes
        String url = "https://imdb-api.com/en/API/Top250Movies/k_7kvp08yi";
        URI endereco = URI.create(url);
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = newBuilder(endereco).GET().build();
        HttpResponse<String> response = cliente.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        // extrair os dados que interessam
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listadeFilmes = parser.parse(body);


        
        //Exibir e manipular os dados
        for (Map<String, String> filme : listadeFilmes) {

            String urlImagem = filme.get("image");


            System.out.print("\033[1m" + "titulo: " + "\033[0m"); // texto "titulo" em negrito
            String titulo = filme.get("title"); 


            InputStream inputStream = new URL(urlImagem).openStream();

            String nomeArquivo = titulo + ".png";

            var geradora = new GeradoraDeFigurinha();


            geradora.cria(inputStream, nomeArquivo);



            System.out.print("\033[1m" + "Link Imagem: " + "\033[0m"); // texto "Link Imagem" em negrito
            System.out.println( "\033[48;5;0m\033[38;5;15m" + filme.get("image") + "\033[0m");


            String numeroString = filme.get("imDbRating");
            String[] partes = numeroString.split("\\."); // separa o ponto do numero float
            int numeroInteiro = Integer.parseInt(partes[0]); // converte a parte inteira para um número inteiro

            System.out.println("\u001B[45m" + "Classificação: "  + filme.get("imDbRating")+ "\u001B[0m"); // link da imagem com fundo preto

            for (int i = 0; i < numeroInteiro; i++) { // imprime a nota usando estrelas
                System.out.print("\u2B50 ");
            }
            System.out.println();
            System.out.println();
        }
        
    }
}