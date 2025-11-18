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
        InputStream input = getClass().getClassLoader().getResourceAsStream("./resources/application.properties");

        if (input == null) {
            throw new IllegalStateException("No se encontró el archivo `application.properties` en el classpath.");
        }

        try {
            // Carga las propiedades desde el stream
            props.load(input);
        }
        catch (IOException e) {
            //throw new RuntimeException("Error al cargar el archivo de configuración.", e);
            System.err.println("Error al cargar el archivo de configuración.");
        }

        // Obtiene valores y los asigna a campos finales
        this.apiBaseUrl = props.getProperty("api.url.base");
        this.apiKey = props.getProperty("api.key");
    }


    public String getApiBaseUrl() {
        return apiBaseUrl;
    }


    public String getApiKey() {
        return apiKey;
    }

}
