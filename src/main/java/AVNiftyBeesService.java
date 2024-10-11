import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AVNiftyBeesService {
    public static void main(String[] args) {

        String apiKey = "4ZQW41KQVE1102P2";
        String symbol = "RELIANCE.BO";
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&outputsize=full&apikey=" + apiKey;

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();


        try(Response response = client.newCall(request).execute()){
            if(!response.isSuccessful()) throw new IOException("Unexpected code" + response);

            String responseData = response.body().string();

            JsonObject rootObject = JsonParser.parseString(responseData).getAsJsonObject();

            JsonObject timeSeries = rootObject.getAsJsonObject("Time Series (Daily)");

            String date = "2024-10-08";
            if(timeSeries.has(date)){
                JsonObject jsonObject = timeSeries.getAsJsonObject(date);
                String closingPrice = jsonObject.get("4. close").getAsString();
                System.out.println(closingPrice);
            }
            else {
                System.out.println("Closing price for" + date + " not available");
            }


        } catch (IOException e) {
            System.out.println("Error fetching stock data");
        }
    }
}
