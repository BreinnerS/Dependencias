package es.iesquevedo.servicios;

import es.iesquevedo.dao.JsonAlquilerDao;
import es.iesquevedo.dao.JsonPeliculaDao;
import es.iesquevedo.dao.JsonSocioDao;
import es.iesquevedo.modelo.Alquiler;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AlquilerServiceTest {

    @Test
    void testListarAlquileres() {
        // 1. PREPARACIÓN (Arrange)
        // Definimos la ruta base
        String basePath = System.getProperty("user.dir");

        // Creamos los DAOs apuntando EXPRESAMENTE a los archivos "_test.json"
        var peliculaDaoTest = new JsonPeliculaDao(basePath + "/peliculas_test.json");
        var socioDaoTest = new JsonSocioDao(basePath + "/socios_test.json");
        var alquilerDaoTest = new JsonAlquilerDao(basePath + "/alquileres_test.json");

        // Inyectamos estos DAOs de prueba al servicio
        AlquilerService servicio = new AlquilerService(peliculaDaoTest, socioDaoTest, alquilerDaoTest);

        // 2. EJECUCIÓN (Act)
        // Probamos el metodo
        List<Alquiler> resultados = servicio.listarAlquileres();

        // 3. VERIFICACIÓN (Assert)
        // Comprobamos que la lista no sea nula (significa que funcionó la lectura)
        assertNotNull(resultados, "La lista de alquileres no debería ser nula");

        // Mensaje de éxito en consola
        System.out.println("Test pasado correctamente. Se leyeron " + resultados.size() + " alquileres.");
    }
}
