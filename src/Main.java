import java.io.IOException;
import java.net.URISyntaxException;


public class Main {


    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        HttpController HttpController = new HttpController();
        HttpController.httpPost("https://github.com/ArnoTr/tranlator/blob/master/malik%20plyta%20z%20belmondziakiem%20nie%20nagrywa%20za%20friko.mp3?raw=True");
        System.out.println(HttpController.httpGet());

    }
}

