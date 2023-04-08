import static java.net.http.HttpRequest.newBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

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
          

            System.out.print("\033[1m" + "titulo: " + "\033[0m");
            System.out.println(filme.get("title"));

            System.out.print("\033[1m" + "Link Imagem: " + "\033[0m");
            System.out.println(filme.get("image"));


            String numeroString = filme.get("imDbRating");
            String[] partes = numeroString.split("\\."); // separa o ponto do numero float
            int numeroInteiro = Integer.parseInt(partes[0]); // converte a parte inteira para um número inteiro

            System.out.println("Classificação: " + filme.get("imDbRating"));
            for (int i = 0; i < numeroInteiro; i++) { // imprime a nota usando estrelas
                System.out.print("\u2B50 ");
            }
            System.out.println();
            System.out.println();
        }
        
    }
}