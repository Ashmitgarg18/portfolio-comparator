////import yahoofinance.Stock;
////import yahoofinance.YahooFinance;
////import yahoofinance.histquotes.HistoricalQuote;
////
////import java.util.Calendar;
////import java.util.List;
////
////
////public class NIFTYBeesService {
////    public static void main(String[] args) throws Exception {
////
////
////
////        Stock niftyBees = YahooFinance.get("NIFTYBEES.NS");
////
////        Calendar from = Calendar.getInstance();
////        from.set(2023, Calendar.AUGUST, 14);
////
////        Calendar to = Calendar.getInstance();
////        to.set(2023, Calendar.AUGUST, 14);
////
////        List<HistoricalQuote> historicalQuotes = niftyBees.getHistory(from, to);
////        for(HistoricalQuote historicalQuote: historicalQuotes){
////            System.out.println(historicalQuote.getClose());
////        }
////
////
////    }
////}
//
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
//public class YFNiftyBeesService {
//    public static void main(String[] args) throws IOException {
//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(30, TimeUnit.SECONDS)
//                .build();
//
//        // Use the fresh crumb and cookie
//        String crumb = "PL9W8wV8kWC";
//        String cookie = "A1=d=AQABBAcE2WICEB2y2GYHRPtQ8KX801_c19EFEgEBCAFgBWczZ1lQb2UB_eMBAAcIBwTZYl_c19E&S=AQAAAgSa9fDHNBIbumfDUZL5T0U; A3=d=AQABBAcE2WICEB2y2GYHRPtQ8KX801_c19EFEgEBCAFgBWczZ1lQb2UB_eMBAAcIBwTZYl_c19E&S=AQAAAgSa9fDHNBIbumfDUZL5T0U; A1S=d=AQABBAcE2WICEB2y2GYHRPtQ8KX801_c19EFEgEBCAFgBWczZ1lQb2UB_eMBAAcIBwTZYl_c19E&S=AQAAAgSa9fDHNBIbumfDUZL5T0U"; // Your fresh cookie
//
//        // UNIX timestamps for 14-08-2023
//        long period1 = 1691971200;  // Start time (14-08-2023 00:00)
//        long period2 = 1692057600;  // End time (14-08-2023 23:59)
//
//        String url = "https://query1.finance.yahoo.com/v7/finance/download/NIFTYBEES.NS?period1=" + period1 +
//                "&period2=" + period2 + "&interval=1d&events=history&crumb=" + crumb;
//
//        Request request = new Request.Builder()
//                .url(url)
//                .addHeader("Cookie", cookie)
//                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//            // The response will contain historical data in CSV format
//            String responseData = response.body().string();
//            System.out.println(responseData);
//        }
//    }
//}
