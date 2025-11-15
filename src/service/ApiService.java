package service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiService {

    // Campos finales de instancia (no static porque pueden cambiar por instancia en pruebas)
    private final String apiBaseUrl;
    private final String apiKey;

    public ApiService() {
        Properties props = new Properties();

        // Se busca el archivo en el classpath raíz (por ejemplo: src/main/resources/application.properties)
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("./resources/application.properties")) {
            if (input == null) {
                throw new IllegalStateException("No se encontró el archivo application.properties.");
            }

            // Carga las propiedades desde el stream
            props.load(input);

            // Obtiene valores y los asigna a campos finales
            this.apiKey = props.getProperty("api.key");
            this.apiBaseUrl = props.getProperty("api.url.base");


            // Validaciones simples para evitar valores nulos o vacíos
            if (this.apiKey == null || this.apiKey.isBlank()) {
                throw new IllegalStateException("Propiedad 'api.key' no encontrada o vacía.");
            }
            if (this.apiBaseUrl == null || this.apiBaseUrl.isBlank()) {
                throw new IllegalStateException("Propiedad 'api.url.base' no encontrada o vacía.");
            }

        } catch (IOException ex) {
            throw new RuntimeException("Error al cargar propiedades de configuración.", ex);
        }

    }

    public String getApiBaseUrl() {
        return apiBaseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

}
