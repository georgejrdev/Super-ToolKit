package com.georgejrdev.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.georgejrdev.core.interfaces.RequestInterface;;


public class Request implements RequestInterface{
    
    public String getResultRequest(String url, String method){

        try{
            String inputLine;

            URL requestUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod(method);

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