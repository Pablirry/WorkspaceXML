package Interface;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import Logic.Biblioteca;
import Model.*;

public class Main {

    public static void main(String[] args) {
        String rutaBiblioteca = System.getProperty("user.dir") + "\\src\\ficheros\\Biblioteca.xml";
        String rutaDestino = System.getProperty("user.dir") + "\\src\\ficheros\\biblioteca_actualizada.xml";

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
                try {
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
                } catch (InputMismatchException e) {
                    System.out.println("Introduce un número.");
                    scanner.nextLine();
                    opcionPrincipal = 0;
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
            try {
                opcionLibros = scanner.nextInt();
                scanner.nextLine();

                switch (opcionLibros) {
                    case 1:
                        biblioteca.listarLibros();
                        break;
                    case 2:
                        try {
                            System.out.print("ID: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            if (biblioteca.existeLibro(id)) {
                                System.out.println("El ID ya está registrado.");
                                break;
                            }
                            System.out.print("Título: ");
                            String titulo = scanner.nextLine();
                            System.out.print("Género: ");
                            String genero = scanner.nextLine();
                            System.out.print("Año: ");
                            int ano = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Autor: ");
                            String autor = scanner.nextLine();
                            try {
                                biblioteca.anadirLibro(new Libro(id, titulo, genero, ano, autor));
                                biblioteca.escribirFicheroXML(rutaDestino);
                            } catch (Exception e) {
                                System.out.println("Error al añadir el libro: " + e.getMessage());
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Ingrese los datos correctamente.");
                            scanner.nextLine();
                        }
                        break;
                    case 3:
                        System.out.println("Volviendo al Menú Principal...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Introduce un número.");
                scanner.nextLine();
                opcionLibros = 0;
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
            try {
                opcionUsuarios = scanner.nextInt();
                scanner.nextLine();

                switch (opcionUsuarios) {
                    case 1:
                        biblioteca.listarUsuarios();
                        break;
                    case 2:
                        try {
                            System.out.print("ID: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            if (biblioteca.existeUsuario(id)) {
                                System.out.println("El ID ya está registrado.");
                                break;
                            }
                            System.out.print("Nombre: ");
                            String nombre = scanner.nextLine();
                            System.out.print("Nacionalidad: ");
                            String nacionalidad = scanner.nextLine();
                            try {
                                biblioteca.anadirUsuario(new Usuario(id, nombre, nacionalidad));
                                biblioteca.escribirFicheroXML(rutaDestino);
                            } catch (Exception e) {
                                System.out.println("Error al añadir el usuario: " + e.getMessage());
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Ingrese los datos correctamente.");
                            scanner.nextLine();
                        }
                        break;
                    case 3:
                        System.out.println("Volviendo al Menú Principal...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Introduce un número.");
                scanner.nextLine();
                opcionUsuarios = 0;
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
            try {
                opcionPrestamos = scanner.nextInt();
                scanner.nextLine();

                switch (opcionPrestamos) {
                    case 1:
                        biblioteca.listarPrestamos();
                        System.out.println("\nPréstamos Activos:");
                        biblioteca.listarPrestamosActivos();
                        break;
                    case 2:
                        try {
                            System.out.print("ID: ");
                            int id = scanner.nextInt();
                            if (biblioteca.existePrestamo(id)) {
                                System.out.println("El ID ya está registrado.");
                                break;
                            }
                            System.out.print("ID Usuario: ");
                            int idUsuario = scanner.nextInt();
                            if (!biblioteca.existeUsuario(idUsuario)) {
                                System.out.println("El ID de usuario no existe.");
                                break;
                            }
                            System.out.print("ID Libro: ");
                            int idLibro = scanner.nextInt();
                            if (!biblioteca.existeLibro(idLibro)) {
                                System.out.println("El ID de libro no existe.");
                                break;
                            }
                            scanner.nextLine();
                            System.out.print("Fecha Inicio (yyyy-MM-dd): ");
                            String fechaInicio = scanner.nextLine();
                            System.out.print("Fecha Fin (yyyy-MM-dd): ");
                            String fechaFin = scanner.nextLine();
                            try {
                                biblioteca.anadirPrestamo(new Prestamo(id, idUsuario, idLibro, fechaInicio, fechaFin));
                            } catch (Exception e) {
                                System.out.println("Error al añadir el préstamo: " + e.getMessage());
                            }
                            biblioteca.escribirFicheroXML(rutaDestino);
                        } catch (InputMismatchException e) {
                            System.out.println("Ingrese los datos correctamente.");
                            scanner.nextLine();
                        }
                        break;
                    case 3:
                        System.out.println("Volviendo al Menú Principal...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Introduce un número.");
                scanner.nextLine();
                opcionPrestamos = 0;
            }
        } while (opcionPrestamos != 3);
    }
}