import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpController {

    public Transcript transcript = new Transcript();
    public Gson gson = new Gson();

    public String httpPost(String audio_url) throws URISyntaxException, IOException, InterruptedException {

        transcript.setAudio_url(audio_url);
        transcript.setLanguage_code("pl");
        String json = gson.toJson(transcript);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.assemblyai.com/v2/transcript"))
                .header("Authorization", "c6cc79d9c06c47df82aa8190457961ff")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();


        HttpResponse<String> postResponse =  httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        transcript  = gson.fromJson(postResponse.body(), Transcript.class);

        return transcript.getId();
    }

    HttpClient httpClient = HttpClient.newHttpClient();

    public String httpGet() throws InterruptedException, URISyntaxException, IOException {

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.assemblyai.com/v2/transcript/" + transcript.getId()))
                .header("Authorization", "c6cc79d9c06c47df82aa8190457961ff")
                .GET()
                .build();


        while (true) {
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            transcript = gson.fromJson(getResponse.body(), Transcript.class);
            String status = transcript.getStatus();

            if ("completed".equals(status) || "error".equals(status)) {
                break;
            }
        }
        return transcript.getText();

    }

}


