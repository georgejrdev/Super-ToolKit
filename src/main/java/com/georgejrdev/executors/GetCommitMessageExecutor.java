package com.georgejrdev.executors;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

public class GetCommitMessageExecutor {
    
    static public boolean getCommitMessage(String description) {

        final String BASE_IA_PROMPT = "Retorne uma messagem de commit com base na descrição. A messagem deve ser escrita em inglês e ser bem formatada. A descrição deve ser escrita em inglês e ser bem formatada. Você deve retornar apenas a messagem de commit, sem nada mais. Descrição: ";
        final String FULL_IA_PROMPT = BASE_IA_PROMPT + description;

        final String IA_API_KEY = System.getenv("STK_GEMINI_API_KEY");
        final String URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + IA_API_KEY;

        JSONObject json = new JSONObject();
        JSONArray contentsArray = new JSONArray();
        JSONObject contentObject = new JSONObject();
        JSONArray partsArray = new JSONArray();

        JSONObject partObject = new JSONObject();
        partObject.put("text", FULL_IA_PROMPT);
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
                
                System.out.println(commitMessage);
                return true;

            } else {
                String errorResponse = response.body().string();
                System.out.println("Erro: " + response.code());
                System.out.println(errorResponse);
                return false;
            }
            
        } catch (IOException e) {
            
            System.out.println("Network or IO error: " + e.getMessage());
            return false;
        }
    }
}
