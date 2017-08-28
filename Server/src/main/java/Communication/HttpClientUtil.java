package Communication;

import Exceptions.CommunicationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by User on 8/28/2017.
 */
public class HttpClientUtil {
    private static final String GET = "GET";

    public String sendGET(String url) throws CommunicationException {
        URL obj = null;
        HttpURLConnection con = null;
        try {
            obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod(GET);
        } catch (MalformedURLException e) {
            throw new CommunicationException("Bad url: " + url, e);
        } catch (IOException e) {
            throw new CommunicationException("Couldnt connect the url: " + url, e);
        }
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));) {
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                return response.toString();
            } else {
                throw new CommunicationException("Bad response (" + con.getResponseCode() + ") from " + url);
            }
        } catch (IOException e) {
            throw new CommunicationException("Error sending GET request to" + url, e);
        }
    }
}
