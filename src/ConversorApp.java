import service.ConversorService;
import service.MenuService;

public class ConversorApp {
    public static void main(String[] args) {
        MenuService menuService = new MenuService();
        menuService.mostrarMenu();
    }
}
