import java.util.Scanner;
import java.util.Random;

public class App {
    static Scanner scanner = new Scanner(System.in);
    static int option;
    static int progress = 0;
    static String[] planets = { "Marte", "Júpiter", "Saturno" };
    static String[] ships = { "Exploradora", "Carga pesada", "Turbo" };
    static String[] troubles = {"Meteroritos", "Asteroides", "Turbulencias","Falta de combustible"};
    static double[] distance = { 225.0, 628.0, 1426.0 };
    static double[] speeds = { 20000.0, 15000.0, 30000.0 };
    static Random random = new Random();
 

    // Verificación de la selección que hace el usuario
    static boolean selectPlanet = false;
    static boolean selectShip = false;

    public static void main(String[] args) throws Exception {
        do {
            showMenu();
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    selectPlanet();
                    break;
                case 2:
                    selectShip();
                    break;
                case 3:
                    iniciarSimulacion();
                    break;
                case 4:
                    System.out.println("\033[32mSaliendo... ¡Gracias por usar la simulación!\033[0m");
                    break;
                default:
                    System.err.println("\033[31m¡¡Selecciona una opción válida! (1-4).\033[0m");
                    break;
            }
        } while (option != 4);
    }

    // Menú Principal
    public static void showMenu() {
        System.out.println("\n\033[1;4;32m\t ### Menú Principal ### \033[0m\n");
        System.out.println("\033[1m1).\033[0m Planeta destino");
        System.out.println("\033[1m2).\033[0m Nave espacial");
        System.out.println("\033[1m3).\033[0m Iniciar la simulación del vuelo");
        System.out.println("\033[1m4).\033[0m Salir");
        System.out.print("\033[1mPor favor, elige una opción: \033[0m");
    }

    // Selección de planeta
    public static void selectPlanet() {
        System.out.println("\n\033[4;1m\tMenú Planetas y sus distancias desde la Tierra \033[0m\n");
        for (int i = 0; i < planets.length; i++) {
            System.out.println((i + 1) + ". " + planets[i] + " - Distancia: " + distance[i] + " millones de km");
        }
        System.out.print("\033[1mElige el número de tu planeta destino: ");
        int selection = scanner.nextInt();

        if (selection >= 1 && selection <= planets.length) {
            System.out.println("\n\033[32mHas seleccionado " + planets[selection - 1] + ".\033[0m");
            selectPlanet = true; // Se marca como seleccionado el planeta
        } else {
            System.err.println("\033[31mSelección no válida. Por favor, intenta de nuevo.\033[0m");
        }
    }

    // Selección de nave
    public static void selectShip() {
        System.out.println("\n\033[1;34mSelecciona una nave espacial:\033[0m");
        for (int i = 0; i < ships.length; i++) {
            System.out.println((i + 1) + ". " + ships[i] + " - Velocidad: " + speeds[i] + " km/h");
        }
        System.out.print("\033[1mElige el número de tu nave espacial: ");
        int option = scanner.nextInt();

        if (option >= 1 && option <= ships.length) {
            System.out.println("\033[32mHas seleccionado la nave " + ships[option - 1] + ".\033[0m");
            selectShip = true; // Se marca como seleccionada la nave
        } else {
            System.err.println("\033[31mSelección no válida. Por favor, intenta de nuevo.\033[0m");
        }

    }

    // Iniciar la simulación
    public static void iniciarSimulacion() {
        if (selectPlanet && selectShip) {
            System.out.println("\n\033[1;34mIniciando simulación de viaje...\033[0m");
            events();
        } else {
            System.err.println("\033[31m¡ERROR! Debes seleccionar un planeta y una nave antes de iniciar el viaje.\033[0m");
        }
    }



    public static void calcularRecursos() {

    }

    public static void events() {
        var random= new Random();
        var distanceTrouble1=random.nextInt(1,30);
        var distanceTrouble2=random.nextInt(31,60);
        var distanceTrouble3=random.nextInt(61,100);
        var fix= random.nextInt(1,5);    
        boolean isFixed = true;
        System.out.println("********** HA COMENZADO EL RECORRIDO **********");
        // va aumentando el progreso del viaje, se tendra que encontrar con 3 eventos durante el recorrido
        while(progress<=100) {
            progress++;
             if(progress == distanceTrouble1) {
                var trouble= random.nextInt(1,4)-1;
                System.out.println("\n Se ha presentado un inconveniente : " + troubles[trouble].toUpperCase() );
                System.out.println("Para arreglar el problema, debe adivinar un numero del 1 al 5");
                if (fixEvent(isFixed,fix) == false){
                    break;
                }
            }else if (progress == distanceTrouble2) {
                var trouble= random.nextInt(1,4)-1;
                System.out.println("\n Se ha presentado un inconveniente : " +troubles[trouble].toUpperCase());
                System.out.println("Para arreglar el problema, debe adivinar un numero del 1 al 5");
                if (fixEvent(isFixed,fix) == false){
                    break;
                }
            }else if(progress == distanceTrouble3){
                var trouble= random.nextInt(1,4)-1;
                System.out.println("\n Se ha presentado un inconveniente : " + troubles[trouble].toUpperCase());
                System.out.println("Para arreglar el problema, debe adivinar un numero del 1 al 5");
                if (fixEvent(isFixed,fix) == false){
                    break;
                }
            }else{
                if (progress == 100) {
                    System.out.println("\n ***** Felicidades, hemos llegado al destino. *****");
                    break;
                }else{
                System.out.print(progress+"% ..");
            }
         }
        }
    }

    // esta funcion es para que el usuario arregle el evento adivinando un numero
    public static boolean fixEvent(boolean isFixed, int fix){
        int tempts =3;
        while (isFixed == true) {
            System.out.println("Ingrese un numero: ");
            option= scanner.nextInt();
            if (option == fix) {
                System.out.println("***** Haz arreglado el problema. Continuamos! *****");
                break;
            }else{
                tempts-=1;
                if (tempts>0) {
                    System.out.println("Tienes "+tempts+" intentos");
                    if (fix < option) {
                        System.out.println("PISTA: Es menor que: " + option );
                    }else{
                        System.out.println("PISTA: Es mayor que: " + option );
                    }
                }else{
                    System.out.println("***** No pudiste resolverlo. La nave caerá. *****");
                    isFixed=false;
                    break;
                }
            }
        }
        return isFixed;
    }

    public static void detenerNave() {

    }

}
