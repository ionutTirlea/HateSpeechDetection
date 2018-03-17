/**
 * Created by ionut.tirlea on 19/03/2017.
 */
import utils.ConnectionUtilities;
import utils.SSLUtilities;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class should be used only for testing
 */
public class HatebaseSearchTester {

    private boolean getHatebaseSearchResultJSONFile(String requestURL, File file){

        try {

            SSLUtilities.trustAllCertificates();

            URL url = new URL(requestURL);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            ConnectionUtilities.saveResponseToFile(connection, file);

            connection.disconnect();
            System.out.println("Executed Hatebase API call : requestURL=" + requestURL);
            return true;

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return false;
    }

    private boolean getHatebaseSearchResultJSONFile(HatebaseRequestModel hatebaseRequestModel, String filePath){

        if (hatebaseRequestModel == null) {
            return false;
        }

        String requestURL = hatebaseRequestModel.getRequestURL();

        if (requestURL == null || requestURL.isEmpty()){
            return false;
        }

        if(!requestURL.startsWith("https://api.hatebase.org/v3-0/")) {
            return false;
        }

        if (filePath == null || filePath.isEmpty()) {
            return false;
        }

        if(!filePath.endsWith(".json")){
            return false;
        }

        return this.getHatebaseSearchResultJSONFile(requestURL, new File(filePath));

    }

}
