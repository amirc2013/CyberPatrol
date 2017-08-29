package Communication;

import Exceptions.CommunicationException;
import org.omg.CORBA.NameValuePair;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by User on 8/28/2017.
 */
public class HttpClientUtil {
    private static final String GET = "GET";
    private final String USER_AGENT = "Mozilla/5.0";


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


    public String sendGETWithParam(String url, String param) throws CommunicationException {
        URL obj = null;
        HttpURLConnection con = null;
        try {
            obj = new URL(url+"?url="+URLEncoder.encode(param,"UTF-8"));
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
