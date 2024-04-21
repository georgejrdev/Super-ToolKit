import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;


public class ToolKit {
    private static final String TRANSLATION_API_URL = "https://translate-api-0unn.onrender.com/getTranslation/";
    private static final String SEPARATOR = ",";


    public static String getTranslation(String text, String dest) {
        String requestUrl = TRANSLATION_API_URL + text + SEPARATOR + dest;

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String response = reader.readLine();
                Object[] formattedStringToArray = getArray(response);

                boolean result = (boolean) formattedStringToArray[0];

                if (result) {
                    return formattedStringToArray[1].toString();
                } else {
                    return "Error communicating with the Translation API";
                }
            } catch (IOException e) {
                return "Error reading response from the Translation API: " + e.getMessage();
            } finally {
                connection.disconnect();
            }
        } catch (IOException e) {
            return "Error connecting to the Translation API: " + e.getMessage();
        }
    }


    private static Object[] getArray(String text) {
        String temporaryText = text.substring(1, text.length() - 1);
        String[] elements = temporaryText.split(",");
        Object[] array = new Object[elements.length];

        for (int i = 0; i < elements.length; i++) {
            String element = elements[i].trim();
            if (element.equals("true") || element.equals("false")) {
                array[i] = Boolean.parseBoolean(element);
            } else if (element.startsWith("\"") && element.endsWith("\"")) {
                array[i] = element.substring(1, element.length() - 1); // Remova as aspas
            } else {
                array[i] = element;
            }
        }

        return array;
    }


    public static void main(String[] args) {
        System.out.println(getTranslation("Olá", "en"));
    }
}