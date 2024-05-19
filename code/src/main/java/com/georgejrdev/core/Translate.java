package com.georgejrdev.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.georgejrdev.core.interfaces.TranslateInterface;


public class Translate implements TranslateInterface {
    private final String URL_API = "https://translate-api-0unn.onrender.com/getTranslation/";
    private final String SEPARATOR = "|";

    @Override
    public String translate(String text, String lang){
        final String REQUEST_URL = URL_API + text + SEPARATOR + lang;

        try{
            String inputLine;

            URL url = new URL(REQUEST_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            
            while ((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }

            in.close();
            connection.disconnect();

            return response.toString();
        } catch (FileNotFoundException e) {
            return e.toString();
        } catch (Exception e){
            return e.toString();
        }
    }
}