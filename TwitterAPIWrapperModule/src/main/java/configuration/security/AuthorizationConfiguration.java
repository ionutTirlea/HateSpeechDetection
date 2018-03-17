package configuration.security;

import configuration.TwitterAPISettings;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import utils.ConnectionUtilities;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;

/**
 * Created by ionut.tirlea on 01/12/2017.
 */
public class AuthorizationConfiguration implements IAuthorizationConfiguration {

    private static AuthorizationConfiguration instance = null;

    private String accessToken;

    private  AuthorizationConfiguration(){
        super();
        try {
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialize() throws IOException{
        HttpsURLConnection connection = null;
        String encodedCredentials = encodeKeys();
        try {
            URL url = new URL(TwitterAPISettings.TWITTER_API_AUTHORIZATION_URL);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Basic " + encodedCredentials);
            connection.setUseCaches(false);
            ConnectionUtilities.writeRequest(connection, "grant_type=client_credentials");
            // Parse the JSON response into a JSON mapped object to fetch fields from.
            JSONObject obj = (JSONObject) JSONValue.parse(ConnectionUtilities.readResponse(connection));
            if (obj != null) {
                String tokenType = (String) obj.get("token_type");
                String token = (String) obj.get("access_token");
                this.accessToken = ((tokenType.equals("bearer")) && (token != null)) ? token : "";
            }
        }
        catch (MalformedURLException | ProtocolException e) {
            throw new IOException("Invalid endpoint URL specified.", e);
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    // Encodes the consumer key and secret to create the basic authorization key
    private String encodeKeys() {
        try {
            String encodedConsumerKey = URLEncoder.encode(getOAuthConsumerKey(), "UTF-8");
            String encodedConsumerSecret = URLEncoder.encode(getOAuthConsumerSecret(), "UTF-8");

            String fullKey = encodedConsumerKey + ":" + encodedConsumerSecret;
            byte[] encodedBytes;
            encodedBytes = Base64.getEncoder().encode(fullKey.getBytes());
            return new String(encodedBytes);
        }
        catch (UnsupportedEncodingException e) {
            return new String();
        }
    }

    public static AuthorizationConfiguration getInstance(){
        if(instance == null){
            instance = new AuthorizationConfiguration();
        }
        return instance;
    }

    @Override
    public String getOAuthConsumerKey() {
        return TwitterAPISettings.CONSUMER_KEY;
    }

    @Override
    public String getOAuthConsumerSecret() {
        return TwitterAPISettings.CONSUMER_SECRET;
    }

    @Override
    public String getOAuthAccessToken() {
        return accessToken;
    }
}
