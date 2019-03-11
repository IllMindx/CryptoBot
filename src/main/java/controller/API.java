package controller;

import model.CoinModel;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class API {

    private static String apiKey = "25174033-cf8f-4a70-827d-6d132857e426";

    public static void main(String[] args) {
        getLastest("Bitcoin");
    }

    public static void getInfo() {
        String uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/info";
    }

    public static void getLastest(String name) {
        String uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        CoinModel coin = null;

        try {
            /*
                CONTINUAR DAQUI PEGANDO DADOS DO QUOTES
            */
            JSONArray result = makeAPICall(uri).getJSONArray("data");
            //System.out.println(result.getJSONObject(0).get("name"));
            for (int i= 0; i <= result.length(); i++){
                if (result.getJSONObject(i).get("name").equals(name)) {
                    coin = new CoinModel(result.getJSONObject(i).get("name").toString(),
                            result.getJSONObject(i).get("symbol").toString(),
                            Integer.parseInt(result.getJSONObject(i).get("cmc_rank").toString()),
                            Integer.parseInt(result.getJSONObject(i).get("").toString()));
                    break;
                }
                else
                    System.out.println("Errado!");
            }
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }

        System.out.println("Name: " +coin.getName());
        System.out.println("Symbol: "+coin.getSymbol());
        System.out.println("Rank: "+coin.getRank());
        System.out.println("Price: "+coin.getPrice());
    }

    public static JSONObject makeAPICall(String uri)
            throws URISyntaxException, IOException {
        String response_content = "";

        URIBuilder query = new URIBuilder(uri);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.addHeader("X-CMC_PRO_API_KEY", apiKey);

        CloseableHttpResponse response = client.execute(request);

        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

        return new JSONObject(response_content);
    }

    public static JSONObject makeAPICall(String uri, List<NameValuePair> parameters)
            throws URISyntaxException, IOException {
        String response_content = "";

        URIBuilder query = new URIBuilder(uri);
        query.addParameters(parameters);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.addHeader("X-CMC_PRO_API_KEY", apiKey);

        CloseableHttpResponse response = client.execute(request);

        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

        return new JSONObject(response_content);
    }

}
