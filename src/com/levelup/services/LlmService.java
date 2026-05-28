package com.levelup.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.levelup.config.ConfigLoader;

public class LlmService {

    private static final String OPENROUTER_URL = "https://openrouter.ai/api/v1/chat/completions";

    public String enviarPrompt(String prompt) {
        String apiKey = ConfigLoader.getOpenRouterApiKey();
        String model = ConfigLoader.getOpenRouterModel();

        if (apiKey == null || apiKey.trim().isEmpty()) {
            return "Error: falta la API Key en config.properties";
        }

        if (model == null || model.trim().isEmpty()) {
            return "Error: falta el modelo en config.properties";
        }

        if (prompt == null || prompt.trim().isEmpty()) {
            return "Error: el prompt no puede estar vacío";
        }

        HttpURLConnection conn = null;

        try {
            URL url = new URL(OPENROUTER_URL);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + apiKey.trim());
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("HTTP-Referer", "http://localhost");
            conn.setRequestProperty("X-Title", "LevelUp Arcade");
            conn.setDoOutput(true);

            JsonObject body = new JsonObject();
            body.addProperty("model", model.trim());

            JsonArray messages = new JsonArray();

            JsonObject systemMessage = new JsonObject();
            systemMessage.addProperty("role", "system");
            systemMessage.addProperty("content",
                    "Eres un asistente para una tienda llamada LevelUp Arcade. Responde en español de forma breve, clara y útil.");
            messages.add(systemMessage);

            JsonObject userMessage = new JsonObject();
            userMessage.addProperty("role", "user");
            userMessage.addProperty("content", prompt.trim());
            messages.add(userMessage);

            body.add("messages", messages);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.toString().getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = conn.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    responseCode >= 200 && responseCode < 300 ? conn.getInputStream() : conn.getErrorStream(),
                    StandardCharsets.UTF_8));

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            if (responseCode < 200 || responseCode >= 300) {
                return "Error en OpenRouter. Código: " + responseCode + " Respuesta: " + response;
            }

            JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
            JsonArray choices = jsonResponse.getAsJsonArray("choices");

            if (choices == null || choices.isEmpty()) {
                return "Error: la respuesta no contiene texto generado.";
            }

            JsonObject firstChoice = choices.get(0).getAsJsonObject();
            JsonObject message = firstChoice.getAsJsonObject("message");

            if (message == null || !message.has("content")) {
                return "Error: no se encontró el contenido de la respuesta.";
            }

            return message.get("content").getAsString().trim();

        } catch (Exception e) {
            return "Error al conectar con OpenRouter: " + e.getMessage();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public String generarDescripcionProducto(String nombreProducto) {
        String prompt = "Genera una descripción breve y atractiva para el producto: "
                + nombreProducto + ". Máximo 60 palabras.";
        return enviarPrompt(prompt);
    }

    public String sugerirCategoriaProducto(String nombreProducto) {
        String prompt = "Sugiere la categoría más adecuada para este producto de LevelUp Arcade: "
                + nombreProducto
                + ". Responde solo con una categoría entre: Videojuegos, Merchandising, Consolas, Cartas.";
        return enviarPrompt(prompt);
    }
}