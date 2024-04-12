package net.olin.blockrail.util;
import com.google.gson.Gson;
import net.olin.blockrail.trades.Trade2;
import net.olin.blockrail.trades.Trades2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API {
    private static final Gson gson = new Gson();  // Make Gson instance static


    public static void main(String[] args) throws Exception {
        for (int i = 0; i <= 2; i++){
            String API_URL = "http://localhost:3000/gettrade/" + i;
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI(API_URL))
                    .GET()
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

            Trade2 trade = gson.fromJson(getResponse.body(), Trade2.class);

            Trades2.appendTrades(trade);
        }

    }

}