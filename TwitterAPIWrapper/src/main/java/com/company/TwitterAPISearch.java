package com.company;

import configuration.TwitterAPISettings;
import configuration.security.AuthorizationConfiguration;
import configuration.security.IAuthorizationConfiguration;
import utils.ConnectionUtilities;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by ionut.tirlea on 01/12/2017.
 */
public class TwitterAPISearch {

    private IAuthorizationConfiguration authorizationConfiguration;

    public TwitterAPISearch(){
        this.authorizationConfiguration = AuthorizationConfiguration.getInstance();
    }

    public String search(TwitterAPISearchReqestModel twitterAPISearchReqestModel) throws IOException {
        HttpsURLConnection connection = null;
        System.out.println("Ionut " + authorizationConfiguration.getOAuthAccessToken());
        try {
            URL url = new URL(TwitterAPISettings.TWITTER_SEARCH_API_URL + "?q=hate&count=1");
            connection = (HttpsURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + authorizationConfiguration.getOAuthAccessToken());
            connection.setUseCaches(false);

            return ConnectionUtilities.readResponse(connection);
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
}
