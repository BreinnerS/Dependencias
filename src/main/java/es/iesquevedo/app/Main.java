package es.iesquevedo.app;

public class Main {
    public static void main(String[] args) {
        // 1. Preparamos las rutas
        String basePath = System.getProperty("user.dir");

        // 2. Creamos los DAOs (Aquí es el único lugar donde usamos "new Json...")
        var peliculaDao = new es.iesquevedo.dao.JsonPeliculaDao(basePath + "/peliculas.json");
        var socioDao = new es.iesquevedo.dao.JsonSocioDao(basePath + "/socios.json");
        var alquilerDao = new es.iesquevedo.dao.JsonAlquilerDao(basePath + "/alquileres.json");

        // 3. Creamos el Servicio (inyectando los DAOs)
        var servicio = new es.iesquevedo.servicios.AlquilerService(peliculaDao, socioDao, alquilerDao);

        // 4. Creamos la App (inyectando el Servicio y los DAOs para los menús)
        es.iesquevedo.app.ConsoleApp app = new es.iesquevedo.app.ConsoleApp(servicio, peliculaDao, socioDao);

        // 5. Arrancamos el bucle
        try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
            boolean running = true;
            while (running) {
                // LLAMAMOS AL METODO QUE PINTA EL MENÚ (asegúrate de tenerlo en tu Main o en app)
                // Si el metodo printMenu() es privado estático en Main, úsalo así:
                printMenu();

                System.out.print("Elige una opción: ");
                String opt = scanner.nextLine().trim();

                switch (opt) {
                    case "1" -> app.crearPelicula(scanner);
                    case "2" -> app.listarPeliculas();
                    case "3" -> app.crearSocio(scanner);
                    case "4" -> app.listarSocios();
                    case "5" -> app.alquilar(scanner);
                    case "6" -> app.listarAlquileres();
                    // case "7" -> ... (tu lógica para listar alquileres por socio)
                    case "8" -> app.devolver(scanner);
                    case "0" -> {
                        running = false;
                        System.out.println("Saliendo...");
                    }
                    default -> System.out.println("Opción no válida");
                }
                System.out.println();
            }
        }
    }
    private static void printMenu() {
        System.out.println("--- Videoclub ---");
        System.out.println("1. Añadir/Actualizar Pelicula");
        System.out.println("2. Listar Peliculas");
        System.out.println("3. Añadir/Actualizar Socio");
        System.out.println("4. Listar Socios");
        System.out.println("5. Alquilar película");
        System.out.println("6. Listar todos los alquileres");
        System.out.println("8. Devolver alquiler (por id)");
        System.out.println("0. Salir");
    }

}
