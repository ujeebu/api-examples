import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * RequestExample
 */
public class RequestExample {
  private final String BASE_URI = "https://lexper.p.mashape.com/v1.1/extract";
    private final String KEY_NAME = "X-Mashape-Key";
    private final String KEY_VALUE = "8vKX7VEKR2mshaOFlTBQnqOeGkkep1cgXBfjsnbVLeoliSC01R";

    public static void main(String[] args) throws Exception{
        RequestExample example = new RequestExample();
        example.runExample();
    }


    /**
     *
     */
    public void runExample() throws Exception{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(getUri());
            httpget.addHeader(KEY_NAME, KEY_VALUE);
            System.out.println("Executing request " + httpget.getRequestLine());
            
            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status != 200) {
                        System.out.println("Unexpected response status: " + status);
                    }
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            prettyPrintJson(responseBody);
        } finally {
            httpclient.close();
        }

    }


    /**
     * 
     */
    private void prettyPrintJson(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(json);
        String prettyJsonString = gson.toJson(je);
        System.out.println(prettyJsonString);
    }


    /**
     *
     * @return
     * @throws URISyntaxException
     */
    private URI getUri() throws URISyntaxException {
        return new URIBuilder(BASE_URI)
                    .setParameter("url", "https://medium.com/financial-times/silicon-valleys-founder-factory-80dade9377ab") //  url to extract
                    .setParameter("text", "1")       // return extracted text
                    .setParameter("html", "1")   // extract html
                    .setParameter("media", "1")  // extract media
                    .setParameter("feeds", "0")  // do not extract RSS feeds
                    .setParameter("images", "1")     // extract all images present in HTML
                    .setParameter("author", "1")     // extract article's author
                    .setParameter("pub_date", "1")   // extract article's publish date
                    .setParameter("js", "0")     // do not run js
                    .setParameter("js_wait", " 0")   // when JavaScript is enabled, indicates how many seconds the API should wait for the JS interpreter before starting the extraction.
                    .setParameter("strip_tags", "form,style")    // tags to strip from the extracted HTML
                    .setParameter("timeout", "6")    // request timeout in seconds.

                    .build();
    }


}