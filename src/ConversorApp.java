import model.Moneda;
import service.ConversorService;

public class ConversorApp {
    public static void main(String[] args) {
        ConversorService conversorService = new ConversorService();
        double resultado = conversorService.convertir("USD", "EUR", 100.0);
        System.out.println("Resultado de la conversión: " + resultado);
    }
}
