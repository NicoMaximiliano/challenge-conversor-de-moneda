package service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class ConversorService {
    private final String API_BASE_URL;
    private final String API_KEY;

    private final HttpClient client = HttpClient.newHttpClient();

    public ConversorService() {
        Properties props = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("./resources/application.properties")) {
            if (input == null) {
                throw new IllegalStateException("No se encontró el archivo application.properties.");
            }
            props.load(input);
            // Carga los valores
            this.API_KEY = props.getProperty("api.key");
            this.API_BASE_URL = props.getProperty("api.url.base");
        } catch (IOException ex) {
            throw new RuntimeException("Error al cargar propiedades de configuración.", ex);
        }
    }


    public String convertir(String monedaOrigen, String monedaDestino, double cantidad) {
        String json;

        String url = API_BASE_URL + API_KEY + "/pair/" + monedaOrigen + "/" + monedaDestino + "/" + cantidad;


        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            json = response.body();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        catch (IllegalArgumentException e){
            throw new RuntimeException("Error en la solicitud: " + e.getMessage());
        }

        return json;
    }
}
