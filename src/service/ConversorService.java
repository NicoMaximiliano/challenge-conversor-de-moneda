package service;

import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversorService {

    private final ApiService apiService = new ApiService();
    private final HttpClient client = HttpClient.newHttpClient();
    
    public double convertir(String monedaOrigen, String monedaDestino, double cantidad) {
        double tasa = 0;

        String url = apiService.getApiBaseUrl() + apiService.getApiKey() + "/pair/" + monedaOrigen + "/" + monedaDestino + "/" + cantidad;

        try {
            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> respuesta = client
                    .send(solicitud, HttpResponse.BodyHandlers.ofString());

            JsonElement elemento = JsonParser.parseString(respuesta.body());
            JsonObject objectRoot = elemento.getAsJsonObject();

            tasa = objectRoot.get("conversion_result").getAsDouble();

        } catch (IOException e) {
            System.out.println("Error de comunicación: " + e.getMessage());
        }
        catch (InterruptedException e) {
            System.out.println("La solicitud fue interrumpida: " + e.getMessage());
        }

        return tasa;
    }


}
