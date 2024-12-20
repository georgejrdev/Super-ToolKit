package com.georgejrdev.utils.ia;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

import com.georgejrdev.utils.exceptions.AIKeyNotFound;
import com.georgejrdev.utils.validations.OptionsValidation;

public class GeminiRequest implements IARequest {

    public IAResponse request(String prompt){

        try {
            OptionsValidation.isAIKeyConfigured();
        }

        catch (AIKeyNotFound e){
            String message = "AI Key not found. Create an environment variable named STK_GEMINI_API_KEY with the value of your gemini-1.5-flash API access key. It's free. Link: https://ai.google.dev/pricing?hl=pt-br#1_5flash";
            return new IAResponse(false, message);
        }

        final String IA_API_KEY = System.getenv("STK_GEMINI_API_KEY");
        final String URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + IA_API_KEY;

        JSONObject json = new JSONObject();
        JSONArray contentsArray = new JSONArray();
        JSONObject contentObject = new JSONObject();
        JSONArray partsArray = new JSONArray();

        JSONObject partObject = new JSONObject();
        partObject.put("text", prompt);
        partsArray.put(partObject);

        contentObject.put("parts", partsArray);
        contentsArray.put(contentObject);
        json.put("contents", contentsArray);

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(json.toString(), MediaType.parse("application/json"));

        Request request = new Request.Builder()
            .url(URL)
            .post(body)
            .header("Content-Type", "application/json")
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();

                JSONObject jsonResponse = new JSONObject(responseBody);
                JSONArray candidates = jsonResponse.getJSONArray("candidates");
                JSONObject candidate = candidates.getJSONObject(0);
                JSONObject content = candidate.getJSONObject("content");
                JSONArray parts = content.getJSONArray("parts");
                String commitMessage = parts.getJSONObject(0).getString("text");
                
                commitMessage = commitMessage.replace("\n", "").trim();
                return new IAResponse(true, commitMessage);

            } else {
                String errorResponse = response.body().string();
                String errorMessage = "Error: " + response.code() + " - " + errorResponse;
                return new IAResponse(false, errorMessage);
            }
            
        } catch (IOException e) {
            
            String errorMessage = "Network or IO error: " + e.getMessage();
            return new IAResponse(false, errorMessage);
        }
    }
}