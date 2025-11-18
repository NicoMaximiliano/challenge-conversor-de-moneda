package service;

import model.Moneda;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuService {

    private final ConversorService conversorService = new ConversorService();
    private final Scanner entrada = new Scanner(System.in);

    public void mostrarMenu() {
        int opc = 0;
        double valor = 0.0;
        double resultado = 0.0;

        do{
            try{
                System.out.println("\n=== Menú Principal ===");
                System.out.println("1. Dolar a Peso argentino");
                System.out.println("2. Peso argentino a Dolar");
                System.out.println("3. Dolar a Real brasileño");
                System.out.println("4. Real brasileño a Dolar");
                System.out.println("5. Dolar a Peso colombiano");
                System.out.println("6. Peso colombiano a Dolar");
                System.out.println("7. Salir");

                System.out.println("\nSelecciona una opción: ");
                opc = entrada.nextInt();

                if ((opc >= 1) && (opc <= 6)){
                    System.out.println("\nIngresa la cantidad a convertir $: ");
                    valor = entrada.nextDouble();

                    if (valor < 0){
                        System.out.println("Cantidad inválida. Por favor, ingresa un valor mayor o igual a 0.");
                        continue; // Volver al inicio del bucle para mostrar el menú nuevamente
                    }
                }


                switch (opc){
                    case 1:
                        resultado = conversorService.convertir(Moneda.ESTADOSUNIDOS.getCodigo(), Moneda.ARGENTINA.getCodigo(), valor);
                        System.out.println("\nResultado de la conversión $" + String.format("%.2f", resultado));
                        break;
                    case 2:
                        resultado = conversorService.convertir(Moneda.ARGENTINA.getCodigo(), Moneda.ESTADOSUNIDOS.getCodigo(), valor);
                        System.out.println("\nResultado de la conversión $" + String.format("%.2f", resultado));
                        break;
                    case 3:
                        resultado = conversorService.convertir(Moneda.ESTADOSUNIDOS.getCodigo(), Moneda.BRASIL.getCodigo(), valor);
                        System.out.println("\nResultado de la conversión $" + String.format("%.2f", resultado));
                        break;
                    case 4:
                        resultado = conversorService.convertir(Moneda.BRASIL.getCodigo(), Moneda.ESTADOSUNIDOS.getCodigo(), valor);
                        System.out.println("\nResultado de la conversión $" + String.format("%.2f", resultado));
                        break;
                    case 5:
                        resultado = conversorService.convertir(Moneda.ESTADOSUNIDOS.getCodigo(), Moneda.COLOMBIA.getCodigo(), valor);
                        System.out.println("\nResultado de la conversión $" + String.format("%.2f", resultado));
                        break;
                    case 6:
                        resultado = conversorService.convertir(Moneda.COLOMBIA.getCodigo(), Moneda.ESTADOSUNIDOS.getCodigo(), valor);
                        System.out.println("\nResultado de la conversión $" + String.format("%.2f", resultado));
                        break;
                    case 7:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elige una opción del menú.");
                }

            }
            catch (InputMismatchException e){
                System.out.println("Entrada no válida. Por favor, ingresa un número.");
                entrada.next(); // Limpiar el buffer de entrada
            }

        }while (opc != 7);

    }


}
