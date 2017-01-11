package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by kreska on 09.01.17.
 */
public class RequestsMaker {
    public String getJsonString(String url) throws IOException {
        URL site = new URL(url);
        URLConnection urlConnection = site.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuilder result = new StringBuilder();
        String textLine = bufferedReader.readLine();

        try {
            do {
                result.append(textLine);
                textLine = bufferedReader.readLine();
            } while (textLine != null);
        } finally {
            bufferedReader.close();
        }
        return result.toString();
    }
}
