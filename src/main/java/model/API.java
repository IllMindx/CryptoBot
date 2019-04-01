package model;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

public class API {
    private final static String apiKey = "25174033-cf8f-4a70-827d-6d132857e426";

    public Coin getComplete(String name) {
        String uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        Coin coin = null;

        try {
            JSONArray result = makeAPICall(uri).getJSONArray("data");
            for (int i= 0; i <= result.length(); i++){
                if (result.getJSONObject(i).get("name").toString().toLowerCase().equals(name.toLowerCase())) {
                    coin = new Coin(result.getJSONObject(i).get("name").toString(),
                            result.getJSONObject(i).get("symbol").toString(),
                            result.getJSONObject(i).get("cmc_rank").toString(),
                            result.getJSONObject(i).getJSONObject("quote").getJSONObject("USD").get("price").toString(),
                            result.getJSONObject(i).get("circulating_supply").toString(),
                            result.getJSONObject(i).get("max_supply").toString(),
                            result.getJSONObject(i).getJSONObject("quote").getJSONObject("USD").get("percent_change_1h").toString(),
                            result.getJSONObject(i).getJSONObject("quote").getJSONObject("USD").get("percent_change_24h").toString(),
                            result.getJSONObject(i).getJSONObject("quote").getJSONObject("USD").get("percent_change_7d").toString());
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }

        return coin;
    }

    public Coin getBasic(String name) {
        String uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        Coin coin = null;

        try {
            JSONArray result = makeAPICall(uri).getJSONArray("data");
            for (int i= 0; i <= result.length(); i++){
                if (result.getJSONObject(i).get("name").toString().toLowerCase().equals(name.toLowerCase())) {
                    coin = new Coin(result.getJSONObject(i).get("name").toString(),
                            result.getJSONObject(i).get("symbol").toString(),
                            result.getJSONObject(i).get("cmc_rank").toString(),
                            result.getJSONObject(i).getJSONObject("quote").getJSONObject("USD").get("price").toString());
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }

        return coin;
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

}
