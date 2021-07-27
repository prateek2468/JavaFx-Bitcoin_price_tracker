package sample;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CoinGecko {
    private static final HttpClient httpClient= HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    public static void updateCurrentPrice(Coin coin)  {
        HttpRequest request =  HttpRequest.newBuilder().GET()
                .uri(URI.create("https://api.coingecko.com/api/v3/simple/price?ids="+coin.getName()+"&vs_currencies=usd"))
                .setHeader("User-Agent" , "Java 11 HttpClient a6")
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request , HttpResponse.BodyHandlers.ofString());
            // System.out.println(response.statusCode()+":"+response.body());
            JSONObject json  = new JSONObject(response.body());
            double value = json.getJSONObject(coin.getName()).getDouble("usd");
            coin.setCurrentPrice(value);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void updatePrice(Coin coin , int days){
        coin.getHistoricalValues().getData().clear();
        HttpRequest request =  HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.coingecko.com/api/v3/coins/" +
                        coin.getName()+"/market_chart?vs_currency=usd&days=" +
                        days+"&interval=daily"))
                .setHeader("User-Agent" , "Java 11 HttpClient a6")
                .build();
        try {
            HttpResponse<String> response  = httpClient.send(request , HttpResponse.BodyHandlers.ofString());
            JSONObject obj = new JSONObject(response.body());
            JSONArray priceArray = obj.getJSONArray("prices");

            for(int i=0 ;i<priceArray.length() ; i++){
                JSONArray temp = priceArray.getJSONArray(i);
                double temp1 = temp.getDouble(1);
                coin.addHistoricalValue(i-priceArray.length()+1 , temp1 );
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
