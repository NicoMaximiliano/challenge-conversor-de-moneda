package service;

import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversorService {

    // Servicio auxiliar que proporciona la URL base y la clave de la API
    private final ApiService apiService = new ApiService();

    // Cliente HTTP reutilizable
    private final HttpClient client = HttpClient.newHttpClient();

    public double convertir(String monedaOrigen, String monedaDestino, double cantidad) {
        double tasa = 0;

        try {
            // Construye la URL de la API
            String url = apiService.getApiBaseUrl() + apiService.getApiKey() + "/pair/" + monedaOrigen + "/" + monedaDestino + "/" + cantidad;

            // Construye la solicitud HTTP GET
            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Envía la solicitud y obtiene la respuesta como String
            HttpResponse<String> respuesta = client
                    .send(solicitud, HttpResponse.BodyHandlers.ofString());

            // Parsea la respuesta JSON
            JsonElement elemento = JsonParser.parseString(respuesta.body());

            JsonObject objectRoot = elemento.getAsJsonObject();

            // Extrae el campo "conversion_result" con el resultado numérico
            tasa = objectRoot.get("conversion_result").getAsDouble();

        } catch (IOException e) {
            System.err.println("Error de comunicación al llamar a la API: " + e.getMessage());
        }
        catch (InterruptedException e) {
            System.err.println("La solicitud fue interrumpida: " + e.getMessage());
        }

        return tasa;
    }


}
