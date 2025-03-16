package Interface;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import Logic.Biblioteca;
import Model.*;

public class Main {

    public static void main(String[] args) {
        String rutaBiblioteca = System.getProperty("user.dir") + "\\src\\ficheros\\biblioteca.xml";
        String rutaDestino = System.getProperty("user.dir") + "\\src\\ficheros\\biblioteca.xml";

        Biblioteca biblioteca = new Biblioteca();
        try {
            biblioteca.LeerFicheroXML(rutaBiblioteca);
            Scanner scanner = new Scanner(System.in);
            int opcionPrincipal;

            do {
                System.out.println("Menú Principal:");
                System.out.println("1. Libros");
                System.out.println("2. Usuarios");
                System.out.println("3. Préstamos");
                System.out.println("4. Listar Todo");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                opcionPrincipal = scanner.nextInt();
                scanner.nextLine();

                switch (opcionPrincipal) {
                    case 1:
                        menuLibros(biblioteca, scanner, rutaDestino);
                        break;
                    case 2:
                        menuUsuarios(biblioteca, scanner, rutaDestino);
                        break;
                    case 3:
                        menuPrestamos(biblioteca, scanner, rutaDestino);
                        break;
                    case 4:
                        System.out.println("Libros:");
                        biblioteca.listarLibros();
                        System.out.println("\nUsuarios:");
                        biblioteca.listarUsuarios();
                        System.out.println("\nPréstamos:");
                        biblioteca.listarPrestamos();
                        System.out.println("\nPréstamos Activos:");
                        biblioteca.listarPrestamosActivos();
                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } while (opcionPrincipal != 5);

            biblioteca.escribirFicheroXML(rutaDestino);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void menuLibros(Biblioteca biblioteca, Scanner scanner, String rutaDestino) {
        int opcionLibros;
        do {
            System.out.println("\nMenú Libros:");
            System.out.println("1. Listar Libros");
            System.out.println("2. Añadir Libro");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcionLibros = scanner.nextInt();
            scanner.nextLine();

            switch (opcionLibros) {
                case 1:
                    biblioteca.listarLibros();
                    break;
                case 2:
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Género: ");
                    String genero = scanner.nextLine();
                    System.out.print("Año: ");
                    String ano = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    biblioteca.anadirLibro(new Libro(id, titulo, genero, ano, autor));
                    biblioteca.escribirFicheroXML(rutaDestino);
                    break;
                case 3:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcionLibros != 3);
    }

    private static void menuUsuarios(Biblioteca biblioteca, Scanner scanner, String rutaDestino) {
        int opcionUsuarios;
        do {
            System.out.println("\nMenú Usuarios:");
            System.out.println("1. Listar Usuarios");
            System.out.println("2. Añadir Usuario");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcionUsuarios = scanner.nextInt();
            scanner.nextLine();

            switch (opcionUsuarios) {
                case 1:
                    biblioteca.listarUsuarios();
                    break;
                case 2:
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Nacionalidad: ");
                    String nacionalidad = scanner.nextLine();
                    biblioteca.anadirUsuario(new Usuario(id, nombre, nacionalidad));
                    biblioteca.escribirFicheroXML(rutaDestino);
                    break;
                case 3:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcionUsuarios != 3);
    }

    private static void menuPrestamos(Biblioteca biblioteca, Scanner scanner, String rutaDestino) {
        int opcionPrestamos;
        do {
            System.out.println("\nMenú Préstamos:");
            System.out.println("1. Listar Préstamos");
            System.out.println("2. Añadir Préstamo");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcionPrestamos = scanner.nextInt();
            scanner.nextLine();

            switch (opcionPrestamos) {
                case 1:
                    biblioteca.listarPrestamos();
                    System.out.println("\nPréstamos Activos:");
                    biblioteca.listarPrestamosActivos();
                    break;
                case 2:
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("ID Usuario: ");
                    String idUsuario = scanner.nextLine();
                    System.out.print("ID Libro: ");
                    String idLibro = scanner.nextLine();
                    System.out.print("Fecha Inicio (yyyy-MM-dd): ");
                    String fechaInicio = scanner.nextLine();
                    System.out.print("Fecha Fin (yyyy-MM-dd): ");
                    String fechaFin = scanner.nextLine();
                    biblioteca.anadirPrestamo(new Prestamo(id, idUsuario, idLibro, fechaInicio, fechaFin));
                    biblioteca.escribirFicheroXML(rutaDestino);
                    break;
                case 3:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcionPrestamos != 3);
    }
}