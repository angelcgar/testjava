package gm.zona_fit;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import java.util.List;
import java.util.Scanner;

//@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

    @Autowired
    private IClienteServicio clienteServicio;

    String nl = System.lineSeparator();

    // private static final logger =
    private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);

    public static void main(String[] args) {
        logger.info("Iniciando la aplicacion");
        // Levantar Spring
        SpringApplication.run(ZonaFitApplication.class, args);
        logger.info("¡Aplicacion Finalizada!");
    }

    @Override
    public void run(String... args) throws Exception {

        zonaFitApp();

    }

    private void zonaFitApp() {
        // logger.info(nl + "*** Aplicacion zona fit (GYM) ***" + nl);

        var salir = false;
        var consola = new Scanner(System.in);

        while (!salir) {
            try {
                var opcion = mostrarMenu(consola);

                salir = ejecutamosOpciones(consola, opcion);
                logger.info(nl);
            } catch (Exception e) {
                System.out.println("Error al ejecutar opciones: " + e.getMessage());
            }

        }
    }

    private int mostrarMenu(Scanner consola) {
        logger.info("""
                \n*** Aplicacion zona fit (GYM) ***
                1. Listar Cliente
                2. Buscar Cliente
                3. Agregar Cliente
                4. Modificar Cliente
                5. Eliminar Cliente
                6. Salir
                Elija una opcion:\s""");

        return Integer.parseInt(consola.nextLine());
    }

    private boolean ejecutamosOpciones(Scanner consola, int opcion) {
        var salir = false;
        switch (opcion) {
            case 1 -> {
                logger.info(nl + "--- Listado de clientees ---" + nl);
                List<Cliente> clientes = clienteServicio.listarCliente();
                clientes.forEach(System.out::println);
            }
            case 2 -> {
                logger.info(nl + "--- Buscar por ID ---" + nl);
                logger.info("Introduce el Id de cliente a buscar: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                // var cliente = new Cliente(idCliente);
                Cliente clienteFound = clienteServicio.buscarClientePorId(idCliente);
                if (clienteFound != null) {
                    System.out.println("Cliente encontrado: " + clienteFound);
                } else {
                    System.out.println("Cliente no encontrado: " + clienteFound);
                }
            }
            case 3 -> {
                logger.info(nl + "--- Agregar cliente ---" + nl);
                logger.info("Nombre: ");
                var name = consola.nextLine();
                logger.info("Aprellido: ");
                var lastname = consola.nextLine();
                logger.info("Membresia: ");
                var membership = Integer.parseInt(consola.nextLine());
                // Creamos una insersion
                var cliente = new Cliente();
                cliente.setName(name);
                cliente.setLastname(lastname);
                cliente.setMembership(membership);
                clienteServicio.guardarCliente(cliente);
                logger.info("Cliente agregado: " + cliente + nl);

            }
            case 4 -> {
                logger.info(nl + "--- Modificar Cliente ---" + nl);
                logger.info("Id cliente: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
                if (cliente != null) {

                    logger.info("Nombre: ");
                    var name = consola.nextLine();
                    logger.info("Apellido: ");
                    var lastname = consola.nextLine();
                    logger.info("Membresia: ");
                    var membership = Integer.parseInt(consola.nextLine());
                    cliente.setName(name);
                    cliente.setLastname(lastname);
                    cliente.setMembership(membership);
                    clienteServicio.guardarCliente(cliente);
                    logger.info("Cliente modificado: " + cliente + nl);
                } else {
                    logger.info("Cliente no encontrado: " + cliente + nl);
                }
            }
            case 5 -> {
                logger.info("--- Eliminar Cliente ---" + nl);
                logger.info("Id cliente: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = clienteServicio.buscarClientePorId(idCliente);

                if (cliente != null) {
                    clienteServicio.eliminarCliente(cliente);
                    logger.info("Cliente eliminado: " + cliente + nl);
                } else {
                    logger.info("Cliente no eliminado: " + cliente + nl);
                }
            }
            case 6 -> {
                logger.info("Vuelva pronto!" + nl + nl);
                salir = true;
            }
            default -> logger.info("Caracter Invalido" + nl);
        }

        return salir;
    }

}
