package utils;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;

/**
 * Created by ionut.tirlea on 01/12/2017.
 */
public class ConnectionUtilities {

    // Writes a request to a connection
    public static boolean writeRequest(HttpsURLConnection connection, String textBody) {

        if (connection == null)
            return false;

        try {
            if (connection.getResponseCode() != 200 || connection.getResponseCode() != 201) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + connection.getResponseCode());
            }
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            wr.write(textBody);
            wr.flush();
            wr.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Reads a response for a given connection and returns it as a string.
    public static String readResponse(HttpsURLConnection connection) {

        if (connection == null)
            return "";

        try {
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + connection.getResponseCode());
            }
            StringBuilder str = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                str.append(line + System.getProperty("line.separator"));
            }
            return str.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return new String();
        }
    }

    public static boolean saveResponseToFile(HttpsURLConnection connection, File file) {

        if (file.isDirectory() || !file.canRead() || !file.canWrite()) {
            return false;
        }

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(ConnectionUtilities.readResponse(connection));

            bw.close();
            fw.close();

        } catch (IOException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
