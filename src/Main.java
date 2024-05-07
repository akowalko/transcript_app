import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpRequest;

public class Main {


    public static void main(String[] args) {
        Gson gson = new Gson();
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.assemblyai.com/v2/transcript"))
                .header("Authorization", "c6cc79d9c06c47df82aa8190457961ff")
                .POST(HttpRequest.BodyPublishers.ofString())
    }
}
