package model;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
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
    private final static String apiKey = "25174033-cf8f-4a70-827d-6d132857e426";

    public void getComplete() {
        String uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        Coin coin = null;

        try {
            JSONArray result = makeAPICall(uri).getJSONArray("data");
            for (int i= 0; i < result.length(); i++){
                coin = new Coin(result.getJSONObject(i).get("name").toString(),
                        result.getJSONObject(i).get("symbol").toString(),
                        result.getJSONObject(i).get("cmc_rank").toString(),
                        result.getJSONObject(i).getJSONObject("quote").getJSONObject("USD").get("price").toString(),
                        result.getJSONObject(i).get("circulating_supply").toString(),
                        result.getJSONObject(i).get("max_supply").toString(),
                        result.getJSONObject(i).getJSONObject("quote").getJSONObject("USD").get("percent_change_1h").toString(),
                        result.getJSONObject(i).getJSONObject("quote").getJSONObject("USD").get("percent_change_24h").toString(),
                        result.getJSONObject(i).getJSONObject("quote").getJSONObject("USD").get("percent_change_7d").toString());

                Model.completeCoins.store(coin);
            }
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }
    }

    public void getBasic() {
        String uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        Coin coin = null;

        try {
            JSONArray result = makeAPICall(uri).getJSONArray("data");
            for (int i= 0; i < result.length(); i++){
                coin = new Coin(result.getJSONObject(i).get("name").toString(),
                        result.getJSONObject(i).get("symbol").toString(),
                        result.getJSONObject(i).get("cmc_rank").toString(),
                        result.getJSONObject(i).getJSONObject("quote").getJSONObject("USD").get("price").toString());

                Model.basicCoins.store(coin);
            }
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }
    }

    public Info getInfo(String name) {
        String uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/info";
        List<NameValuePair> paratmers = new ArrayList<NameValuePair>();
        paratmers.add(new BasicNameValuePair("symbol", name.toUpperCase()));
        Info info = null;

        try {
            JSONObject result = makeAPICall(uri, paratmers).getJSONObject("data").getJSONObject(name.toUpperCase());
            info = new Info(result.get("name").toString(),
                    result.get("symbol").toString(),
                    result.get("category").toString(),
                    result.get("logo").toString(),
                    result.get("tags").toString(),
                    result.get("description").toString());
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }
        return info;
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
