package com.ash.projects;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class AVNiftyBeesService {

    public Double calculateNiftyBeesPriceOnDate(String date){

        String apiKey = "4ZQW41KQVE1102P2";
        String symbol = "NIFTYBEES.BSE";
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

//            String date = "2024-10-08";
            if(timeSeries.has(date)){
                JsonObject jsonObject = timeSeries.getAsJsonObject(date);
                return jsonObject.get("4. close").getAsDouble();
//                System.out.println(closingPrice);
            }
            else {
                System.err.println("Closing price for " + date + " not available");
                return null;
            }
        } catch (IOException e) {
            System.err.println("Error fetching stock data: " + e.getMessage());
            return null;
        }
    }
}
