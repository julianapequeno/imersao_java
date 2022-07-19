import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        /*STEP BY STEP
         * 1 - Connection HTTP with IMDB-API TOP 250 movies. -> Request/Response, pedindo uma página para a web.
         * 2 - Separar os dados que estão vindo (De início: Título, poster, classificação)
         * 3 - Exibir e manipular os dados da forma que queremos para produzir nossa aplicação
         */

        //First Step
        String url_imdb_filmes = "https://alura-imdb-api.herokuapp.com/movies";
        URI endereco = URI.create(url_imdb_filmes); //URL é um tipo de URI
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString()); //!Mando meu pedido e ele devolve o resultado
        String body = response.body(); //!Peguei toda a informação do json
        
        //Second Step - Using regular expressions
        JsonParser parser = new JsonParser(); 
        List<Map<String, String>> MoviesList = parser.parse(body);

        System.out.println("You have "+MoviesList.size()+" movies in your list :)");
        //Third step
        float number;
        for (Map<String,String> movie : MoviesList) {
            System.out.println("\u001b[1m Title: \u001b[m"+movie.get("title"));
            System.out.println(movie.get("image"));
            number =  Float.parseFloat(movie.get("imDbRating"));
            for (int i = 0; i < Math.round(number); i++) {
                System.out.print("\uD83D\uDC99 ⭐");
            }
            //System.out.println(movie.get("imDbRating"));
            System.out.println();
        }
    }
}
