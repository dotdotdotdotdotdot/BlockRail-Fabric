package net.olin.blockrail.util;
import com.google.gson.Gson;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.olin.blockrail.trades.Trade;
import net.olin.blockrail.trades.Trades;

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

            APISchema apiBody = gson.fromJson(getResponse.body(), APISchema.class);

            Trade trade = new Trade(apiBody.getTradeId(),apiBody.getName(),apiBody.getInput(), apiBody.getInputAmount(),apiBody.getOutput(),apiBody.getOutputAmount(),
                    apiBody.getNamespace(), apiBody.getTexturePath());
            System.out.println(trade);
            Trades.appendTrades(trade);
        }

    }

}