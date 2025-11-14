package service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiService {

    private final String API_BASE_URL;
    private final String API_KEY;

    public ApiService() {
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

    public String getApiBaseUrl() {
        return API_BASE_URL;
    }

    public String getApiKey() {
        return API_KEY;
    }

}
